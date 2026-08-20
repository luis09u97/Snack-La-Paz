package com.snacklapaz.app.ui.home.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BakeryDining
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.LunchDining
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.ui.graphics.vector.ImageVector

data class Category(
    val id: String,
    val name: String,
    val icon: ImageVector
)

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val rating: Float,
    val imageUrl: String,
    val categoryId: String,
    var isFavorite: Boolean = false
)

// ==== Dados de exemplo — serão substituídos pelo Supabase na integração futura ====

val sampleCategories = listOf(
    Category("salgados", "Salgados", Icons.Filled.LunchDining),
    Category("bebidas", "Bebidas", Icons.Filled.LocalDrink),
    Category("doces", "Doces", Icons.Filled.Cake),
    Category("paes", "Pães", Icons.Filled.BakeryDining),
    Category("pratos", "Pratos", Icons.Filled.RestaurantMenu),
    Category("promocoes", "Promoções", Icons.Filled.LocalFireDepartment)
)

val sampleFeaturedProducts = listOf(
    Product(
        id = "1",
        name = "Salteña Paceña",
        price = 12.0,
        rating = 4.8f,
        imageUrl = "https://images.unsplash.com/photo-1601050690597-df0568f70950?w=400",
        categoryId = "salgados"
    ),
    Product(
        id = "2",
        name = "Api Morado",
        price = 8.5,
        rating = 4.6f,
        imageUrl = "https://images.unsplash.com/photo-1541167760496-1628856ab772?w=400",
        categoryId = "bebidas"
    ),
    Product(
        id = "3",
        name = "Anticucho",
        price = 15.0,
        rating = 4.9f,
        imageUrl = "https://images.unsplash.com/photo-1529193591184-b1d58069ecdd?w=400",
        categoryId = "pratos"
    )
)

val samplePopularProducts = listOf(
    Product(
        id = "4",
        name = "Empanada de Queso",
        price = 7.0,
        rating = 4.5f,
        imageUrl = "https://images.unsplash.com/photo-1604467707321-a3f0ba1fc4e0?w=400",
        categoryId = "salgados"
    ),
    Product(
        id = "5",
        name = "Cuñapé",
        price = 6.0,
        rating = 4.7f,
        imageUrl = "https://images.unsplash.com/photo-1509440159596-0249088772ff?w=400",
        categoryId = "paes"
    ),
    Product(
        id = "6",
        name = "Tucumana",
        price = 10.0,
        rating = 4.4f,
        imageUrl = "https://images.unsplash.com/photo-1601050690597-df0568f70950?w=400",
        categoryId = "salgados"
    ),
    Product(
        id = "7",
        name = "Mocochinchi",
        price = 5.5,
        rating = 4.3f,
        imageUrl = "https://images.unsplash.com/photo-1541167760496-1628856ab772?w=400",
        categoryId = "bebidas"
    )
)