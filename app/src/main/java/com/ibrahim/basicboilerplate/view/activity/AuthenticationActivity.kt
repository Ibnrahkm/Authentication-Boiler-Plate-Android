package com.ibrahim.basicboilerplate.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.ibrahim.basicboilerplate.R
import com.ibrahim.basicboilerplate.util.Helper

class AuthenticationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        Helper.isInternetAvailable.observe(this@AuthenticationActivity, Observer { v ->
            if (v != null) {
                if (!v) {
                    Toast.makeText(this@AuthenticationActivity, getString(R.string.it_seems_to_be), Toast.LENGTH_LONG).show()
                    Helper.hideProgressDialog()
                }
            }
        })
    }
}
