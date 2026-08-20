package com.snacklapaz.app.ui.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * Controla o estado de autenticação do app. Por enquanto é 100%
 * simulado (qualquer email/senha "funciona"), só pra já termos o fluxo
 * de telas pronto. Quando o Supabase Auth entrar, login()/signUp()
 * passam a chamar de verdade a API — as telas não precisam mudar.
 */
class AuthViewModel : ViewModel() {

    var isLoggedIn by mutableStateOf(false)
        private set

    var userName by mutableStateOf("")
        private set

    var userEmail by mutableStateOf("")
        private set

    fun login(email: String, password: String) {
        // TODO: substituir por supabase.auth.signInWith(Email) na integração
        isLoggedIn = true
        userEmail = email
        userName = email.substringBefore("@").replaceFirstChar { it.uppercase() }
    }

    fun signUp(fullName: String, email: String, password: String) {
        // TODO: substituir por supabase.auth.signUpWith(Email) na integração
        isLoggedIn = true
        userEmail = email
        userName = fullName
    }

    fun logout() {
        isLoggedIn = false
        userName = ""
        userEmail = ""
    }
}