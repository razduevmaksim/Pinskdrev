package com.maksapp.pinskdrev.ui.orders

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.maksapp.pinskdrev.*
import com.maksapp.pinskdrev.adapter.NewOrdersAdapter
import com.maksapp.pinskdrev.databinding.FragmentOrdersBinding
import com.maksapp.pinskdrev.userdata.User
import kotlinx.android.synthetic.main.layout_alert_dialog.view.*

class OrdersFragment : Fragment() {
    private lateinit var preferences: SharedPreferences
    private lateinit var database: DatabaseReference
    private var _binding: FragmentOrdersBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NewOrdersAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val ordersViewModel =
            ViewModelProvider(this).get(OrdersViewModel::class.java)

        _binding = FragmentOrdersBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

        binding.buttonCheckout.setOnClickListener {
            preferences = this.requireActivity()
                .getSharedPreferences(USER_INFORMATION_PREFERENCES, Context.MODE_PRIVATE)
            val userFirstName = preferences.getString(USER_INFORMATION_FIRST_NAME, "")
            val userLastName = preferences.getString(USER_INFORMATION_LAST_NAME, "")
            val userEmail = preferences.getString(USER_INFORMATION_EMAIL, "")
            val userPhone = preferences.getString(USER_INFORMATION_PHONE, "")

            if (userFirstName!!.isEmpty() || userLastName!!.isEmpty() || userEmail!!.isEmpty() || userPhone!!.isEmpty()) {
                Toast.makeText(context, "Заполните данные о себе", Toast.LENGTH_SHORT).show()
                showEditTextDialog()
            } else {
                database = FirebaseDatabase.getInstance().getReference("Users")
                val user = User(userFirstName, userLastName, userEmail, userPhone)
                database.child(userFirstName + userLastName).setValue(user).addOnSuccessListener {
                    Toast.makeText(context, "Заказ оформлен", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(context, "Заказ не оформлен", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun showEditTextDialog() {
        val builder = AlertDialog.Builder(context)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.layout_alert_dialog, null)

        with(builder) {
            setTitle("Анкета")
            setPositiveButton("Сохранить") { dialog, which ->
                preferences =
                    this.context.getSharedPreferences(
                        USER_INFORMATION_PREFERENCES,
                        Context.MODE_PRIVATE
                    )

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

                Toast.makeText(context, "Данные сохранены", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("Отмена") { dialog, which ->
                Toast.makeText(context, "Данные не сохранены", Toast.LENGTH_SHORT).show()
            }
            setView(dialogLayout)
            show()
        }
    }

    @Deprecated(
        "Deprecated in Java", ReplaceWith(
            "inflater.inflate(R.menu.menu_action_bar, menu)",
            "com.example.geolocation.R"
        )
    )
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_action_bar, menu)
    }

    //вызов диалогового окна при клике на кнопку CLEAR ALL.
    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mybutton) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Удаление всех данных")
            builder.setMessage("Вы действительно хотите удалить все данные?")

            //события при клике на "отмена". Выход из диалогового окна
            builder.setNegativeButton("Отмена") { dialog, _ ->
                dialog.cancel()
            }

            //события при клике на "удалить". Удаление данных из room
            builder.setPositiveButton("Удалить") { _, _ ->
                deleteAll()
            }
            builder.show()
        }
        return super.onOptionsItemSelected(item)
    }

    //запрос на удаление всех данных
    private fun deleteAll() {
        val viewModel = ViewModelProvider(this)[OrdersViewModel::class.java]

        //инициализация БД
        viewModel.initDatabase()
        adapter = NewOrdersAdapter(requireContext())
        recyclerView.adapter = adapter

        viewModel.deleteAll()
    }

    // инициализация RecyclerView и чтение данных из room
    private fun init() {
        val viewModel = ViewModelProvider(this)[OrdersViewModel::class.java]

        //инициализация БД
        viewModel.initDatabase()

        recyclerView = binding.recyclerViewLocation
        adapter = NewOrdersAdapter(requireContext())
        recyclerView.adapter = adapter

        //чтение данных
        viewModel.getAll().observe(viewLifecycleOwner) { listOrders ->
            adapter.setList(listOrders)
        }
    }
}