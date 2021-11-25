package com.hackathon.ahreview.utils

import android.media.MediaPlayer
import android.os.Build
import android.os.Environment
import android.os.FileUtils
import android.os.StrictMode
import androidx.annotation.RequiresApi
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.nio.file.Paths


class NaverAPITTS {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getTTS(args: String) {
        val clientId = "2g52oohbbm" //애플리케이션 클라이언트 아이디값";
        val clientSecret = "9Fbp185GtaebnVMb3YgMJUHru0efAOXpKvLMYC7y" //애플리케이션 클라이언트 시크릿값";
        var requestContentType = "application/x-www-form-urlencoded";

        try {
            val SDK_INT = Build.VERSION.SDK_INT
            if (SDK_INT > 8) {
                val policy = StrictMode.ThreadPolicy.Builder()
                    .permitAll().build()
                StrictMode.setThreadPolicy(policy)
            }

            val text = URLEncoder.encode(args, "UTF-8")
            val apiURL = "https://naveropenapi.apigw.ntruss.com/tts-premium/v1/tts"

            val url = URL(apiURL)
            var con = url.openConnection() as HttpURLConnection

            con.requestMethod = "POST"
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            con.setRequestProperty("Content-Type", requestContentType);


            val postParams = "speaker=dara&text=${text}"
            con.doOutput = true
            con.doInput = true

            val wr = DataOutputStream(con.outputStream)
            wr.writeBytes(postParams)
            wr.flush()
            wr.close()
            val responseCode = con.responseCode
            var buffer: BufferedReader

            if (responseCode == 200) {
                var inputStream = con.inputStream


                var read = 0
                val bytes = ByteArray(1024)
                val dir =
                    File(Environment.getExternalStorageDirectory().absolutePath + "/", "Naver")

                if (!dir.exists()) {
                    dir.mkdirs()
                }

                val tempname = "naverttstemp" //하나의 파일명으로 덮어쓰기 하자.

                val audioSavePath: String =
                    Environment.getExternalStorageDirectory().absolutePath + "/" + "Naver/" + tempname + ".mp3"

                val f =
                    File(audioSavePath)
                f.createNewFile()

                val outputStream: FileOutputStream = FileOutputStream(f)
                while (inputStream.read(bytes).also { read = it } != -1) {
                    outputStream.write(bytes, 0, read)
                }
                inputStream.close()

                val audioPlay = MediaPlayer()
                audioPlay.setDataSource(audioSavePath)
                audioPlay.prepare()
                audioPlay.start()

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}