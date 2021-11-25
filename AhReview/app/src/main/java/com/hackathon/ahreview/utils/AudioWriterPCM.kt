package com.hackathon.ahreview.utils

import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.ByteOrder

class AudioWriterPCM(path: String?) {
    var mPath: String? = null
    var filename: String? = null
    var speechFile: FileOutputStream? = null

    init {
        mPath = path
    }

    fun open(sessionId: String) {
        val directory = File(mPath)
        if (!directory.exists()) {
            directory.mkdirs()
        }
        filename = directory.toString() + "/" + sessionId + ".pcm"
        try {
            speechFile = FileOutputStream(File(filename))
        } catch (e: FileNotFoundException) {
            System.err.println("Can't open file : $filename")
            speechFile = null
        }
    }

    fun close() {
        if (speechFile == null) return
        try {
            speechFile!!.close()
        } catch (e: IOException) {
            System.err.println("Can't close file : $filename")
        }
    }

    fun write(data: ShortArray) {
        if (speechFile == null) return
        val buffer: ByteBuffer = ByteBuffer.allocate(data.size * 2)
        buffer.order(ByteOrder.LITTLE_ENDIAN)
        for (i in data.indices) {
            buffer.putShort(data[i])
        }
        buffer.flip()
        try {
            speechFile!!.write(buffer.array())
        } catch (e: IOException) {
            System.err.println("Can't write file : $filename")
        }
    }
}