package com.example.project

import MusicService
import android.graphics.Color
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest


class Final : AppCompatActivity() {
    private val phoneNumber = "0795007972"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.final_menu)

        val myColor = getColor(R.color.background_color)
window.decorView.setBackgroundColor(myColor)

        val textPhoneNumber = findViewById<TextView>(R.id.textphonenumber)

        val sum = findViewById<TextView>(R.id.textView10)
        val loc = findViewById<TextView>(R.id.textView12)

        val num1 = findViewById<TextView>(R.id.textView13)
        val num2 = findViewById<TextView>(R.id.textView14)
        val num3 = findViewById<TextView>(R.id.textView15)
        val num4 = findViewById<TextView>(R.id.textView16)


        sum.text = "${Second.Prices.sum.toString()}$"

        num1.text = "${Second.Prices.burgnum}x Burgers"
        num2.text = "${Second.Prices.pizzanum}x Pizzas"
        num3.text = "${Second.Prices.hotdognum}x Hotdogs"
        num4.text = "${Second.Prices.pastanum}x Pastas"


        loc.setOnClickListener{
            val mapIntent: Intent = Uri.parse(
                "geo:0,0?q=I+Dabouq,+Ahl+Al+Bait+Street+Next+to+the+Cake+Shop,+Amman,+Jordan"
            ).let { location ->
             Intent(Intent.ACTION_VIEW, location)        }
             startActivity(mapIntent);
        }

textPhoneNumber.setOnClickListener{
    initiatePhoneCall()
}

    }
    private fun initiatePhoneCall() {
        val permission = Manifest.permission.CALL_PHONE
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            val phoneUri = Uri.parse("tel:$phoneNumber")
            val callIntent = Intent(Intent.ACTION_CALL, phoneUri)
            startActivity(callIntent)
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(permission), REQUEST_CALL_PHONE_PERMISSION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CALL_PHONE_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initiatePhoneCall()
            } else {
                TODO()
            }
        }
    }


    companion object {
        private const val REQUEST_CALL_PHONE_PERMISSION = 1
    }

}