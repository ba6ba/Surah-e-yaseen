package com.example.extensions

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.showToast(message : String, length : Int = Toast.LENGTH_SHORT) =
    Toast.makeText((requireActivity() as AppCompatActivity), message, length).show()