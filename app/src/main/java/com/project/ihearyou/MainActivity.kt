package com.project.ihearyou

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()





        /*if (isFirstLaunch) {

            splashScreen.setOnExitAnimationListener { splashScreenView ->
                val rotation = ObjectAnimator.ofFloat(
                    splashScreenView.iconView,
                    View.ROTATION,
                    0f,
                    360f
                )
                rotation.duration = 1000
                rotation.repeatCount = ObjectAnimator.INFINITE
                rotation.interpolator = LinearInterpolator()
                rotation.start()

                // Add fade out animation after some delay
                Handler(Looper.getMainLooper()).postDelayed({
                    val alpha = ObjectAnimator.ofFloat(
                        splashScreenView.view,
                        View.ALPHA,
                        1f,
                        0f
                    )
                    alpha.duration = 500L
                    alpha.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            splashScreenView.remove()
                        }
                    })
                    alpha.start()
                }, 3000) // Show splash for 3 seconds
            }
        } else {
            // Skip animation on configuration changes
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                splashScreenView.remove()
            }
        }*/


        setContentView(R.layout.activity_main)







    }



}