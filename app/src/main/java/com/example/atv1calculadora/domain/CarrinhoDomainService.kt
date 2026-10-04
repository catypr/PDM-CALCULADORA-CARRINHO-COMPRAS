package com.example.atv1calculadora.domain

import android.util.Log
import com.example.atv1calculadora.model.ItemCarrinho
import java.util.Locale

object CarrinhoDomainService {

    fun calcularSubtotalBruto(itens: List<ItemCarrinho>): Double {
        return itens.sumOf { item ->
            item.produto.precoUnitario * item.quantidade
        }
    }

    fun calcularTotalDesconto(itens: List<ItemCarrinho>): Double {
        return itens.sumOf { item ->
            val precoTotalSemDesconto =
                item.produto.precoUnitario * item.quantidade

            val percentual =
                item.produto.descontoPercentual / 100.0

            precoTotalSemDesconto * percentual
        }
    }

    fun calcularValorTotalFinal(itens: List<ItemCarrinho>): Double {
        return itens.fold(0.0) { acumulador, item ->
            acumulador + item.calcularTotal()
        }
    }

    fun gerarRelatorioLogcat(itens: List<ItemCarrinho>) {

        val produtosComDesconto = itens
            .filter { item ->
                item.produto.descontoPercentual > 0
            }
            .sortedByDescending { item ->
                item.calcularTotal()
            }
            .map { item ->
                val valorFormatado = String.format(
                    Locale("pt", "BR"),
                    "R$ %.2f",
                    item.calcularTotal()
                )

                "${item.produto.nome} | $valorFormatado"
            }

        Log.d(
            "RELATORIO_CARRINHO",
            "=== PRODUTOS COM DESCONTO ==="
        )

        produtosComDesconto.forEach { linha ->
            Log.d("RELATORIO_CARRINHO", linha)
        }
    }
}