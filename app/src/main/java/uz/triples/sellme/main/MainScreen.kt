package uz.triples.sellme.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_main.*
import uz.triples.sellme.R
import uz.triples.sellme.data.CategoryData
import uz.triples.sellme.data.ProductData
import uz.triples.sellme.main.adapter.CategoryAdapter
import uz.triples.sellme.main.adapter.LocationProductAdapter
import uz.triples.sellme.main.adapter.ProductAdapter
import uz.triples.sellme.utils.hideKeyboard

class MainScreen : Fragment(R.layout.fragment_main) {
    val adapter by lazy { CategoryAdapter() }
    val pr_adapter by lazy { ProductAdapter() }
    val loc_pr_adapter by lazy { LocationProductAdapter() }
    val categorydata = ArrayList<CategoryData>()
    val productdata = ArrayList<ProductData>()
    val locationproductdata = ArrayList<ProductData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideKeyboard()

        loadCategoryData()
        adapter.submitList(categorydata)
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        loadProductData()
        pr_adapter.submitList(productdata)
        list_product1.adapter = pr_adapter
        list_product1.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        loadLocationProductData()
        loc_pr_adapter.submitList(locationproductdata)
        location_product_list.adapter = loc_pr_adapter
        location_product_list.layoutManager =
            GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)

        loc_pr_adapter.setOnClickListener {
            Toast.makeText(context, "item position = $it", Toast.LENGTH_SHORT).show()
        }


        upload_image.setOnClickListener {
            findNavController().navigate(R.id.action_mainScreen_to_cameraScreen)
        }


        pr_adapter.setOnClickFavoriteListener {
            if (productdata[it].isfavorite) {
                productdata[it].isfavorite = false
                pr_adapter.notifyDataSetChanged()
            } else {
                productdata[it].isfavorite = true
                pr_adapter.notifyDataSetChanged()
            }
        }

    }

    fun loadCategoryData() {
        for (i in 0..20) {
            categorydata.add(CategoryData(i, "name $i", R.drawable.car))
        }
    }

    fun loadProductData() {
        for (i in 0..20) {
            productdata.add(ProductData(i, R.drawable.productimg, true))
        }
    }

    fun loadLocationProductData() {
        for (i in 0..100) {
            locationproductdata.add(ProductData(i, R.drawable.productimg, true))
        }
    }
}