package com.hsh.sunnyweather.ui.place

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.hsh.sunnyweather.R
import com.hsh.sunnyweather.logic.Repository
import com.hsh.sunnyweather.logic.model.Place
import com.hsh.sunnyweather.ui.weather.WeatherActivity


class PlaceAdapter(private val fragment: Fragment, private val placeList: List<Place>) :
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val placeName: TextView = view.findViewById(R.id.placeName)
        val placeAddress: TextView = view.findViewById(R.id.placeAddress)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.place_item,
            parent, false)
        val holder=ViewHolder(view)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = placeList[position]
        holder.placeName.text = place.name
        holder.placeAddress.text = place.address

        holder.itemView.setOnClickListener {
            val position=holder.adapterPosition
            val place=placeList[position]

            if(fragment.activity is WeatherActivity){
                val activity_ = fragment.activity as WeatherActivity
                activity_.binding.drawerLayout.closeDrawers()
                activity_.viewModel.locationLat=place.location.lat
                activity_.viewModel.locationLng=place.location.lng
                activity_.viewModel.placeName=place.name
                activity_.refreshWeather()
            }else{
                val intent= Intent(fragment.context,WeatherActivity::class.java).apply {
                    putExtra("location_lng",place.location.lng)
                    putExtra("location_lat",place.location.lat)
                    putExtra("place_name",place.name)
                }
                (fragment as PlaceFragment).viewModel.savePlace(place)
                fragment.startActivity(intent)
                fragment.activity?.finish()

            }
        }
    }
    override fun getItemCount() = placeList.size
}