package com.example.atv1calculadora.domain

import com.example.atv1calculadora.model.ItemCarrinho

object CarrinhoDomainService {

    // Verifique se o nome exato aqui é "calcularSubtotalBruto"
    fun calcularSubtotalBruto(itens: List<ItemCarrinho>): Double {
        return itens.sumOf { it.produto.precoUnitario * it.quantidade }
    }

    fun calcularTotalDesconto(itens: List<ItemCarrinho>): Double {
        return itens.sumOf { item ->
            val precoTotalSemDesconto = item.produto.precoUnitario * item.quantidade
            val percentual = item.produto.descontoPercentual / 100.0
            precoTotalSemDesconto * percentual
        }
    }

    fun calcularValorTotalFinal(itens: List<ItemCarrinho>): Double {
        return itens.sumOf { it.calcularTotal() }
    }

    fun gerarRelatorioLogcat(itens: List<ItemCarrinho>) {
        // Lógica de log no Logcat
    }
}