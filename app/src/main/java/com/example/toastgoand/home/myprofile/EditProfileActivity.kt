package com.example.toastgoand.home.myprofile

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toFile
import androidx.core.net.toUri
import androidx.viewbinding.ViewBinding
import coil.compose.rememberImagePainter
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityEditProfileBinding
import com.example.toastgoand.home.myprofile.network.EditProfileApi
import com.example.toastgoand.home.myprofile.network.EditProfileDataClass
import com.example.toastgoand.uibits.HeaderOtherScreens
import com.example.toastgoand.uibits.SqaureRoundedIcon
import com.google.accompanist.insets.ProvideWindowInsets
import compose.icons.FeatherIcons
import compose.icons.feathericons.Edit
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import retrofit2.http.Part
import android.os.FileUtils
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import okhttp3.*
import okhttp3.RequestBody.Companion.asRequestBody
import okio.IOException
import java.io.File
import java.io.FileOutputStream


class EditProfileActivity : BaseActivity() {

    private lateinit var binding: ActivityEditProfileBinding

    private val pickImage = 100
    private var imageUri: Uri? = null
    private var imagePath: String? = ""
    private var profileupdateid: String? = ""
    private var imageData = "".toUri()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityEditProfileBinding

        fun onBackPressedHere() {
            onBackPressed()
        }

        fun getPathFromContent(link: Uri): String {

            val projection = arrayOf(MediaStore.Images.Media.DATA)
            val cursor: Cursor? = contentResolver.query(link, projection, null, null, null)

            val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()

            return cursor.getString(column_index)
        }

//        private const val BUFFER_SIZE = 1024 * 2

//        fun createOutputFile(
//            context: Context,
//            contentUri: Uri,
//            fileNamePrefix: String,
//            defaultFileExtension: String
//        ): File {
//            var count = 0
//            var file: File
//
//            val uriPath: String? = contentUri.path
//            val fileExtension = if (uriPath == null) defaultFileExtension
//            else MediaFileHelper.getFileExtension(uriPath)
//
//            do {
//                count++
//
//                val mFileName = "$fileNamePrefix${StringHelper.getUniqueId()}$count$fileExtension"
//                val newFilePath =
//                    "${context.getExternalFilesDir(null)?.absolutePath}${context.getString(R.string.audio_select_directory)}/$mFileName"
//
//                file = File(newFilePath)
//
//            } while (file.exists() && !file.isDirectory)
//
//            return file
//        }
//
//        fun copyUriToFile(context: Context, srcUri: Uri, dstFile: File) {
//            try {
//                val inputStream = context.contentResolver.openInputStream(srcUri) ?: return
//                val outputStream = FileOutputStream(dstFile)
//                inputStream.copyTo(outputStream, BUFFER_SIZE)
//                inputStream.close()
//                outputStream.close()
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//        }
//
//        fun getPathFromURI(
//            context: Context,
//            contentUri: Uri,
//            fileNamePrefix: String,
//            defaultFileExtension: String
//        ): String? {
//            val uriPath: String = contentUri.path ?: return null
//            val fileName: String = MediaFileHelper.getFileNameWithExtension(uriPath)
//
//            if (fileName.isNotBlank()) {
//                val destFile = createOutputFile(context, contentUri, fileNamePrefix, defaultFileExtension)
//                copyUriToFile(context, contentUri, destFile)
//                return destFile.absolutePath
//            }
//            return null
//        }



        setContent {

            imageUri = intent.getStringExtra("olddp")?.toUri()
            profileupdateid = intent.getStringExtra("profileupdateid")

            var showGallerySelect by remember { mutableStateOf(false) }

            AyeTheme {
                val context = LocalContext.current
                val composableScope = rememberCoroutineScope()

                @Composable
                fun pickImageFromPhone() {
                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.GetContent(),
                        onResult = { uri: Uri? ->
                            imageUri = uri
                            imagePath = uri?.encodedPath
                            imageData = Uri.parse(imageUri.toString())
                            showGallerySelect = false

                        }
                    )

                    @Composable
                    fun LaunchGallery() {
                        SideEffect {
                            launcher.launch("image/*")
                        }
                    }
                    LaunchGallery()
                }

                ProvideWindowInsets() {
                    if (showGallerySelect) {
                        pickImageFromPhone()
                    } else {
                        Scaffold(
                            topBar = {
                                HeaderOtherScreens(
                                    modifier = Modifier.fillMaxWidth(),
                                    title = "",
                                    onBackIconPressed = { onBackPressedHere() }
                                )
                            }
                        ) { contentPadding ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .background(AyeTheme.colors.uiBackground),
                                verticalArrangement = Arrangement.SpaceBetween,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(0.9f)
                                        .height(100.dp)
                                        .padding(top = 10.dp, bottom = 30.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    SqaureRoundedIcon(
                                        FeatherIcons.Edit,
                                        color = AyeTheme.colors.success,
                                        modifier = Modifier.padding(horizontal = 5.dp)
                                    )
                                    Text(
                                        text = "Edit Profile",
                                        style = MaterialTheme.typography.subtitle1,
                                        color = AyeTheme.colors.success,
                                        modifier = Modifier.padding(horizontal = 20.dp)
                                    )
                                }
                                val painter = rememberImagePainter(data = imageUri)
                                Image(
                                    painter = painter,
                                    contentDescription = "Forest Image",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .size(100.dp)
                                        .clip(RoundedCornerShape(corner = CornerSize(50.dp)))
                                        .clickable {
                                            showGallerySelect = true
                                        }

                                )
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Button(
                                        onClick = {
                                            Log.i(
                                                "editprofilelogs",
                                                "clicked on button"
                                            )

                                            composableScope.launch {

                                                    imageUri?.let {
                                                        getContentResolver().openInputStream(it)
                                                            .toString().toMediaTypeOrNull().toString()
                                                    }?.let {
                                                        Log.i("uploaddpdebug",
                                                            it
                                                        )
                                                    }

//                                                AndroidNetworking.upload("https://apisayepirates.life/api/users/profile-update/${profileupdateid}/")
//                                                    .addMultipartFile("image", imageUri?.toFile())
//                                                    .addMultipartParameter("bio", "")
//                                                    .setPriority(Priority.HIGH)
//                                                    .build()

                                            }
                                        },
                                        colors = ButtonDefaults.textButtonColors(
                                            backgroundColor = AyeTheme.colors.success,
                                        ),
                                        shape = RoundedCornerShape(30.dp),
                                        modifier = Modifier
                                            .padding(vertical = 30.dp)
                                            .height(60.dp)
                                            .width(160.dp),
                                    ) {
                                        Text(
                                            "save changes",
                                            style = MaterialTheme.typography.body1,
                                            color = AyeTheme.colors.uiBackground
                                        )
                                    }
                                }

                            }

                        }
                    }
                }
            }
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            Log.i("editprofilelogs", imageUri.toString())
        }
    }

    override fun binding(): ViewBinding {
        return ActivityEditProfileBinding.inflate(layoutInflater)
    }
}