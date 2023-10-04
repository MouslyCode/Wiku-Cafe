package com.example.wikucafe.Add

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.wikucafe.Data.DataTable
import com.example.wikucafe.R
import com.example.wikucafe.TableActivity
import com.example.wikucafe.databinding.ActivityAddTableBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddTableActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddTableBinding
    private lateinit var dbref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_table)

        binding = ActivityAddTableBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backbtn.setOnClickListener {
            startActivity(Intent(this, TableActivity::class.java))
        }

        dbref =  FirebaseDatabase.getInstance().getReference("table")

        binding.btntmbh.setOnClickListener{
            val nomorMejaText = binding.edtNomorTable.editText?.text.toString()

            if (nomorMejaText.isEmpty()) {
                binding.edtNomorTable.error = "Mohon Memasukan Nama"
                binding.edtNomorTable.requestFocus()
                return@setOnClickListener
            }
            val nomorMeja = nomorMejaText.toInt()

            val tableId = dbref.push().key
            val table = DataTable(id = tableId, nomorMeja)

            dbref.child(tableId!!).setValue(table)
                .addOnCompleteListener{
                    Toast.makeText(this, "Meja Berhasil Ditambahkan", Toast.LENGTH_LONG).show()

                    binding.edtNomorTable.editText?.text?.clear()
                    startActivity(Intent(this, TableActivity::class.java))
                }
                .addOnFailureListener{ err ->
                    Toast.makeText(this, "Gagal Ditambahkan ${err.message}", Toast.LENGTH_LONG)
                        .show()
                }


        }
    }
}