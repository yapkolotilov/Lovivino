package com.ocean.lovivino.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ocean.lovivino.R
import com.ocean.lovivino.common.FragmentWithViewModel
import com.ocean.lovivino.utils.autoDispose
import com.ocean.lovivino.utils.textString
import kotlinx.android.synthetic.main.fragment_log_in.*
import kotlin.reflect.KClass


/**
 * A simple [Fragment] subclass.
 */
class LogInFragment : FragmentWithViewModel<LogInViewModel, LogInViewModelFactory>() {

    override val classType: KClass<LogInViewModel> = LogInViewModel::class

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        switchToLogIn()
    }

    private fun switchToRegister() {
        register_button.apply {
            text = getString(R.string.log_in)
            setOnClickListener { switchToLogIn() }
        }
        confirm_password_icon.visibility = View.VISIBLE
        confirm_password_edit_text.apply {
            visibility = View.VISIBLE
        }

        log_in_button.apply {
            text = getString(R.string.register)
            setOnClickListener {
                viewModel.register(email_edit_text.textString(), password_edit_text.textString())
                    .subscribe({
                        switchToLogIn()
                    }, {
                        error_textview.text = it.message
                    })
                    .autoDispose()
            }
        }
    }

    private fun switchToLogIn() {
        register_button.apply {
            text = getString(R.string.register)
            setOnClickListener { switchToRegister() }
        }
        confirm_password_icon.visibility = View.GONE
        confirm_password_edit_text.apply {
            visibility = View.GONE
        }

        log_in_button.apply {
            text = getString(R.string.log_in)
            setOnClickListener {
                viewModel.logIn(email_edit_text.textString(), password_edit_text.textString())
                    .subscribe({
                        sharedPreferences.edit().putString("api_key", it).apply()
                        findNavController().navigate(R.id.action_logInFragment_to_fieldFragment)
                    }, {
                        error_textview.text = it.message
                    })
                    .autoDispose()
            }
        }
    }
}
