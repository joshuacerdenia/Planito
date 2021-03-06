package com.cerdenia.android.planito.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

object CalendarPermissions {

    private val PERMISSIONS = arrayOf(
        Manifest.permission.READ_CALENDAR,
        Manifest.permission.WRITE_CALENDAR
    )

    private var launcher: ActivityResultLauncher<Array<out String>>? = null

    fun setResultWhenGranted(fragment: Fragment, callback: () -> Unit) {
        val contract = ActivityResultContracts.RequestMultiplePermissions()
        launcher = fragment.registerForActivityResult(contract) { permissions ->
            val isGranted = permissions.all { it.value == true }
            if (isGranted) callback()
        }
    }

    fun isAlreadyGranted(context: Context?): Boolean {
        var isPermitted = false
        for (permission in PERMISSIONS) {
            val result = context?.let { ContextCompat.checkSelfPermission(it, permission) }
            isPermitted = result == PackageManager.PERMISSION_GRANTED
        }

        return isPermitted
    }

    fun request() {
        launcher?.launch(PERMISSIONS)
    }

    fun cleanup() {
        launcher = null
    }
}