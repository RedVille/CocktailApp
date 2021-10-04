package com.amalip.cocktailapp.presentation.cocktails

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amalip.cocktailapp.databinding.RowCocktailBinding
import com.amalip.cocktailapp.domain.model.Cocktail

class CocktailAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: MutableList<Cocktail> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun addData(list: List<Cocktail>) {
        this.list = list.toMutableList()

        notifyDataSetChanged() // notifica que algo cambió
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolderItem(
        RowCocktailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as ViewHolderItem).bind(
            list[position]
        )

    override fun getItemCount() = list.size

    // bindea cada item de la lista
    class ViewHolderItem(private val binding: RowCocktailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Cocktail) {
            binding.item = data
        }
    }

}