package com.example.raja_ji_partner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.raja_ji_partner.databinding.ActivityPendingBinding
import com.example.raja_ji_partner.model.Orderdetail
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class pendingActivity : AppCompatActivity() {
    private var listofname: MutableList<String> = mutableListOf()
    private var listofaddress: MutableList<String> = mutableListOf()
    private var contactno: MutableList<String> = mutableListOf()
    private var email: MutableList<String> = mutableListOf()
    private var companyname:MutableList<String> = mutableListOf()
    private var companyimages:MutableList<String> = mutableListOf()
    private var listOrderItem: MutableList<Orderdetail> = mutableListOf()
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseOrderdetail: DatabaseReference
    private lateinit var binding: ActivityPendingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPendingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // intialize firebase
        database = FirebaseDatabase.getInstance()
        databaseOrderdetail = database.reference.child("Orderdetail")
        getOrderdetail()
    }
    private fun getOrderdetail() {
        //retrive data rom firebase
        databaseOrderdetail.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (orderSnapshot in snapshot.children) {
                    val orderDetaill = orderSnapshot.getValue(Orderdetail::class.java)
                    orderDetaill?.let {
                        listOrderItem.add(it)
                    }
                }
                addDatatolistofrecyclerview()
            }

            override fun onCancelled(error: DatabaseError) {


            }

        })

    }

    private fun addDatatolistofrecyclerview() {
        for(orderItem  in listOrderItem) {
            // add data
            orderItem.username?.let { listofname.add(it) }
            orderItem.useradrees?.let { listofaddress.add(it) }
            orderItem.usercontactno?.let { contactno.add(it) }
            orderItem.companyname?.let { companyname.add(it) }
            orderItem.useremail?.let { email.add(it) }
            orderItem.companyimage?.filterNot { it.isEmpty() }?.forEach { companyimages.add(it) }
        }
        setAdapter()
    }

    private fun setAdapter() {
binding.pendingRecyclerView.layoutManager=LinearLayoutManager(this)
        val adapter =pendingAdapter(this ,listofname,listofaddress,contactno,companyname,email,companyimages)
        binding.pendingRecyclerView.adapter=adapter
    }
}


