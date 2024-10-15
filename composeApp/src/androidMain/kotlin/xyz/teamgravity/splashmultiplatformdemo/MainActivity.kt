package xyz.teamgravity.splashmultiplatformdemo

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class MainActivity : ComponentActivity() {

    private var ready: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        super.onCreate(savedInstanceState)
        countdown()
        splash(splash)
        setContent {
            App()
        }
    }

    private fun countdown() {
        lifecycleScope.launch {
            delay(3.seconds)
            ready = true
        }
    }

    private fun splash(splash: SplashScreen) {
        splash.setKeepOnScreenCondition { !ready }
        splash.setOnExitAnimationListener { screen ->
            val scaleX = ObjectAnimator.ofFloat(screen.iconView, View.SCALE_X, 0.4F, 0.0F)
            scaleX.interpolator = OvershootInterpolator()
            scaleX.duration = 500L
            scaleX.doOnEnd { screen.remove() }
            scaleX.start()

            val scaleY = ObjectAnimator.ofFloat(screen.iconView, View.SCALE_Y, 0.4F, 0.0F)
            scaleY.interpolator = OvershootInterpolator()
            scaleY.duration = 500L
            scaleY.doOnEnd { screen.remove() }
            scaleY.start()
        }
    }
}