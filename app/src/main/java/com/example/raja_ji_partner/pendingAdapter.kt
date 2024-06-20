package com.example.raja_ji_partner

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.raja_ji_partner.databinding.PendingitemBinding

class pendingAdapter (
    private val context: Context,
    private val listofname :MutableList<String>,
    private val listofAddress:MutableList<String>,
    private val contactnumb :MutableList<String>,
    private val companyname:MutableList<String>,
    private val email:MutableList<String>,
    private val companyimage:MutableList<String>,

):RecyclerView.Adapter<pendingAdapter.pendingOrderViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): pendingAdapter.pendingOrderViewHolder {
        val binding=PendingitemBinding.inflate(LayoutInflater.from(parent.context) ,parent,false)
        return pendingOrderViewHolder(binding)
    }



    override fun onBindViewHolder(holder: pendingAdapter.pendingOrderViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount():Int= listofname.size
   inner class pendingOrderViewHolder(private val binding: PendingitemBinding):RecyclerView.ViewHolder(binding.root) {
private var isAccepted =false
       fun bind(position: Int){
           binding.apply {
username.text=listofname[position]
               companynames.text=companyname[position]
               useraddress.text=listofAddress[position]
               contactnumber.text=contactnumb[position]
               emailforuser.text=email[position]
               val uriString =companyimage[position]
               val uri= Uri.parse(uriString)
               Glide.with(context).load(uri).into(foodImageView)
           }
       }
    }

}