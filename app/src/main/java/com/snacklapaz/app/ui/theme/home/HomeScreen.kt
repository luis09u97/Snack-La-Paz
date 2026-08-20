package com.snacklapaz.app.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.snacklapaz.app.ui.cart.CartViewModel
import com.snacklapaz.app.ui.components.ProductCard
import com.snacklapaz.app.ui.components.SnackTextField
import com.snacklapaz.app.ui.home.model.Category
import com.snacklapaz.app.ui.home.model.Product
import com.snacklapaz.app.ui.home.model.sampleCategories
import com.snacklapaz.app.ui.home.model.sampleFeaturedProducts
import com.snacklapaz.app.ui.home.model.samplePopularProducts
import com.snacklapaz.app.ui.theme.CreamBackground
import com.snacklapaz.app.ui.theme.GrayDark
import com.snacklapaz.app.ui.theme.GrayMedium
import com.snacklapaz.app.ui.theme.OrangeLight
import com.snacklapaz.app.ui.theme.OrangePrimary
import com.snacklapaz.app.ui.theme.OrangeSoft
import com.snacklapaz.app.ui.theme.White
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid

@Composable
fun HomeScreen(cartViewModel: CartViewModel) {
    // Estado local só pra fins de demonstração (sem persistência ainda).
    // Na integração com Supabase, isso vira um ViewModel com estado real.
    var searchText by remember { mutableStateOf("") }
    var featured by remember { mutableStateOf(sampleFeaturedProducts) }
    var popular by remember { mutableStateOf(samplePopularProducts) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(CreamBackground),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        item { HomeHeader() }

        item {
            Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)) {
                SnackTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    label = "Buscar salteñas, api, anticuchos...",
                    leadingIcon = Icons.Filled.Search
                )
            }
        }

        item { PromoBanner() }

        item {
            SectionTitle(title = "Categorias")
            CategoriesRow(categories = sampleCategories)
        }

        item {
            SectionTitle(title = "Destaques")
            FeaturedRow(
                products = featured,
                onFavoriteToggle = { id ->
                    featured = featured.map {
                        if (it.id == id) it.copy(isFavorite = !it.isFavorite) else it
                    }
                },
                onAddToCart = { product -> cartViewModel.addToCart(product) }
            )
        }

        item { SectionTitle(title = "Populares") }

        // Grid "manual" em pares, pra não conflitar com o scroll do LazyColumn pai
        val rows = popular.chunked(2)
        items(rows.size) { rowIndex ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                rows[rowIndex].forEach { product ->
                    ProductCard(
                        imageUrl = product.imageUrl,
                        name = product.name,
                        price = "Bs ${"%.2f".format(product.price)}",
                        rating = product.rating,
                        isFavorite = product.isFavorite,
                        onFavoriteClick = {
                            popular = popular.map {
                                if (it.id == product.id) it.copy(isFavorite = !it.isFavorite) else it
                            }
                        },
                        onAddToCartClick = { cartViewModel.addToCart(product) },
                        onClick = { /* Etapa de detalhes vai implementar */ },
                        modifier = Modifier.weight(1f)
                    )
                }
                // Preenche o espaço se a linha tiver só 1 item
                if (rows[rowIndex].size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun HomeHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(OrangePrimary)
            .padding(horizontal = 20.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Olá, Luid! 👋",
                color = White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "O que vamos saborear hoje?",
                color = White.copy(alpha = 0.9f),
                fontSize = 14.sp
            )
        }

        Surface(
            shape = CircleShape,
            color = White.copy(alpha = 0.2f),
            modifier = Modifier.size(44.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notificações",
                    tint = White
                )
            }
        }
    }
}

@Composable
private fun PromoBanner() {
    Surface(
        shape = RoundedCornerShape(18.dp),
        color = OrangeSoft,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .height(120.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(20.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Column {
                Text(
                    text = "🇧🇴 Sabores autênticos",
                    color = OrangePrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
                Text(
                    text = "Frete grátis no seu primeiro pedido",
                    color = GrayDark,
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        color = GrayDark,
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
    )
}

@Composable
private fun CategoriesRow(categories: List<Category>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(categories) { category ->
            CategoryItem(category = category)
        }
    }
}

@Composable
private fun CategoryItem(category: Category) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(72.dp)
            .clickable { /* Etapa da busca por categoria vai implementar */ }
    ) {
        Surface(
            shape = CircleShape,
            color = OrangeLight,
            modifier = Modifier.size(56.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = category.icon,
                    contentDescription = category.name,
                    tint = OrangePrimary,
                    modifier = Modifier.size(26.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = category.name,
            fontSize = 12.sp,
            color = GrayDark,
            maxLines = 1
        )
    }
}

@Composable
private fun FeaturedRow(
    products: List<Product>,
    onFavoriteToggle: (String) -> Unit,
    onAddToCart: (Product) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(products) { product ->
            ProductCard(
                imageUrl = product.imageUrl,
                name = product.name,
                price = "Bs ${"%.2f".format(product.price)}",
                rating = product.rating,
                isFavorite = product.isFavorite,
                onFavoriteClick = { onFavoriteToggle(product.id) },
                onAddToCartClick = { onAddToCart(product) },
                onClick = { /* Etapa de detalhes vai implementar */ },
                modifier = Modifier.width(160.dp)
            )
        }
    }
}