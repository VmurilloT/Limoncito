package com.vmurillot.limeade

import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.vmurillot.limeade.databinding.ActivityRegisterBinding
import com.vmurillot.limeade.viewmodel.UserViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = "Create an account"

        //Prueba 2
        userViewModel.userModel.observe(this, Observer { currentUser ->
            currentUser.firstName = binding.firstName.text.toString()
            currentUser.lasttName = binding.lastName.text.toString()
            currentUser.Phone = binding.cellPhone.text.toString()
            currentUser.userName = binding.userName.text.toString()
            currentUser.password = binding.password.text.toString()

            userViewModel.booleano.observe(this, Observer { currentBoolean ->
                binding.btnCreateAccount.isEnabled = currentBoolean
            })
        })

        //Typing Listeners
        binding.password.addTextChangedListener { _ -> userViewModel.verifyNotNull() }
        binding.userName.addTextChangedListener { _ -> userViewModel.verifyNotNull() }
        binding.firstName.addTextChangedListener { _ -> userViewModel.verifyNotNull() }
        binding.lastName.addTextChangedListener { _ -> userViewModel.verifyNotNull() }
        binding.cellPhone.addTextChangedListener { _ -> userViewModel.verifyNotNull() }
        //Typing Listeners
        userViewModel.verifyNotNull()
        //Prueba 2
        setContentView(binding.root)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                this.finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}

