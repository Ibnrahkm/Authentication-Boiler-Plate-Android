package com.ibrahim.basicboilerplate.util

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.ibrahim.basicboilerplate.R

class Helper {


    companion object {
        public  var isInternetAvailable=MutableLiveData<Boolean>()

        private lateinit var progressDialog: ProgressDialog
        private lateinit var alertDialog: AlertDialog.Builder

        fun checkInputFields(activity: Activity, editText: Array<EditText>): Boolean {
            var counter: Int = 0;
            for (singleEditText: EditText in editText) {
                if (singleEditText.text.toString().trim().equals("")||singleEditText.text.toString().trim().equals("null")) {
                    Toast.makeText(
                        activity,
                        singleEditText.tag.toString()+" "+ activity.getString(R.string.is_empty),
                        Toast.LENGTH_SHORT
                    ).show()
                    return false
                } else {
                    if (singleEditText.tag.equals(activity.getString(R.string.email))) {
                        val emailPattern = "[a-zA-Z0-9+._-]+@[a-z]+\\.+[a-z]+"
                        val inputEmail = singleEditText.text.toString().trim()
                        if (!inputEmail.matches(Regex(emailPattern))) {
                            Toast.makeText(
                                activity,
                                singleEditText.tag.toString().trim() + activity.getString(R.string.is_empty),
                                Toast.LENGTH_SHORT
                            ).show()
                            return false
                        } else {
                            counter++
                        }
                    } else {
                        counter++
                    }
                }
            }
            return counter == editText.size;
        }


        fun showProgressDialog(title: String, message: String, context: Activity): ProgressDialog {
            progressDialog = ProgressDialog(context)
            progressDialog.setTitle(title)
            progressDialog.setMessage(message)
            progressDialog.setCancelable(false)
            progressDialog.show()
            return progressDialog
        }


        fun hideProgressDialog() {
            if (progressDialog.isShowing) {
                progressDialog.dismiss()
            }
        }


        fun showAlertDialog(code: Int, title: String, message: String, context: Activity): AlertDialog.Builder {
            alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle(title)
            alertDialog.setMessage(message)
            alertDialog.setCancelable(false)
            alertDialog.setNeutralButton(context.getString(R.string.okay), DialogInterface.OnClickListener { a, v ->
                alertDialog.setCancelable(true)
            })
            alertDialog.create().show()
            return alertDialog
        }
    }
}