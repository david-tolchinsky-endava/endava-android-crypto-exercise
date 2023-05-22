package com.mendoza.endavacryptoapp.utils

import java.text.NumberFormat

object StringUtils {
    fun formatCurrency(amount:Double):String {
        val numberFormat = NumberFormat.getCurrencyInstance()
        return numberFormat.format(amount).replace(numberFormat.currency?.symbol ?: "$", "")
    }
}