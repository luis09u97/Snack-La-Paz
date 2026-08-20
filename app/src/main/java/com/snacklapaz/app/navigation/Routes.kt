package com.snacklapaz.app.ui.navigation

/**
 * Rotas centralizadas do app. Usar sempre estas constantes em vez de
 * strings soltas, pra evitar erros de digitação em telas futuras.
 */
object Routes {
    const val HOME = "home"
    const val SEARCH = "search"
    const val CART = "cart"
    const val ORDERS = "orders"
    const val PROFILE = "profile"

    const val ADDRESS = "address"
    const val ORDER_CONFIRMATION = "order_confirmation/{orderNumber}/{total}"
    const val RECEIPT = "receipt"
    const val ORDER_TRACKING = "order_tracking"

    fun orderConfirmationRoute(orderNumber: String, total: String) =
        "order_confirmation/$orderNumber/$total"
}