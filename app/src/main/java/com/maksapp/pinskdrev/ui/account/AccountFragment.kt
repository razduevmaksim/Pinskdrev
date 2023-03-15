package com.maksapp.pinskdrev.ui.account

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.maksapp.pinskdrev.R
import com.maksapp.pinskdrev.databinding.FragmentAccountBinding


class AccountFragment : Fragment() {

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