package com.example.atv1calculadora.domain

import android.util.Log
import com.example.atv1calculadora.model.ItemCarrinho
import java.util.Locale

object CarrinhoDomainService {

    fun CalcularSubtotalBruto(itens: List<ItemCarrinho>): Double {
        return itens.sumOf { it.calcularSubTotalBruto() }
    }

    fun calcularTotal
}