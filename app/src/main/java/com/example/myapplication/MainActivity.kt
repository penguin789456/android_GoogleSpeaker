package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE_INPUT=1001
    private lateinit var BTN_1:Button
    private lateinit var TV_1:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BTN_1=findViewById(R.id.button)
        TV_1=findViewById(R.id.textView)

        BTN_1.setOnClickListener{
            startSpeach()
        }
    }

    private fun startSpeach() {
        var intent=Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault())
            putExtra(RecognizerIntent.EXTRA_PROMPT,"請說話")
        }
        try {
            startActivityForResult(intent,REQUEST_CODE_INPUT)
        }catch (ex:Exception){
            Log.d("myTAG",ex.toString())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==REQUEST_CODE_INPUT){
            if (resultCode == RESULT_OK&&data!=null) {
                val result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                val spokenTEXT= result?.get(0)
                checkSpokenText(spokenTEXT.toString())
            }
        }else{
            Log.d("resultError","result ERRor")
        }
    }

    private fun checkSpokenText(s:String) {
        TV_1.text=s
    }
}