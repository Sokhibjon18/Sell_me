package uz.triples.sellme.data

data class CategoryData(
    val id: Int,
    val name: String,
    val image: Int
)

data class ProductData(
    val id: Int,
    val image: Int,
    var isfavorite: Boolean
)