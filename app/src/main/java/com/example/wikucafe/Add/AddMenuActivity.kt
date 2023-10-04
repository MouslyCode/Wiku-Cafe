package com.example.wikucafe.Add

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.wikucafe.MenuActivity
import com.example.wikucafe.Data.dataMenu
import com.example.wikucafe.databinding.ActivityAddMenuBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddMenuBinding
    private lateinit var dbref : DatabaseReference
    private var selectedImageUri: Uri? = null
    private val PICK_IMAGE_REQUEST = 1

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbref = FirebaseDatabase.getInstance().getReference("menu")
        binding.backbtn.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
        }
        val jenisMnu = arrayOf("Makanan","Minuman")
        val adapter = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,jenisMnu)
        binding.edtJenismenu.setAdapter(adapter)

        binding.btnUploadImage.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }

        binding.btntmbh.setOnClickListener {
            val menuName = binding.edtNamamenu.editText?.text.toString()
            val jnsMenu = binding.edtJenismenu.text.toString()
            val hargaMenuText = binding.edtHarga.editText?.text.toString()
            val desc = binding.edtDesc.editText?.text.toString()
            val img = selectedImageUri?.toString()


                if (menuName.isEmpty()) {
                    binding.edtNamamenu.error = "Mohon Memasukan Nama"
                    binding.edtNamamenu.requestFocus()
                    return@setOnClickListener
                }
                    if (jnsMenu.isEmpty()){
                    binding.edtJenismenu.error = "Mohon Memilih Jenis Menu"
                    binding.edtJenismenu.requestFocus()
                    return@setOnClickListener
                }
                if (hargaMenuText.isEmpty()) {
                    binding.edtHarga.error = "Mohon Mengisi Harga Menu"
                    binding.edtHarga.requestFocus()
                    return@setOnClickListener
                }
                val hargaMenu = hargaMenuText.toInt()
                if (hargaMenu == 0){
                    binding.edtHarga.error = "Mohon Mengisi Harga Menu"
                    binding.edtHarga.requestFocus()
                    return@setOnClickListener
                }

                    if (desc.isEmpty()){
                    binding.edtDesc.error = "Mohon Mengisi Deskripsi"
                    binding.edtDesc.requestFocus()
                    return@setOnClickListener
                }

                    if (img == null || img.isEmpty()){
                        binding.imageErrorText.visibility = View.VISIBLE
                        binding.imageErrorText.text = "Mohon Memilih Gambar"
                        return@setOnClickListener
                    }

                    val menuId = dbref.push().key!!
                    val menu = dataMenu(id = menuId,menuName, jnsMenu, hargaMenu, desc, img = selectedImageUri.toString())

                if(jnsMenu == "Makanan"){
                    dbref.child("Makanan").child(menuId).setValue(menu)
                } else{
                    dbref.child("Minuman").child(menuId).setValue(menu)
                }
                    .addOnCompleteListener {
                        Toast.makeText(this, "Berhasil Ditambahkan", Toast.LENGTH_LONG).show()

                        binding.edtNamamenu.editText?.text?.clear()
                        binding.edtJenismenu.text?.clear()
                        binding.edtHarga.editText?.text?.clear()
                        binding.edtDesc.editText?.text?.clear()
                        selectedImageUri = null
                        binding.imageErrorText.visibility = View.GONE

                        startActivity(Intent(this, MenuActivity::class.java))

                    }.addOnFailureListener { err ->
                        Toast.makeText(this, "Gagal Ditambahkan ${err.message}", Toast.LENGTH_LONG)
                            .show()
                    }

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null){
            selectedImageUri = data?.data
        }
    }



    }
