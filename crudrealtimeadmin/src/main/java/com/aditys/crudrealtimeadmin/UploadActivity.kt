package com.aditys.crudrealtimeadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.aditys.crudrealtimeadmin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener{
            val ownerName = binding.uploadMoleculeName.text.toString()
            val vehicleBrand = binding.uploadHeatCapacity.text.toString()
            val vehicleRTO = binding.uploadMolecularFormula.text.toString()
            val vehicleNumber = binding.uploadMoleculePH.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("Molecule Information")
            val vehicleData = VehicleData(ownerName, vehicleBrand,vehicleNumber)
            databaseReference.child(vehicleNumber).setValue(vehicleData).addOnSuccessListener {
                binding.uploadMoleculeName.text.clear()
                binding.uploadHeatCapacity.text.clear()
                binding.uploadMolecularFormula.text.clear()
                binding.uploadMoleculePH.text.clear()

                Toast.makeText(this,"Saved" , Toast.LENGTH_SHORT).show()
                val intent = Intent(this@UploadActivity,MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener{
                Toast.makeText(this,"Failed" , Toast.LENGTH_SHORT).show()

            }

        }

    }
}