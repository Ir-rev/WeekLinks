package com.example.weeklinks

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val weekDi = WeekDi()

        val buttonGet = findViewById<Button>(R.id.button_get)
        val buttonClear = findViewById<Button>(R.id.button_clear)

        var testClass: TestClass? = null

        buttonGet.setOnClickListener {
            Log.d("checkResult", "onCreate: $testClass")
            testClass = weekDi.getInstanceOrCreate(TestClass::class.java)
            Log.d("checkResult", "onCreate: $testClass")
        }

        buttonClear.setOnClickListener {
            testClass = null
            System.gc()
            Runtime.getRuntime().gc()
            Log.d("checkResult", "onCreate: GS call")
        }

    }
}