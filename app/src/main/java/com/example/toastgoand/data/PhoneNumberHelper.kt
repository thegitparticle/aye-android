package com.example.toastgoand.data

//
//import android.app.PendingIntent
//import android.content.Context
//import android.net.Credentials
//import android.telephony.TelephonyManager
//import androidx.fragment.app.FragmentActivity
//import com.google.android.gms.auth.api.credentials.Credentials
//import com.google.android.gms.auth.api.credentials.CredentialsOptions
//import com.google.android.gms.auth.api.credentials.HintRequest
//import com.google.i18n.phonenumbers.PhoneNumberUtil
//
//fun getCountryISO(context: Context): String {
//    val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//    return telephonyManager.simCountryIso.uppercase()
//}
//
//fun getCountryCode(context: Context): Int {
//    val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//    val countryIso = telephonyManager.simCountryIso.uppercase()
//    return PhoneNumberUtil.getInstance().getCountryCodeForRegion(countryIso)
//}
//
//fun getPhoneHintIntent(activity: FragmentActivity): PendingIntent {
//    val hintRequest = HintRequest.Builder()
//        .setPhoneNumberIdentifierSupported(true)
//        .build()
//
//    val options = CredentialsOptions.Builder()
//        .forceEnableSaveDialog()
//        .build()
//
//    return Credentials.getClient(activity, options).getHintPickerIntent(hintRequest)
//}