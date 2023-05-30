package com.example.project

import MusicService
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myColor = getColor(R.color.background_color)
        window.decorView.setBackgroundColor(myColor)

        startService(Intent(this, MusicService::class.java))


        databaseHelper = DatabaseHelper(this)

        val updateButton = findViewById<Button>(R.id.update_button)
        val deleteButton = findViewById<Button>(R.id.delete_button)

        val usernameEditText = findViewById<EditText>(R.id.username)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val registerButton = findViewById<Button>(R.id.register_button)
        val loginButton = findViewById<Button>(R.id.login_button)

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Username or password cannot be empty.", Toast.LENGTH_LONG).show()
            } else {
                val newUser = User(name = username, password = password)
                val result = databaseHelper.addUser(newUser)
                if (result) {
                    Toast.makeText(this, "User registered successfully!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "User already exists!", Toast.LENGTH_LONG).show()
                }
            }
        }

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Username or password cannot be empty.", Toast.LENGTH_LONG).show()
            } else {
                val checkResult = databaseHelper.checkUser(username, password)
                when (checkResult) {
                    0 -> Toast.makeText(this, "User does not exist!", Toast.LENGTH_LONG).show()
                    1 -> Toast.makeText(this, "Password is incorrect!", Toast.LENGTH_LONG).show()
                    2 -> {
                        Toast.makeText(this, "User successfully logged in!", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, Second::class.java)
                        startActivity(intent)
                    }
                }
            }
        }

        updateButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val newPassword = passwordEditText.text.toString()

            if (username.isEmpty() || newPassword.isEmpty()) {
                Toast.makeText(this, "Username or new password cannot be empty.", Toast.LENGTH_LONG).show()
            } else {
                val updatedUser = User(name = username, password = newPassword)
                val result = databaseHelper.updateRecord(updatedUser)
                if (result) {
                    Toast.makeText(this, "User updated successfully!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Failed to update user!", Toast.LENGTH_LONG).show()
                }
            }
        }

        deleteButton.setOnClickListener {
            val username = usernameEditText.text.toString()

            if (username.isEmpty()) {
                Toast.makeText(this, "Username cannot be empty.", Toast.LENGTH_LONG).show()
            } else {
                val result = databaseHelper.deleteRecord(username)
                if (result) {
                    Toast.makeText(this, "User deleted successfully!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Failed to delete user!", Toast.LENGTH_LONG).show()
                }
            }
        }








    }
    }





