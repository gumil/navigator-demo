package io.github.gumil.testnavigator.target

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.zhuinden.simplestack.navigator.Navigator
import io.github.gumil.testnavigator.R
import io.github.gumil.testnavigator.common.ViewLayout
import io.github.gumil.testnavigator.target.title.TargetTitleKey
import io.github.gumil.testnavigator.utils.applyButtonBarStyle
import io.github.gumil.testnavigator.utils.findActivity
import io.github.gumil.testnavigator.utils.getColorRes
import org.jetbrains.anko.*

internal class TargetLayout(
        private val onTitlePicked: (String) -> Unit = {}
) : ViewLayout() {

    private lateinit var textView: TextView
    private lateinit var imageView: ImageView

    companion object {
        const val REQUEST_SELECT_IMAGE = 126
    }

    override fun createView(context: Context) = with(context) {
        toolbarTitle = "Target Controller Demo"
        verticalLayout {
            backgroundColor = getColorRes(R.color.pink_300)

            textView = textView {
                text = getString(R.string.default_text)
                gravity = Gravity.CENTER
                textSize = 20f
            }.lparams(matchParent, 0) {
                weight = 1f
            }


            val size = resources.getDimensionPixelSize(R.dimen.display_target_image_size)
            imageView = imageView().lparams(size, size) {
                gravity = Gravity.CENTER_HORIZONTAL
                margin = resources.getDimensionPixelSize(R.dimen.display_target_image_margin)
            }

            linearLayout {
                backgroundColor = getColorRes(android.R.color.white)

                button(getString(R.string.pick_title)) {
                    onClick {
                        Navigator.getBackstack(ctx).goTo(TargetTitleKey(onTitlePicked))
                    }
                }.applyButtonBarStyle().lparams(0, matchParent) {
                    weight = 1f
                }

                button(getString(R.string.pick_image)) {
                    onClick {
                        launchImagePicker(ctx)
                    }
                }.applyButtonBarStyle().lparams(0, matchParent) {
                    weight = 1f
                }

            }.lparams(matchParent, dip(80)) {
                gravity = Gravity.BOTTOM
            }
        }
    }

    private fun launchImagePicker(context: Context) {
        val intent = Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
        context.findActivity().startActivityForResult(
                Intent.createChooser(intent, "Select Image"),
                REQUEST_SELECT_IMAGE
        )
    }

    fun setTextView(text: String) {
        if (text.isEmpty()) {
            textView.text = view.context.getString(R.string.default_text)
        } else {
            textView.text = text
        }
    }

    fun setImageView(imageUri: Uri) {
        Picasso.with(view.context)
                .load(imageUri)
                .fit()
                .centerCrop()
                .into(imageView)
    }

}