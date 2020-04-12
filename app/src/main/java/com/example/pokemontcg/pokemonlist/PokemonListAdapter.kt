package com.example.pokemontcg.pokemonlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemontcg.R
import com.example.pokemontcg.model.PokemonCard
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_card.*

class PokemonListAdapter : RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {

    private val pokemonCards = mutableListOf<PokemonCard>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false))

    override fun getItemCount(): Int = pokemonCards.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(pokemonCards[position])
    }

    fun updateData(newPokemonCards: MutableList<PokemonCard>) {
        pokemonCards.clear()
        pokemonCards.addAll(newPokemonCards)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), LayoutContainer {

        override val containerView: View?
            get() = itemView

        fun bindItem(pokemonCard: PokemonCard) {
            Glide.with(containerView!!)
                .load(pokemonCard.image)
                .into(ivCardLogo)

            tvCardName.text = pokemonCard.name
            tvCardRarity.text = pokemonCard.rarity

            containerView?.setOnClickListener { view ->
                val action = PokemonListFragmentDirections
                    .actionPokemonListFragmentToPokemonCardDetailFragment2(
                        pokemonCard, pokemonCard.name!!
                    )
                view.findNavController().navigate(action)
            }
        }
    }
}