package com.humber.lab5

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("SwiftCourierPrefs", Context.MODE_PRIVATE)

    fun saveOrder(orderId: String, address: String, status: String) {
        val editor = sharedPreferences.edit()
        editor.putString("orderId", orderId)
        editor.putString("address", address)
        editor.putString("status", status)
        editor.apply()
    }

    fun getOrder(): Triple<String, String, String> {
        val orderId = sharedPreferences.getString("orderId", "No Order") ?: "No Order"
        val address = sharedPreferences.getString("address", "No Address") ?: "No Address"
        val status = sharedPreferences.getString("status", "Pending") ?: "Pending"
        return Triple(orderId, address, status)
    }
}