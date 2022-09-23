package kr.tutorials.timerexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var countDownTimer : CountDownTimer? = null
    private var timerDuration : Long = 60000
    private var pauseOffset : Long = 0
    private var tvTimer : TextView? = null
    private var btnStart : Button? = null
    private var btnPause : Button? = null
    private var btnReset : Button? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTimer = findViewById(R.id.tv_timer)
        tvTimer?.text="${(timerDuration/1000).toString()}"
        btnStart = findViewById(R.id.btn_Start)
        btnPause = findViewById(R.id.btn_Pause)
        btnReset = findViewById(R.id.btn_Reset)


        btnStart?.setOnClickListener{
            timerStart(pauseOffset)
        }
        btnPause?.setOnClickListener{
            timerPause()
        }
        btnReset?.setOnClickListener{
            timerEnd()
        }


    }

    private fun timerStart(pauseOffsetL: Long){
        Toast.makeText(this, "TimerStarted",Toast.LENGTH_SHORT).show()
        countDownTimer = object : CountDownTimer(timerDuration - pauseOffsetL, 1000){
            override fun onTick(millisUntilFinished: Long) {

                pauseOffset = timerDuration - millisUntilFinished
                tvTimer?.text=(millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity, "Timer is finished", Toast.LENGTH_SHORT).show()
            }
        }.start()

    }

    private fun timerPause(){
        Toast.makeText(this, "Paused",Toast.LENGTH_SHORT).show()
        if (countDownTimer !=null){
            countDownTimer!!.cancel()
        }

    }
    private fun timerEnd(){
        Toast.makeText(this, "TimerReset",Toast.LENGTH_SHORT).show()
        if(countDownTimer !=null){
            countDownTimer!!.cancel()
            tvTimer?.text = "${(timerDuration/1000).toString()}"
            countDownTimer = null
            pauseOffset = 0
        }
    }
}