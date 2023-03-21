package com.maksapp.pinskdrev.userdata

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.maksapp.pinskdrev.*
import com.maksapp.pinskdrev.databinding.FragmentAccountBinding
import com.maksapp.pinskdrev.databinding.FragmentUserInformationBinding
import com.maksapp.pinskdrev.ui.account.AccountViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_user_information.*

class UserInformationFragment : Fragment() {
    private var _binding: FragmentUserInformationBinding? = null
    private lateinit var preferences: SharedPreferences

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val viewModel = ViewModelProvider(this).get(UserInformationViewModel::class.java)
        requireActivity().nav_view.visibility = View.GONE

        _binding = FragmentUserInformationBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferences = this.requireActivity()
            .getSharedPreferences(USER_INFORMATION_PREFERENCES, Context.MODE_PRIVATE)

        val userFirstName = preferences.getString(USER_INFORMATION_FIRST_NAME, "")
        val userLastName = preferences.getString(USER_INFORMATION_LAST_NAME, "")
        val userEmail = preferences.getString(USER_INFORMATION_EMAIL, "")
        val userPhone = preferences.getString(USER_INFORMATION_PHONE, "")

        alert_dialog_edit_text_first_name.setText(userFirstName)
        alert_dialog_edit_text_last_name.setText(userLastName)
        alert_dialog_edit_text_email.setText(userEmail)
        alert_dialog_edit_text_number.setText(userPhone)


        //Валидация FirstName
        var validationFirstName = false

        alert_dialog_edit_text_first_name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                validationFirstName =
                    if (alert_dialog_edit_text_first_name.text.toString().length >= 3) {
                        true
                    } else {
                        alert_dialog_edit_text_first_name.error = "Invalid Name"
                        false
                    }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        //Валидация LastName
        var validationLastName = false

        alert_dialog_edit_text_last_name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                validationLastName =
                    if (alert_dialog_edit_text_last_name.text.toString().length >= 3) {
                        true
                    } else {
                        alert_dialog_edit_text_last_name.error = "Invalid Name"
                        false
                    }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        //Валидация MobileNumber

        var validationNumber = false
        alert_dialog_edit_text_number.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                validationNumber = if (alert_dialog_edit_text_number.text.toString().length > 6) {
                    true
                } else {
                    alert_dialog_edit_text_number.error = "Invalid Number"
                    false
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        //Валидация Email
        var validationEmail = false

        alert_dialog_edit_text_email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                validationEmail =
                    if (android.util.Patterns.EMAIL_ADDRESS.matcher(alert_dialog_edit_text_email.text.toString())
                            .matches()
                    ) {
                        true
                    } else {
                        alert_dialog_edit_text_email.error = "Invalid Email"
                        false
                    }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        alert_dialog_button_save.setOnClickListener {
            if (validationFirstName && validationLastName && validationEmail && validationNumber) {
                preferences = this.requireContext().getSharedPreferences(
                    USER_INFORMATION_PREFERENCES, Context.MODE_PRIVATE
                )

                val userFirstName = alert_dialog_edit_text_first_name.text.toString()
                val userLastName = alert_dialog_edit_text_last_name.text.toString()
                val userEmail = alert_dialog_edit_text_email.text.toString()
                val userPhone = alert_dialog_edit_text_number.text.toString()

                //Передача данных в хранилище
                val editor = preferences.edit()
                editor.putString(USER_INFORMATION_FIRST_NAME, userFirstName)
                editor.putString(USER_INFORMATION_LAST_NAME, userLastName)
                editor.putString(USER_INFORMATION_EMAIL, userEmail)
                editor.putString(USER_INFORMATION_PHONE, userPhone)
                editor.apply()

                Toast.makeText(context, "Данные сохранены", Toast.LENGTH_SHORT).show()


            } else {
                Toast.makeText(context, "Заполните все данные", Toast.LENGTH_SHORT).show()
            }
        }
    }
}