package com.aditys.crudrealtimeadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.aditys.crudrealtimeadmin.databinding.ActivityUpdateBinding
import com.aditys.crudrealtimeadmin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateButton.setOnClickListener{
            val referenceVehicleNumber = binding.referenceVehicleNumber.text.toString()
            val updateOwnerName = binding.updateOwnerName.text.toString()
            val updateVehicleBrand = binding.updateOwnerName.text.toString()
            val updateVehicleRTO = binding.updateOwnerName.text.toString()

            updateData(referenceVehicleNumber, referenceVehicleNumber,updateOwnerName,updateVehicleRTO)

        }
    }
    private fun updateData(vehicleNumber: String, ownerName:String, vehicleBrand:String, vehicleRTO:String){
        databaseReference=FirebaseDatabase.getInstance().getReference("Vehicle Information")
        val vehicleData = mapOf<String, String>("OwnerName" to ownerName, "vehicleBrand" to vehicleBrand, "vehicleRTO" to vehicleRTO)
        databaseReference.child(vehicleNumber).updateChildren(vehicleData).addOnSuccessListener {
            binding.referenceVehicleNumber.text.clear()
            binding.updateOwnerName.text.clear()
            binding.updateVehicleBrand.text.clear()
            binding.updateVehicleRTO.text.clear()
            Toast.makeText(this,"Updated" , Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{
            Toast.makeText(this,"Unable to update", Toast.LENGTH_SHORT).show()
        }
    }
}