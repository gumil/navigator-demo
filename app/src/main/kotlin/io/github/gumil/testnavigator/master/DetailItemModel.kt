package io.github.gumil.testnavigator.master

import io.github.gumil.testnavigator.R

internal enum class DetailItemModel(
        val title: String,
        val detail: String,
        val backgroundColor: Int
) {
    ONE("Item 1", "This is a quick demo of master/detail flow using Conductor. In portrait mode you'll see a standard list. In landscape, you'll see a two-pane layout.", R.color.green_300),
    TWO("Item 2", "This is another item.", R.color.cyan_300),
    THREE("Item 3", "Wow, a 3rd item!", R.color.deep_purple_300);

}