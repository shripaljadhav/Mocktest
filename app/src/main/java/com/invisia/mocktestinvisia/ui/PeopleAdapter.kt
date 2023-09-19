package com.invisia.mocktestinvisia.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.invisia.mocktestinvisia.R
import com.invisia.mocktestinvisia.db.PersonWithGuests

class PeopleAdapter : ListAdapter<PersonWithGuests, PeopleAdapter.PersonViewHolder>(DiffCallback()) {

    // Called when RecyclerView needs a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_person, parent, false)
        return PersonViewHolder(view)
    }

    // Called to bind data to a ViewHolder
    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val personWithGuests = getItem(position)
        holder.bind(personWithGuests)
    }

    inner class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val typeTextView: TextView = itemView.findViewById(R.id.typeTextView)
        private val adultsTextView: TextView = itemView.findViewById(R.id.adultsTextView)
        private val childrenTextView: TextView = itemView.findViewById(R.id.childrenTextView)

        // Binds data to the ViewHolder
        fun bind(personWithGuests: PersonWithGuests) {
            val person = personWithGuests.person
            val guests = personWithGuests.guests

            nameTextView.text = "Name: ${person.name}"
            typeTextView.text = "Type: ${person.type}"

            // Calculate the total sum of children's ages for all guests
            val totalChildrenSum = guests.sumBy { guest ->
                guest.children.sum()
            }

            // Set the text for adults and children
            adultsTextView.text = "Adults: ${guests.sumBy { it.adults }}"
            childrenTextView.text = "Children: $totalChildrenSum"
        }
    }

    // DiffCallback for efficient updates in the RecyclerView
    private class DiffCallback : DiffUtil.ItemCallback<PersonWithGuests>() {
        override fun areItemsTheSame(oldItem: PersonWithGuests, newItem: PersonWithGuests): Boolean {
            return oldItem.person.id == newItem.person.id
        }

        override fun areContentsTheSame(oldItem: PersonWithGuests, newItem: PersonWithGuests): Boolean {
            return oldItem == newItem
        }
    }
}
