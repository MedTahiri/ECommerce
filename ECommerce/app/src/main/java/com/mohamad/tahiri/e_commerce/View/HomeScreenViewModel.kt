package com.mohamad.tahiri.e_commerceadmin.Views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mohamad.tahiri.e_commerce.View.Constants
import com.mohamad.tahiri.e_commerce.products

class HomeScreenViewModel : ViewModel() {
    init {
        getProducts()
    }

    private var _products = MutableLiveData(emptyList<products>().toMutableList())
    val products: LiveData<MutableList<products>> = _products

    fun addProduct(products: products) {
        Firebase.firestore.collection(Constants.Product).document(products.add_on).set(products)
    }

    fun getProducts() {
        Firebase.firestore.collection(Constants.Product).orderBy("add_on")
            .addSnapshotListener { value, e ->
                val list = emptyList<products>().toMutableList()
                if (value != null) {
                    for (doc in value) {
                        val data = doc.data
                        list.add(products(data.get(Constants.Title).toString(),
                            data.get(Constants.Description).toString(),
                            data.get(Constants.Price).toString(),
                            data.get(Constants.Number_Of_Products).toString(),
                            data.get(Constants.Image).toString(),
                            data.get(Constants.Add_ON).toString())
                        )
                    }
                }
                updateProducts(list)
            }
    }

    fun DeleteProduct(product: products) {
        Firebase.firestore.collection(Constants.Product).document(product.add_on).delete()
    }
    fun SearchProduct(query:String){
        Firebase.firestore.collection(Constants.Product).orderBy("add_on").get()
            .addOnSuccessListener{ value->
                val list = emptyList<products>().toMutableList()
                if (value != null) {
                    for (doc in value) {
                        val data = doc.data
                        if(query in data.get(Constants.Title).toString()){
                            list.add(products(data.get(Constants.Title).toString(),
                                data.get(Constants.Description).toString(),
                                data.get(Constants.Price).toString(),
                                data.get(Constants.Number_Of_Products).toString(),
                                data.get(Constants.Image).toString(),
                                data.get(Constants.Add_ON).toString())
                            )
                        }else{
                            continue
                        }
                    }
                }
                updateProducts(list)
            }
    }


    private fun updateProducts(list: MutableList<products>) {
        _products.value = list.asReversed()
    }
}