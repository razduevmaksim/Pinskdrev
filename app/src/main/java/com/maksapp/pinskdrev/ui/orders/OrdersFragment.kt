package com.maksapp.pinskdrev.ui.orders

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.maksapp.pinskdrev.R
import com.maksapp.pinskdrev.adapter.NewOrdersAdapter
import com.maksapp.pinskdrev.databinding.FragmentOrdersBinding

class OrdersFragment : Fragment() {

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