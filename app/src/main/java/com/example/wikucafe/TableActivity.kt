package com.example.wikucafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wikucafe.Adapter.TableAdapter
import com.example.wikucafe.Add.AddTableActivity
import com.example.wikucafe.Data.DataTable
import com.example.wikucafe.databinding.ActivityTableBinding
import com.google.firebase.database.*

class TableActivity : AppCompatActivity() {
    private lateinit var rv_table: RecyclerView
    private lateinit var dbref: DatabaseReference
    private lateinit var binding: ActivityTableBinding
    private lateinit var tableList: ArrayList<DataTable>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)

        binding = ActivityTableBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backbtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.fabbtnTable.setOnClickListener {
            startActivity(Intent(this, AddTableActivity::class.java))
        }

        rv_table = binding.rvTampilTable
        rv_table.layoutManager = LinearLayoutManager(this)
        rv_table.setHasFixedSize(true)

        tableList = arrayListOf<DataTable>()

        getTableData()

    }

    private fun getTableData() {
        dbref = FirebaseDatabase.getInstance().getReference("table")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("Table Activity", "Data Berhasil: $snapshot")
                tableList.clear()
                if (snapshot.exists()) {
                    for (tableSnap in snapshot.children) {
                        val tableItem = tableSnap.getValue(DataTable::class.java)
                        tableList.add(tableItem!!)
                    }
                    rv_table.adapter = TableAdapter(tableList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}
