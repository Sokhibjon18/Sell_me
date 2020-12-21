package uz.triples.sellme

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            window.statusBarColor = getColor(R.color.white)
        }


        object : CountDownTimer(2000, 2000) {
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                finish()
                startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            }
        }.start()
    }
}