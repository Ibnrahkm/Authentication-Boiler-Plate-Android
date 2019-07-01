package com.ibrahim.basicboilerplate.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.ibrahim.basicboilerplate.R
import com.ibrahim.basicboilerplate.databinding.LayoutResetPasswordBinding
import com.ibrahim.basicboilerplate.util.Helper
import com.ibrahim.basicboilerplate.view.activity.AuthenticationActivity

class ResetPassword : Fragment() {

    lateinit var binding: LayoutResetPasswordBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_reset_password, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnBack.setOnClickListener { v ->
            Navigation.findNavController(binding.root).navigateUp()

        }
        binding.btnResetPassword.setOnClickListener { v ->
            if (Helper.checkInputFields(
                    activity as AuthenticationActivity,
                    arrayOf(binding.etOldPassword, binding.etNewPass, binding.etConfirmPassword)
                )
            ) {
                //do reset password
            }
        }
    }
}