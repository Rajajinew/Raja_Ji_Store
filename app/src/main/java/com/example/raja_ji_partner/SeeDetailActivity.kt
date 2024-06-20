package com.example.raja_ji_partner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.raja_ji_partner.databinding.ActivitySeeDetailBinding
import com.example.raja_ji_partner.model.allmenu
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SeeDetailActivity : AppCompatActivity() {
    private lateinit var  databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private  var menuItems :ArrayList<allmenu> = ArrayList()
    private val binding:ActivitySeeDetailBinding by lazy {
        ActivitySeeDetailBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        databaseReference=FirebaseDatabase.getInstance().reference
        retrivemenuItem()
        binding.backimage.setOnClickListener {
            finish()
        }
    }

    private fun retrivemenuItem() {
        database=FirebaseDatabase.getInstance()
        val foodref:DatabaseReference=database.reference.child("menu")
        // fetch data base from database
        foodref.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot){
                // data existing data before populating
                menuItems.clear()
                //loop for through each item
                for(foodSnspdhot in snapshot.children){
                    val menuItem=foodSnspdhot.getValue(allmenu::class.java)
                    menuItem?.let {
                        menuItems.add(it)
                    }
                }
                setAdapter()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("DatabaseError" , "Error: ${error.message}")
            }


        })
    }

    private fun setAdapter() {
        val adapter=MenuAdapter(this@SeeDetailActivity,menuItems,databaseReference)
        binding.menurecyclerview.layoutManager=LinearLayoutManager(this)
        binding.menurecyclerview.adapter=adapter
    }
}
