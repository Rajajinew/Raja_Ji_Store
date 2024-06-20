package com.example.raja_ji_partner

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.raja_ji_partner.databinding.ItemItemBinding
import com.example.raja_ji_partner.model.allmenu
import com.google.firebase.database.DatabaseReference

class MenuAdapter(
    private val context: Context,
    private val menuList: ArrayList<allmenu>,
    databaseReference: DatabaseReference

): RecyclerView.Adapter<MenuAdapter.AddItemViewHolder>() {

    private val itemQuantities = IntArray(menuList.size) {
        1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddItemViewHolder {
        val binding = ItemItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddItemViewHolder(binding)
    }


    override fun onBindViewHolder(holder: AddItemViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = menuList.size
    inner class AddItemViewHolder(private val binding: ItemItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantities[position]
                val menuItem =menuList[position]
                val uriString=menuItem.image
                val uri= Uri.parse(uriString)
                foodname.text = menuItem.companyname
                foodprice.text = menuItem.Price
Glide.with(context).load(uri).into(foodImageView)

                quantitytextview.text = quantity.toString()
                minusbutton.setOnClickListener {
                    decreaseQuantites(position,binding)
                }
                plusbutton.setOnClickListener {
                    increaseQuantites(position,binding)
                }
                deletebutton.setOnClickListener {
                    deleteQuantites(position,binding)
                }
            }
        }

    }

    private fun decreaseQuantites(position: Int, binding: ItemItemBinding) {
        if (itemQuantities[position] > 1) {
            itemQuantities[position]--
            binding.quantitytextview.text = itemQuantities[position].toString()
        }
    }

    private fun increaseQuantites(position: Int, binding: ItemItemBinding) {
        if (itemQuantities[position] < 10) {
            itemQuantities[position]++
            binding.quantitytextview.text = itemQuantities[position].toString()
        }
    }

    private fun deleteQuantites(position: Int, binding: ItemItemBinding) {
        menuList.removeAt(position)
        menuList.removeAt(position)
        menuList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, menuList.size)
    }

}
