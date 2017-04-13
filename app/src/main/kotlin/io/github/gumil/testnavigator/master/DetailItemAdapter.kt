package io.github.gumil.testnavigator.master

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

internal class DetailItemAdapter(
        val onRowSelected: (Int) -> Unit = {}
) : RecyclerView.Adapter<DetailItemAdapter.ViewHolder>() {

    private val data = DetailItemModel.values()

    private var selectedIndex = 0

    private val onRowSelectedInternal: (Int) -> Unit = {
        selectedIndex = it
        onRowSelected(it)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = with(parent.context) {
        val ctx = this
        ViewHolder(DetailRowLayout(onRowSelectedInternal).apply {
            inflate(ctx)
        })
    }

    override fun getItemCount() = data.size

    internal inner class ViewHolder(
            private val detailRowLayout: DetailRowLayout
    ) : RecyclerView.ViewHolder(detailRowLayout.view) {

        fun bind(itemModel: DetailItemModel, position: Int) {
            detailRowLayout.bind(itemModel, position, selectedIndex)
        }

    }
}