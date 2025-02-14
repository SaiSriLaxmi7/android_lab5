package com.humber.lab5

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    private lateinit var preferenceManager: PreferenceManager
    private lateinit var inputOrderId: EditText
    private lateinit var buttonFetch: Button
    private lateinit var orderDetailsContainer: LinearLayout
    private lateinit var displayOrderId: TextView
    private lateinit var displayAddress: TextView
    private lateinit var displayStatus: TextView
    private lateinit var buttonUpdate: Button
    private lateinit var buttonViewDetails: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize SharedPreferences Helper
        preferenceManager = PreferenceManager(this)

        // Initialize UI Components
        inputOrderId = findViewById(R.id.inputOrderId)
        buttonFetch = findViewById(R.id.buttonFetch)
        orderDetailsContainer = findViewById(R.id.orderDetailsContainer)
        displayOrderId = findViewById(R.id.displayOrderId)
        displayAddress = findViewById(R.id.displayAddress)
        displayStatus = findViewById(R.id.displayStatus)
        buttonUpdate = findViewById(R.id.buttonUpdate)
        buttonViewDetails = findViewById(R.id.buttonViewDetails)

        // Hide order details initially
        orderDetailsContainer.visibility = View.GONE

        // Fetch Order Data Manually
        buttonFetch.setOnClickListener {
            val inputId = inputOrderId.text.toString()
            val (savedOrderId, savedAddress, savedStatus) = preferenceManager.getOrder()

            if (inputId == savedOrderId) {
                displayOrderId.text = "Order: $savedOrderId"
                displayAddress.text = "Address: $savedAddress"
                displayStatus.text = "Status: $savedStatus"
                orderDetailsContainer.visibility = View.VISIBLE
                Toast.makeText(this, "Order Retrieved!", Toast.LENGTH_SHORT).show()
            } else {
                orderDetailsContainer.visibility = View.GONE
                Toast.makeText(this, "Order Not Found!", Toast.LENGTH_SHORT).show()
            }
        }

        // Update order status
        buttonUpdate.setOnClickListener {
            preferenceManager.saveOrder("123", "205 Humber College Blvd, Etobicoke", "Completed")
            loadOrderData()
            Toast.makeText(this, "Order Status Modified!", Toast.LENGTH_SHORT).show()
        }

        // Navigate to Delivery Details Activity
        buttonViewDetails.setOnClickListener {
            val intent = Intent(this, DeliveryDetailsActivity::class.java)
            val (orderId, address, status) = preferenceManager.getOrder()
            intent.putExtra("orderId", orderId)
            intent.putExtra("address", address)
            intent.putExtra("status", status)
            startActivity(intent)
        }
    }

    private fun loadOrderData() {
        val (orderId, address, status) = preferenceManager.getOrder()

        if (orderId == "No Order") {
            preferenceManager.saveOrder("123", "205 Humber College Blvd, Etobicoke", "Completed")
        }

        displayOrderId.text = "Order: $orderId"
        displayAddress.text = "Address: $address"
        displayStatus.text = "Status: $status"
    }
}