package io.github.gumil.testnavigator

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.MotionEvent
import com.zhuinden.simplestack.HistoryBuilder
import com.zhuinden.simplestack.StateChanger
import com.zhuinden.simplestack.navigator.Navigator
import io.github.gumil.testnavigator.common.ParentChildStateChanger
import io.github.gumil.testnavigator.common.ViewKey
import io.github.gumil.testnavigator.home.HomeKey
import org.jetbrains.anko.frameLayout

internal class MainActivity : AppCompatActivity() {

    var isAnimating = false
    lateinit var stateChanger: ParentChildStateChanger

    companion object {
        var instance: MainActivity? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        instance = this
        super.onCreate(savedInstanceState)
        val root = frameLayout { }

        stateChanger = ParentChildStateChanger(this, root)
        Navigator.configure()
                .setStateChanger(stateChanger)
                .install(this, root, HistoryBuilder.single(HomeKey()))
    }

    override fun onBackPressed() {
        if (!Navigator.getBackstack(this)
                .top<ViewKey>()
                .onBackPressed()) {
            if (!Navigator.onBackPressed(this)) {
                super.onBackPressed()
            }
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

    fun setChildStateChanger(stateChanger: StateChanger) {
        this.stateChanger.childStateChanger = stateChanger
    }

    fun removeChildStateChanger() {
        stateChanger.childStateChanger = null
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.clear()
        val menuRes = Navigator.getBackstack(this)
                .top<ViewKey>()
                .onCreateOptionsMenu()
        menuInflater.inflate(menuRes, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val onOptionsItemSelected = Navigator.getBackstack(this)
                .top<ViewKey>()
                .onOptionsItemSelected(item)
        if (!onOptionsItemSelected) {
            return super.onOptionsItemSelected(item)
        } else {
            return onOptionsItemSelected
        }
    }
}
