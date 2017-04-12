package io.github.gumil.testnavigator.target

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import io.github.gumil.testnavigator.changehandler.FadeChangeHandler
import io.github.gumil.testnavigator.common.ViewKey
import io.github.gumil.testnavigator.target.TargetLayout.Companion.REQUEST_SELECT_IMAGE

internal class TargetKey(
        private var title: String = "",
        private var imageUri: Uri? = null
) : ViewKey() {

    override fun layout() = TargetLayout({ title = it })

    override fun viewChangeHandler() = FadeChangeHandler()

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            Uri.parse(parcel.readString())
    )

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<TargetKey> = object : Parcelable.Creator<TargetKey> {
            override fun createFromParcel(`in`: Parcel) = TargetKey(`in`)

            override fun newArray(size: Int) = arrayOfNulls<TargetKey>(size)
        }
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeString(imageUri.toString())
    }

    override fun onChangeStarted() {
        super.onChangeEnded()
        inititalizeLayout()
    }

    private fun inititalizeLayout() {
        (layout as? TargetLayout)?.let { target ->
            target.setTextView(title)
            imageUri?.let { target.setImageView(it) }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_SELECT_IMAGE && resultCode == Activity.RESULT_OK) {
            imageUri = data.data
            inititalizeLayout()
        }
    }
}