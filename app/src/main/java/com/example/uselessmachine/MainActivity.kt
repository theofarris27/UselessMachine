package com.example.uselessmachine

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import kotlinx.coroutines.delay


lateinit var selfDestruct: Button
lateinit var lookBusy: Button
lateinit var uselessSwitch: Switch
lateinit var background: ConstraintLayout
lateinit var groupBar: ProgressBar
lateinit var groupText: TextView
private lateinit var groupBusy: Group
private lateinit var groupRest: Group


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wireWidgets()
        groupBusy.visibility = View.GONE
        uselessSwitch.setOnCheckedChangeListener { _, b ->
            if(b){
                Toast.makeText(this, "ON", Toast.LENGTH_LONG).show()
            timer()
            }
            else{
                Toast.makeText(this, "OFF", Toast.LENGTH_LONG).show()
            }
        }
        selfDestruct.text = "SELF DESTRUCT"
        selfDestruct.setOnClickListener {
            selfDestruct()
        }
        lookBusy.text = "CLICK FOR COOL THINGS"
        lookBusy.setOnClickListener {
            groupRest.visibility = View.GONE
            groupBusy.visibility = View.VISIBLE
            object : CountDownTimer(10300, 1000){
                var full = 0L
                override fun onTick(millisUntilFinished: Long) {
                    groupBar.incrementProgressBy(10)
                    full += 100L
                    if(full == 1000L){ groupText.text = "THAT DID NOTHING HAHA"
                    }
                }

                override fun onFinish() {
                    groupBusy.visibility = View.GONE
                    groupRest.visibility = View.VISIBLE
                    groupBar.progress = 0
                    groupText.text = "Loading..."
                }
            }.start()
        }
    }

private fun timer(){
    object : CountDownTimer(3000, 200){
        override fun onTick(millisUntilFinished: Long) {
        }

        override fun onFinish() {
            uselessSwitch.isChecked = false

        }
    }.start()
}

    private fun selfDestruct(){
        object : CountDownTimer(10000, 1000){
            var time = 10000L
            var duration = 500
            var isRed = false
            override fun onTick(millisUntilFinished: Long) {
                selfDestruct.text = "Seconds Left: " + millisUntilFinished / 1000
                isRed = if(time - millisUntilFinished >= duration){
                    background.setBackgroundColor(Color.WHITE)
                    false
                } else{
                    background.setBackgroundColor(Color.RED)
                    true
                }

            }
            override fun onFinish() {
            finish()
            }
        }.start()
    }

    private fun wireWidgets(){
        selfDestruct = findViewById(R.id.main_button_destruct)
        lookBusy = findViewById(R.id.main_button_lookBusy)
        uselessSwitch = findViewById(R.id.main_switch_useless)
        background = findViewById(R.id.main_background)
        groupBusy = findViewById(R.id.main_newStuff)
        groupRest = findViewById(R.id.group_rest)
        groupBar = findViewById(R.id.progressBar_main_busy)
        groupText = findViewById(R.id.textView_main_busy)
    }

}
