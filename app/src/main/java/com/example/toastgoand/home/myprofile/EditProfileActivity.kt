package com.example.toastgoand.home.myprofile

import android.content.Intent
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
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import retrofit2.http.Part
import android.os.FileUtils
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


class EditProfileActivity : BaseActivity() {

    private lateinit var binding: ActivityEditProfileBinding

    private val pickImage = 100
    private var imageUri: Uri? = null
    private var imagePath: String? = ""
    private var profileupdateid: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityEditProfileBinding

        fun onBackPressedHere() {
            onBackPressed()
        }

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
                                                try {

                                                    fun createPartFromString(param: String): RequestBody {
                                                        return RequestBody.create("multipart/form-data".toMediaTypeOrNull(), param)
                                                    }



                                                    fun prepareFilePart(partName: String, fileUri: Uri): MultipartBody.Part? {

                                                        Log.i(
                                                            "editprofilelogsprepfile",
                                                            "thos func is called"
                                                        )

                                                        val file: File = fileUri.toFile()

                                                        Log.i(
                                                            "editprofilelogsprepfile",
                                                            "file var is done"
                                                        )

                                                        contentResolver.getType(
                                                            fileUri
                                                        )?.let {
                                                            Log.i(
                                                                "editprofilelogsmimetype",
                                                                it
                                                            )
                                                        }

                                                        val requestFile: RequestBody = file
                                                            .asRequestBody(
                                                               "png"
                                                                    .toMediaTypeOrNull()
                                                            )

                                                        Log.i(
                                                            "editprofilelogsprepfile",
                                                            "request file body is done \"file://$imageUri\""
                                                        )

                                                        return MultipartBody.Part.createFormData(partName, "uploadandroid.png", requestFile);
                                                    }

                                                    val bio: RequestBody =
                                                        createPartFromString("")

                                                    var redoneUrl = imageUri.toString().drop(10)

                                                    Log.i(
                                                        "editprofilelogs",
                                                        "functions setup done"
                                                    )

                                                    Log.i(
                                                        "editprofilelogsuri",
                                                        "file:///$redoneUrl"
                                                    )

                                                    imagePath?.let {
                                                        Log.i(
                                                            "editprofilelogspath",
                                                            it
                                                        )
                                                    }

                                                    val image: MultipartBody.Part? =
                                                        prepareFilePart("image", "file://$imageUri".toUri())

                                                    Log.i(
                                                        "editprofilelogs",
                                                        "image part making done"
                                                    )

                                                    Log.i(
                                                        "editprofilelogs",
                                                        "composable scope payload setup done"
                                                    )

//                                                    profileupdateid?.let {

                                                            try {
                                                                EditProfileApi.retrofitService.setNewProfile(
                                                                    profileupdateid = "84",
                                                                    bio = createPartFromString(""),
                                                                    image = image
                                                                )
                                                                Log.i(
                                                                    "editprofilelogs",
                                                                    "kinda worked"
                                                                )
                                                            } catch (ex: Exception) {

                                                                Log.i(
                                                                    "editprofilelogs222",
                                                                    ex.toString() + "exception caught"
                                                                )
                                                            }

//                                                    }
                                                } catch (e: Exception) {
                                                    Log.i(
                                                        "editprofilelogs333",
                                                        e.toString() + "exception"
                                                    )
                                                }
                                            }

//                                        context.startActivity(
//                                            Intent(
//                                                context,
//                                                StartClanActivity::class.java
//                                            ).apply { })
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