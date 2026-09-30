package com.example.atv1calculadora.model

interface Pagavel {
    fun calcularTotal(): Double
}

data class Produto(
    val id: String,
    val nome: String,
    val precoUnitario: Double,
    val descricao: String? = null,
    val descontoPercentual: Double = 0.0
) : Pagavel {

    fun precoComDesconto(): Double {
        return precoUnitario * (1 - descontoPercentual / 100.0)
    }

    override fun calcularTotal(): Double {
        return precoComDesconto()
    }
}

data class ItemCarrinho(
    val produto: Produto,
    val quantidade: Int
) : Pagavel {

    override fun calcularTotal(): Double {
        return produto.precoComDesconto() * quantidade
    }

    fun calcularSubTotalBruto(): Double {
        return produto.precoUnitario * quantidade
    }

    fun calcularValorDesconto(): Double {
        return (produto.precoUnitario - produto.precoComDesconto()) * quantidade
    }
}