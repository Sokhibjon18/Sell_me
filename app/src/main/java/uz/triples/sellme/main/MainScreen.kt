package uz.triples.sellme.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_main.*
import uz.triples.sellme.R
import uz.triples.sellme.data.CategoryData
import uz.triples.sellme.data.ProductData
import uz.triples.sellme.main.adapter.CategoryAdapter
import uz.triples.sellme.main.adapter.LocationProductAdapter
import uz.triples.sellme.main.adapter.ProductAdapter

class MainScreen : Fragment(R.layout.fragment_main) {
    private val adapter by lazy { CategoryAdapter() }
    private val prAdapter by lazy { ProductAdapter() }
    private val locPrAdapter by lazy { LocationProductAdapter() }
    private val categoryData = ArrayList<CategoryData>()
    private val productData = ArrayList<ProductData>()
    private val locationProductData = ArrayList<ProductData>()
    private val mAuth = FirebaseAuth.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadCategoryData()
        adapter.submitList(categoryData)
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        loadProductData()
        prAdapter.submitList(productData)
        list_product1.adapter = prAdapter
        list_product1.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        loadLocationProductData()
        locPrAdapter.submitList(locationProductData)
        location_product_list.adapter = locPrAdapter
        location_product_list.layoutManager =
            GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)

        locPrAdapter.setOnClickListener {
            Toast.makeText(context, "item position = $it", Toast.LENGTH_SHORT).show()
        }


        upload_image.setOnClickListener {
            if (mAuth.currentUser == null)
                findNavController().navigate(R.id.loginFragment)
            else
                findNavController().navigate(R.id.cameraScreen)
        }


        prAdapter.setOnClickFavoriteListener {
            if (productData[it].isfavorite) {
                productData[it].isfavorite = false
                prAdapter.notifyDataSetChanged()
            } else {
                productData[it].isfavorite = true
                prAdapter.notifyDataSetChanged()
            }
        }

    }

    fun loadCategoryData() {
        for (i in 0..20) {
            categoryData.add(CategoryData(i, "name $i", R.drawable.car))
        }
    }

    fun loadProductData() {
        for (i in 0..20) {
            productData.add(ProductData(i, R.drawable.productimg, true))
        }
    }

    fun loadLocationProductData() {
        for (i in 0..100) {
            locationProductData.add(ProductData(i, R.drawable.productimg, true))
        }
    }
}