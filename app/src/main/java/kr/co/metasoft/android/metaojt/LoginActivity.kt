package kr.co.metasoft.android.metaojt

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sdsmdg.harjot.rotatingtext.models.Rotatable

import android.view.View

import com.sdsmdg.harjot.rotatingtext.RotatingTextWrapper




class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val rotatingTextWrapper = findViewById<View>(R.id.custom_switcher) as RotatingTextWrapper
        rotatingTextWrapper.size = 20

        val rotatable = Rotatable(Color.parseColor("#FF8C00"), 1000, "Word", "Word01", "Word02")
        rotatable.size = 35f
        rotatable.animationDuration = 500

        rotatingTextWrapper.setContent("This is ?", rotatable)
    }
}