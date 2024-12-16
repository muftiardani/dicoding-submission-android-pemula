package com.project.growapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.growapp.R
import com.project.growapp.data.Organization
import com.project.growapp.ui.DetailActivity

class ListOrganizationAdapter(
    private val listOrganization: ArrayList<Organization>
) : RecyclerView.Adapter<ListOrganizationAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row_organization, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listOrganization.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val organization = listOrganization[position]
        holder.bind(organization)
        holder.itemView.setOnClickListener {
            navigateToDetail(holder.itemView.context, organization)
        }
    }

    private fun navigateToDetail(context: Context, organization: Organization) {
        val intent = Intent(context, DetailActivity::class.java).apply {
            putExtra(DetailActivity.KEY_ORGANIZATION, organization)
        }
        context.startActivity(intent)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        private val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        private val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)

        fun bind(organization: Organization) {
            with(organization) {
                imgPhoto.setImageResource(photo)
                tvName.text = name
                tvDescription.text = description
            }
        }
    }
}