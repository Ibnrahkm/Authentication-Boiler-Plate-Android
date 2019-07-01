package com.ibrahim.basicboilerplate.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.ibrahim.basicboilerplate.AppController
import com.ibrahim.basicboilerplate.R
import com.ibrahim.basicboilerplate.dagger.Component.DaggerEmailVerificationComponent
import com.ibrahim.basicboilerplate.databinding.LayoutEmailVerificationBinding
import com.ibrahim.basicboilerplate.service.model.UserModel
import com.ibrahim.basicboilerplate.util.Helper
import com.ibrahim.basicboilerplate.util.Resource
import com.ibrahim.basicboilerplate.view.activity.AuthenticationActivity
import com.ibrahim.basicboilerplate.viewmodel.EmailVerificationViewModel
import javax.inject.Inject

class EmailVerificationFragment : Fragment() {

    lateinit var binding: LayoutEmailVerificationBinding
    @Inject
    lateinit var model: EmailVerificationViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.layout_email_verification, container, false)

        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        DaggerEmailVerificationComponent.builder()
            .appComponent(AppController.get(activity as AuthenticationActivity).getApplicationComponent()).build()
            .inject(this@EmailVerificationFragment)
        binding.model = model
        binding.btnEmailVerification.setOnClickListener { v ->


            Log.e("finddd",binding.etCode.text.toString())

            if (Helper.checkInputFields(activity as AuthenticationActivity, arrayOf(binding.etCode))) {

                model.doEmailVerification().observe(viewLifecycleOwner, androidx.lifecycle.Observer { v ->
                    if (v.status == Resource.Status.LOADING) {
                        Helper.showProgressDialog(
                            getString(R.string.please_wait),
                            getString(R.string.signing_in),
                            activity as AuthenticationActivity
                        )
                    } else if (v.status == Resource.Status.SUCCESS) {
                        Helper.hideProgressDialog()
                        //start home screen
                    } else if (v.status == Resource.Status.ERROR) {
                        Helper.hideProgressDialog()
                        Helper.showAlertDialog(
                            v.error!!.code,
                            getString(R.string.info),
                            v.error.message,
                            activity as AuthenticationActivity
                        )
                    }
                })


            }
        }
        binding.btnBack.setOnClickListener { v ->
            Navigation.findNavController(binding.root).navigateUp()

        }
    }
}