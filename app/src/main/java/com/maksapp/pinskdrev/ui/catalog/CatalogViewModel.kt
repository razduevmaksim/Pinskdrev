package com.maksapp.pinskdrev.ui.catalog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.maksapp.pinskdrev.callback.ICategoryCallback
import com.maksapp.pinskdrev.model.CategoryModel

class CatalogViewModel : ViewModel(), ICategoryCallback {

    private var categoriesListMutable: MutableLiveData<List<CategoryModel>>? = null
    private var messageError: MutableLiveData<String> = MutableLiveData()
    private var categoryCallbackListener: ICategoryCallback = this

    override fun onCategorySuccess(categoryList: List<CategoryModel>) {
        categoriesListMutable!!.value = categoryList
    }

    override fun onCategoryLoadFailed(message: String) {
        messageError.value = message
    }

    fun getCategoryList(): MutableLiveData<List<CategoryModel>> {
        if (categoriesListMutable == null) {
            categoriesListMutable = MutableLiveData()
            loadCategory()
        }
        return categoriesListMutable!!
    }

    fun getMessageError(): MutableLiveData<String> {
        return messageError
    }

    private fun loadCategory() {
        val tempList = ArrayList<CategoryModel>()
        val categoryRef = FirebaseDatabase.getInstance()
            .getReference(com.maksapp.pinskdrev.common.Common.CATEGORY_REF)
        categoryRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (itemSnapshot in snapshot.children) {
                    val model = itemSnapshot.getValue(CategoryModel::class.java)
                    model!!.catalogId = itemSnapshot.key
                    tempList.add(model)
                }
                categoryCallbackListener.onCategorySuccess(tempList)
            }

            override fun onCancelled(error: DatabaseError) {
                categoryCallbackListener.onCategoryLoadFailed(error.message)
            }

        })
    }
}