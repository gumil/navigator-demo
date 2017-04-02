package io.github.gumil.testnavigator.home

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

internal class HomeAdapter(
        val items: Array<HomeDemoModel> = HomeDemoModel.values()
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    var onRowClicked: ((HomeDemoModel, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(HomeRowLayout().apply {
            inflate(parent.context)
        })
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    internal inner class ViewHolder(val homeRow: HomeRowLayout) :
            RecyclerView.ViewHolder(homeRow.view) {

        fun bind(position: Int, item: HomeDemoModel) {
            homeRow.bind(position, item)
            homeRow.onRowClicked = onRowClicked
        }

    }
}