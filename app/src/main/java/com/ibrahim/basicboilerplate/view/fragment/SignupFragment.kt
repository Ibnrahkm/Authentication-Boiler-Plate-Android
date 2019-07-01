package com.ibrahim.basicboilerplate.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.ibrahim.basicboilerplate.AppController
import com.ibrahim.basicboilerplate.R
import com.ibrahim.basicboilerplate.dagger.Component.DaggerSignupComponent
import com.ibrahim.basicboilerplate.databinding.LayoutSignupBinding
import com.ibrahim.basicboilerplate.service.model.UserModel
import com.ibrahim.basicboilerplate.util.Helper
import com.ibrahim.basicboilerplate.util.Resource
import com.ibrahim.basicboilerplate.view.activity.AuthenticationActivity
import com.ibrahim.basicboilerplate.viewmodel.SignupViewModel
import javax.inject.Inject

class SignupFragment : Fragment() {
    lateinit var binding: LayoutSignupBinding
    @Inject
    lateinit var model: SignupViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_signup, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val component = DaggerSignupComponent.builder()
            .appComponent(AppController.get(activity as AuthenticationActivity).getApplicationComponent()).build()
        component.inject(this@SignupFragment)
        binding.model = model
        binding.btnBack.setOnClickListener { v ->
            Navigation.findNavController(binding.root).navigateUp()

        }
        binding.btnSignup.setOnClickListener { v ->
            if (Helper.checkInputFields(
                    activity as AuthenticationActivity,
                    arrayOf(
                        binding.etFirstName,
                        binding.etLastName,
                        binding.etEmail,
                        binding.etPassword,
                        binding.etConfirmPassword
                    )
                )
            ) {

                if (!binding.checkbox.isChecked) {
                    Toast.makeText(
                        activity as AuthenticationActivity,
                        getString(R.string.please_read_and_accept),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    model.doSignup().observe(viewLifecycleOwner, androidx.lifecycle.Observer { v ->
                        if (v.status == Resource.Status.LOADING) {
                            Helper.showProgressDialog(
                                getString(R.string.please_wait),
                                getString(R.string.signing_up),
                                activity as AuthenticationActivity
                            )
                        } else if (v.status == Resource.Status.SUCCESS) {
                            Helper.hideProgressDialog()
                            val model = v.data as UserModel
                            if (!model.data!!.userInfo!!.emailVerified.equals("1")) {
                                Navigation.findNavController(binding.root)
                                    .navigate(R.id.action_signupFragment_to_emailVerificationFragment)
                            }
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
        }
    }
}