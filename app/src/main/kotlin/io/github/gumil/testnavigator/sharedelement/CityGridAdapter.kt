package io.github.gumil.testnavigator.sharedelement

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

internal class CityGridAdapter : RecyclerView.Adapter<CityGridAdapter.ViewHolder>() {

    private val CITY_MODELS = CityModel.values()

    var onRowClicked: ((CityModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CityGridRowLayout().apply {
            inflate(parent.context)
        })
    }

    override fun getItemCount() = CITY_MODELS.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(CITY_MODELS[position])
    }

    internal inner class ViewHolder(
            private val cityRow: CityGridRowLayout
    ) : RecyclerView.ViewHolder(cityRow.view) {


        fun bind(item: CityModel) {
            cityRow.onRowClicked = {
                onRowClicked?.invoke(item)
            }
            cityRow.bind(item)
        }

    }

}