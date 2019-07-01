package com.ibrahim.basicboilerplate.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.ibrahim.basicboilerplate.AppController
import com.ibrahim.basicboilerplate.R
import com.ibrahim.basicboilerplate.dagger.Component.DaggerLoginComponent
import com.ibrahim.basicboilerplate.service.model.UserModel
import com.ibrahim.basicboilerplate.util.Helper
import com.ibrahim.basicboilerplate.util.Resource
import com.ibrahim.basicboilerplate.view.activity.AuthenticationActivity
import com.ibrahim.basicboilerplate.viewmodel.LoginViewModel
import javax.inject.Inject

class LoginFragment : Fragment() {

    lateinit var binding: com.ibrahim.basicboilerplate.databinding.LayoutLoginBinding;

    @Inject
    lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_login, container, false)
        return binding.root;
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val component = DaggerLoginComponent.builder()
            .appComponent(AppController.get(activity as AuthenticationActivity).getApplicationComponent()).build();
        component.inject(this@LoginFragment)

        binding.model = viewModel

        binding.btnLogin.setOnClickListener { v ->
            if (Helper.checkInputFields(
                    activity as AuthenticationActivity,
                    arrayOf(binding.etEmail, binding.etPassword)
                )
            ) {
                viewModel.doLogin().observe(viewLifecycleOwner, androidx.lifecycle.Observer { v ->
                    if (v.status == Resource.Status.LOADING) {
                        Helper.showProgressDialog(
                            getString(R.string.please_wait),
                            getString(R.string.signing_in),
                            activity as AuthenticationActivity
                        )
                    } else if (v.status == Resource.Status.SUCCESS) {
                        Helper.hideProgressDialog()
                        val model = v.data as UserModel
                        if (!model.data!!.userInfo!!.emailVerified.equals("1")) {
                            Navigation.findNavController(binding.root)
                                .navigate(R.id.action_loginFragment_to_emailVerificationFragment)
                        } else {
                            //start home activity
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


        binding.btnSignup.setOnClickListener { v ->
            Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_signupFragment)
        }


        binding.btnForgotPassword.setOnClickListener { v ->
            Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_forgotPassword)
        }

    }
}