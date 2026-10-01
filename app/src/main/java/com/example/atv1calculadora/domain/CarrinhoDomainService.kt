package com.example.atv1calculadora.domain

import android.util.Log
import com.example.atv1calculadora.model.ItemCarrinho

object CarrinhoDomainService {

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
        Log.d("RELATORIO_CARRINHO", "=== INICIANDO GERACAO DE RELATORIO ===")
        Log.d("RELATORIO_CARRINHO", "Quantidade de itens no carrinho: ${itens.size}")

        if (itens.isEmpty()) {
            Log.w("RELATORIO_CARRINHO", "A lista de itens está VAZIA!")
            return
        }

        itens.forEach { item ->
            Log.d("RELATORIO_CARRINHO", "Item: ${item.produto.nome} | Total: R$ ${item.calcularTotal()}")
        }
    }
}