package io.github.gumil.testnavigator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.ViewGroup
import com.zhuinden.navigator.Navigator
import com.zhuinden.simplestack.HistoryBuilder
import io.github.gumil.testnavigator.home.HomeKey
import io.github.gumil.testnavigator.common.ViewStateChanger
import org.jetbrains.anko.frameLayout

internal class MainActivity : AppCompatActivity() {

    var isAnimating = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = frameLayout {  }

        Navigator.configure()
                .setStateChanger(ViewStateChanger(this, root))
                .install(this, root, HistoryBuilder.single(HomeKey()))
    }

    override fun onBackPressed() {
        if(!Navigator.onBackPressed(this)) {
            super.onBackPressed()
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return if (!isAnimating) super.dispatchTouchEvent(ev) else isAnimating
    }
}
