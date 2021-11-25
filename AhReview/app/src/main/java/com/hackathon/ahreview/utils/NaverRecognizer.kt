package com.hackathon.ahreview.utils

import android.content.Context
import android.os.Handler
import android.os.Message

import android.util.Log
import androidx.annotation.WorkerThread
import com.hackathon.ahreview.R
import com.naver.speech.clientapi.*
import com.naver.speech.clientapi.SpeechConfig.EndPointDetectType

import com.naver.speech.clientapi.SpeechConfig;
import com.naver.speech.clientapi.SpeechRecognitionException;
import com.naver.speech.clientapi.SpeechRecognitionListener;
import com.naver.speech.clientapi.SpeechRecognitionResult;
import com.naver.speech.clientapi.SpeechRecognizer;
import com.naver.speech.clientapi.SpeechConfig.LanguageType

class NaverRecognizer(context: Context?, handler: Handler, clientId: String?) : SpeechRecognitionListener {

    private val TAG = NaverRecognizer::class.java.simpleName

    var mHandler: Handler = handler
    var mRecognizer: SpeechRecognizer? = null

    init {
        try {
            mRecognizer = SpeechRecognizer(context, clientId)

        } catch (e: SpeechRecognitionException){
            e.printStackTrace()
        }
        mRecognizer!!.setSpeechRecognitionListener(this)
    }

    fun recognize() {
        try {
            mRecognizer!!.recognize(
                SpeechConfig(
                    LanguageType.KOREAN,
                    EndPointDetectType.AUTO
                )
            )
        } catch (e: SpeechRecognitionException) {
            e.printStackTrace()
        }
    }

    fun getSpeechRecognizer(): SpeechRecognizer? {
        return mRecognizer
    }

    @WorkerThread
    override fun onInactive() {
        Log.d(TAG, "Event occurred : Inactive")
        val msg: Message = Message.obtain(mHandler, R.id.clientInactive)
        msg.sendToTarget()
    }

    @WorkerThread
    override fun onReady() {
        Log.d(TAG, "Event occurred : Ready")
        val msg: Message = Message.obtain(mHandler, R.id.clientReady)
        msg.sendToTarget()
    }

    @WorkerThread
    override fun onRecord(speech: ShortArray?) {
        Log.d(TAG, "Event occurred : Record")
        val msg = Message.obtain(mHandler, R.id.audioRecording, speech)
        msg.sendToTarget()
    }

    @WorkerThread
    override fun onPartialResult(partialResult: String?) {
        Log.d(TAG, "Partial Result!! (" + partialResult.toString() + ")")
        val msg = Message.obtain(mHandler, R.id.partialResult, partialResult)
        msg.sendToTarget()
    }

    @WorkerThread
    override fun onEndPointDetected() {
        Log.d(TAG, "Event occurred : EndPointDetected");
    }

    @WorkerThread
    override fun onResult(finalResult: SpeechRecognitionResult?) {
        Log.d(TAG, "Final Result!! (" + finalResult!!.results[0].toString() + ")")
        val msg = Message.obtain(mHandler, R.id.finalResult, finalResult)
        msg.sendToTarget()
    }

    @WorkerThread
    override fun onError(errorCode: Int) {
        Log.d(TAG, "Error!! ($errorCode)")
        val msg = Message.obtain(mHandler, R.id.recognitionError, errorCode)
        msg.sendToTarget()
    }

    @WorkerThread
    override fun onEndPointDetectTypeSelected(epdType: SpeechConfig.EndPointDetectType?) {
        Log.d(TAG, "EndPointDetectType is selected!! (" + epdType!!.toInteger().toString() + ")")
        val msg = Message.obtain(mHandler, R.id.endPointDetectTypeSelected, epdType)
        msg.sendToTarget()
    }
}