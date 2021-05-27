package com.vmurillot.limeade

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.vmurillot.limeade.databinding.ActivityMainBinding
import com.vmurillot.limeade.model.UserData
import com.vmurillot.limeade.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Prueba 1
        userViewModel.booleano.observe(this, Observer { currentBool ->
            binding.btnSignIn.isEnabled = currentBool
        })

        binding.password.addTextChangedListener { _ ->
            userViewModel.verifyNotNull(
                UserViewModel.Origin.Main,
                UserData(
                    "",
                    "",
                    "",
                    binding.userName.text.toString(),
                    binding.password.text.toString(),
                    true
                )
            )
        }

        binding.userName.addTextChangedListener { _ ->
            userViewModel.verifyNotNull(
                UserViewModel.Origin.Main,
                UserData(
                    "",
                    "",
                    "",
                    binding.userName.text.toString(),
                    binding.password.text.toString(),
                    true
                )
            )
        }

        userViewModel.verifyNotNull(
            UserViewModel.Origin.Main, UserData("","","","","",false)
        )
        //Prueba 1

        binding.btnSignIn.setOnClickListener {
            handlerLogin()
        }

        binding.btnRegister.setOnClickListener {
            handlerRegisterUser()
        }

    }

    private fun handlerRegisterUser() {
        val context = applicationContext
        val intent = Intent(context, RegisterActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    private fun handlerLogin() {
        var result = userViewModel.SignIn()
        if (result == null) {
            val usrFound = Snackbar.make(binding.viewContainer, "User found!", 3000)
            usrFound.show()
        } else {
            val error = Snackbar.make(binding.viewContainer, result, 3000)
            error.show()
        }
    }
}