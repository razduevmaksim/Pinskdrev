package com.maksapp.pinskdrev.ui.account

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.maksapp.pinskdrev.*
import com.maksapp.pinskdrev.databinding.FragmentAccountBinding
import kotlinx.android.synthetic.main.layout_alert_dialog.*
import kotlinx.android.synthetic.main.layout_alert_dialog.view.*


class AccountFragment : Fragment() {
    private lateinit var preferences: SharedPreferences
    private var _binding: FragmentAccountBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val accountViewModel =
            ViewModelProvider(this).get(AccountViewModel::class.java)

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.buttonUserInformation.setOnClickListener{
            showEditTextDialog()
        }
        return root
    }

    fun showEditTextDialog(){
        val builder = AlertDialog.Builder(context)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.layout_alert_dialog, null)

        with(builder){
            setTitle("Анкета")
            setPositiveButton("Сохранить"){dialog, which ->
                preferences =
                    this.context.getSharedPreferences(USER_INFORMATION_PREFERENCES, Context.MODE_PRIVATE)

                val userFirstName = dialogLayout.alert_dialog_edit_text_first_name.text.toString()
                val userLastName = dialogLayout.alert_dialog_edit_text_last_name.text.toString()
                val userEmail = dialogLayout.alert_dialog_edit_text_email.text.toString()
                val userPhone = dialogLayout.alert_dialog_edit_text_number.text.toString()
                //Передача данных в хранилище
                val editor = preferences.edit()
                editor.putString(USER_INFORMATION_FIRST_NAME, userFirstName)
                editor.putString(USER_INFORMATION_LAST_NAME, userLastName)
                editor.putString(USER_INFORMATION_EMAIL, userEmail)
                editor.putString(USER_INFORMATION_PHONE, userPhone)
                editor.apply()

                Toast.makeText(context,"Данные сохранены", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("Отмена"){dialog, which ->
                Toast.makeText(context,"Данные не сохранены", Toast.LENGTH_SHORT).show()
            }
            setView(dialogLayout)
            show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}