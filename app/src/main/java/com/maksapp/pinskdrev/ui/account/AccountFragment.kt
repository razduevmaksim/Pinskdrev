package com.maksapp.pinskdrev.ui.account

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
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import com.maksapp.pinskdrev.*
import com.maksapp.pinskdrev.databinding.FragmentAccountBinding
import kotlinx.android.synthetic.main.activity_main.*


class AccountFragment : Fragment() {
    private var _binding: FragmentAccountBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val accountViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
        requireActivity().nav_view.visibility = View.VISIBLE

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.buttonUserInformation.setOnClickListener {
            it.findNavController().navigate(R.id.action_navigation_account_to_userInformationFragment)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}