package com.example.pokemontcg.setlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemontcg.R
import com.example.pokemontcg.model.PokemonSet
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_set.*

class SetListAdapter : RecyclerView.Adapter<SetListAdapter.ViewHolder>() {

    private val pokemonSets = mutableListOf<PokemonSet>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_set, parent, false))


    override fun getItemCount(): Int = pokemonSets.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(pokemonSets[position])
    }

    fun updateData(newPokemonSet: MutableList<PokemonSet>) {
        pokemonSets.clear()
        pokemonSets.addAll(newPokemonSet)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), LayoutContainer {

        override val containerView: View?
            get() = itemView

        fun bindItem(item: PokemonSet) {
            Glide.with(containerView!!)
                .load(item.logo)
                .into(ivSetLogo)

            tvSetName.text = item.name
            containerView?.setOnClickListener { view ->
                val action =
                    SetListFragmentDirections.actionSetListFragmentToPokemonListFragment2(item.name)
                view.findNavController().navigate(action)
            }
        }
    }
}