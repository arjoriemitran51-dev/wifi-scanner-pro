package com.wifiscanner.pro

import android.app.Activity
import android.os.Bundle
import android.content.Context
import android.net.wifi.WifiManager
import android.widget.*
import android.graphics.Color

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(24, 32, 24, 24)
        layout.setBackgroundColor(Color.rgb(20, 15, 40))

        val title = TextView(this)
        title.text = "Wi-Fi Scanner Pro"
        title.textSize = 28f
        title.setTextColor(Color.WHITE)
        layout.addView(title)

        val info = TextView(this)
        info.text = "Wi-Fi Network Scanner"
        info.textSize = 18f
        info.setTextColor(Color.LTGRAY)
        layout.addView(info)

        val scanButton = Button(this)
        scanButton.text = "Scan Wi-Fi"
        layout.addView(scanButton)

        val results = TextView(this)
        results.textSize = 16f
        results.setTextColor(Color.WHITE)
        layout.addView(results)

        scanButton.setOnClickListener {
            val wifi = applicationContext
                .getSystemService(Context.WIFI_SERVICE) as WifiManager

            val networks = wifi.scanResults

            results.text = if (networks.isEmpty()) {
                "No networks found. Check Wi-Fi permissions."
            } else {
                networks.joinToString("\n\n") {
                    "Name: ${it.SSID}\n" +
                    "Signal: ${it.level} dBm\n" +
                    "Security: ${it.capabilities}"
                }
            }
        }

        setContentView(layout)
    }
}
