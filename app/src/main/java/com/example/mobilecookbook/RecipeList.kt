package com.example.mobilecookbook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RecipeList(private val RecipeData: List<RecipeData>): RecyclerView.Adapter<RecipeList.RecipeListHolder>() {
    class RecipeListHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nazwa = itemView.findViewById<TextView>(R.id.nazwa)
        val opis = itemView.findViewById<TextView>(R.id.opis)
        val ocena = itemView.findViewById<RatingBar>(R.id.ocena)
        val hr = itemView.findViewById<View>(R.id.hr)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recipe_list, parent, false)

        return RecipeListHolder(itemView)
    }

    override fun getItemCount(): Int = RecipeData.size

    override fun onBindViewHolder(holder: RecipeListHolder, position: Int) {
        val curentItem = RecipeData[position]

        holder.nazwa.text = position.toString()
        holder.opis.text = curentItem.opis
        holder.ocena.rating = curentItem.ocena

        if (position == RecipeData.size-1) {
            holder.hr.visibility = View.INVISIBLE
        }
    }
}