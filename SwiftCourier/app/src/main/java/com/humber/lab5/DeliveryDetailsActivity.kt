package com.humber.lab5

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class DeliveryDetailsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_details)

        val textOrderInfo: TextView = findViewById(R.id.textOrderInfo)
        val orderDetailsText: TextView = findViewById(R.id.orderDetailsText)
        val buttonMapView: Button = findViewById(R.id.buttonMapView)

        // Get order details from Intent
        val orderId = intent.getStringExtra("orderId") ?: "No Order"
        val address = intent.getStringExtra("address") ?: "No Address"
        val status = intent.getStringExtra("status") ?: "Pending"

        // Display order details
        orderDetailsText.text = "Order: $orderId\nAddress: $address\nStatus: $status"

        // Open Google Maps on button click
        buttonMapView.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:0,0?q=$address")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
    }
}