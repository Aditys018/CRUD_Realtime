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
            val moleculeName = binding.uploadMoleculeName.text.toString()
            val heatCapacity = binding.uploadHeatCapacity.text.toString()
            val molecularFormula = binding.uploadMolecularFormula.text.toString()
            val moleculePH = binding.uploadMoleculePH.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("Molecule Information")
            val moleculeData = MoleculeData(moleculeName, heatCapacity,molecularFormula)
            databaseReference.child(molecularFormula).setValue(moleculeData).addOnSuccessListener {
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