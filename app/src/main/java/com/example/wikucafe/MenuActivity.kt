package com.example.wikucafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wikucafe.Adapter.MenuAdapter
import com.example.wikucafe.Add.AddMenuActivity
import com.example.wikucafe.Data.dataMenu
import com.example.wikucafe.databinding.ActivityMenuBinding
import com.google.firebase.database.*

class MenuActivity : AppCompatActivity() {

    private lateinit var menumakananRv: RecyclerView
    private lateinit var menuminumanRv : RecyclerView
    private lateinit var binding : ActivityMenuBinding
    private lateinit var makananList : ArrayList<dataMenu>
    private lateinit var minumanList : ArrayList<dataMenu>
    private lateinit var dbref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        menumakananRv = binding.rvTampilMknan
        menumakananRv.layoutManager = LinearLayoutManager(this)
        menumakananRv.setHasFixedSize(true)

        menuminumanRv = binding.rvTampilMinmn
        menuminumanRv.layoutManager = LinearLayoutManager(this)
        menuminumanRv.setHasFixedSize(true)

        makananList = arrayListOf<dataMenu>()
        minumanList = arrayListOf<dataMenu>()

        getMenuMakananData()
        getMenuMinumanData()

        binding.backbtn.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }

        binding.fabbtnMenu.setOnClickListener{
            startActivity(Intent(this, AddMenuActivity::class.java))
        }
    }

    private fun getMenuMinumanData() {
//        menuminumanRv.visibility = View.GONE
        dbref = FirebaseDatabase.getInstance().getReference("menu").child("minuman")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("Menu Activity","Data Berhasil: $snapshot")
                minumanList.clear()
                if (snapshot.exists()){
                    for (menuSnap in snapshot.children){
                        val minumanItem = menuSnap.getValue(dataMenu::class.java)
                        minumanList.add(minumanItem!!)
                    }
                    val mAdapter = MenuAdapter(minumanList)
                    menuminumanRv.adapter = mAdapter
                } else {
                    Log.d("Menu Activity","Data Kosong")
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun getMenuMakananData(){
//        menumakananRv.visibility = View.GONE
        dbref  = FirebaseDatabase.getInstance().getReference("menu").child("makanan")

        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("Menu Activity","Data Berhasil: $snapshot")
                makananList.clear()
                if (snapshot.exists()){
                    for (menuSnap in snapshot.children){
                        val makananItem = menuSnap.getValue(dataMenu::class.java)
                        makananList.add(makananItem!!)
                    }
                    val mAdapter = MenuAdapter(makananList)
                        menumakananRv.adapter = mAdapter
                    }else {
                    Log.d("Menu Activity","Data Kosong")
                }
            }


            override fun onCancelled(error: DatabaseError) {
                
            }

        })
    }
}