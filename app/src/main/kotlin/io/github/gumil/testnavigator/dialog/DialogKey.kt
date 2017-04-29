package io.github.gumil.testnavigator.dialog

import android.content.Intent
import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.URLSpan
import android.view.View
import com.zhuinden.simplestack.navigator.ViewChangeHandler
import io.github.gumil.testnavigator.MainActivity
import io.github.gumil.testnavigator.changehandler.SingleFadeChangeHandler
import io.github.gumil.testnavigator.common.ViewKey
import io.github.gumil.testnavigator.common.ViewLayout

internal data class DialogKey(
        private val tag: String = DialogKey::class.java.simpleName
) : ViewKey() {

    var changeHandler: ViewChangeHandler = SingleFadeChangeHandler()

    override fun layout(): ViewLayout {
        val details = SpannableString("A backstack library for simpler navigation between views, fragments, or whatevers.")
        details.setSpan(AbsoluteSizeSpan(16, true), 0, details.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)

        val url = "https://github.com/Zhuinden/simple-stack"
        val link = SpannableString(url)
        link.setSpan(object : URLSpan(url) {
            override fun onClick(widget: View) {
                MainActivity.instance?.
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            }
        }, 0, link.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)

        val description = SpannableStringBuilder()
        description.append(details)
        description.append("\n\n")
        description.append(link)
        return DialogLayout("Navigator", description)
    }

    override fun viewChangeHandler() = changeHandler

    constructor(parcel: Parcel) : this()

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<DialogKey> = object : Parcelable.Creator<DialogKey> {
            override fun createFromParcel(`in`: Parcel) = DialogKey(`in`)

            override fun newArray(size: Int) = arrayOfNulls<DialogKey>(size)
        }
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {}

    override fun isPreviousViewPersisted() = true
}