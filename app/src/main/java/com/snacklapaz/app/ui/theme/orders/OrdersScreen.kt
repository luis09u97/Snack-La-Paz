package com.snacklapaz.app.ui.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.snacklapaz.app.ui.theme.CreamBackground

@Composable
fun OrdersScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CreamBackground),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Pedidos 📦\n(em construção)",
            style = MaterialTheme.typography.titleMedium
        )
    }
}