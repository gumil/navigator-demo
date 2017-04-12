package io.github.gumil.testnavigator

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import com.zhuinden.simplestack.HistoryBuilder
import com.zhuinden.simplestack.navigator.Navigator
import io.github.gumil.testnavigator.common.ViewKey
import io.github.gumil.testnavigator.common.ViewStateChanger
import io.github.gumil.testnavigator.home.HomeKey
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        Navigator.getBackstack(this)
                .top<ViewKey>()
                .onActivityResult(requestCode, resultCode, data)
    }
}
