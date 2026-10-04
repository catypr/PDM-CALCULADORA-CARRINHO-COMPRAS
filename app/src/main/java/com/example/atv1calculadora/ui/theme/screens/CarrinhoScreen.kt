package com.example.atv1calculadora.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.atv1calculadora.domain.CarrinhoDomainService
import com.example.atv1calculadora.model.ItemCarrinho
import com.example.atv1calculadora.model.Produto
import com.example.atv1calculadora.ui.theme.components.ItemCarrinhoCard
import java.util.Locale
import androidx.compose.ui.tooling.preview.Preview

fun getCatalogoProdutos(): List<Produto> {
    return listOf(
        Produto(
            id = "1",
            nome = "Notebook Dell Inspiron 15 3000 Intel Core i5",
            precoUnitario = 3499.00,
            descricao = "Um Notebook rápido para suas tarefas diárias com alta eficiência.",
            descontoPercentual = 5.0
        ),

        Produto(
            id = "2",
            nome = "Mouse sem fio",
            precoUnitario = 89.90,
            descricao = null,
            descontoPercentual = 0.0
        ),

        Produto(
            id = "3",
            nome = "Teclado mecânico RGB com switch azul, ABNT2",
            precoUnitario = 349.90,
            descricao = "Teclado mecânico com iluminação RGB.",
            descontoPercentual = 0.0
        ),

        Produto(
            id = "4",
            nome = "Monitor Gamer 27",
            precoUnitario = 1200.00,
            descricao = "144Hz 1ms",
            descontoPercentual = 10.0
        ),

        Produto(
            id = "5",
            nome = "Headset Surround 7.1",
            precoUnitario = 250.00,
            descricao = "Sem fio",
            descontoPercentual = 0.0
        ),

        Produto(
            id = "6",
            nome = "Pad de Mouse Extra Grande",
            precoUnitario = 50.00,
            descricao = "90x40cm",
            descontoPercentual = 0.0
        )
    )
}


fun getDadosEntradaValidacao(): List<ItemCarrinho> {

    val catalogo = getCatalogoProdutos()

    return listOf(
        ItemCarrinho(
            produto = catalogo[0],
            quantidade = 2
        ),

        ItemCarrinho(
            produto = catalogo[1],
            quantidade = 1
        ),

        ItemCarrinho(
            produto = catalogo[2],
            quantidade = 1
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarrinhoScreen() {
    val itensCarrinho = remember { getDadosEntradaValidacao() }

    LaunchedEffect(Unit) {
        CarrinhoDomainService.gerarRelatorioLogcat(itensCarrinho)
    }

    val subtotalBruto = CarrinhoDomainService.calcularSubtotalBruto(itensCarrinho)
    val totalDesconto = CarrinhoDomainService.calcularTotalDesconto(itensCarrinho)
    val totalFinal = CarrinhoDomainService.calcularValorTotalFinal(itensCarrinho)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Meu Carrinho", style = MaterialTheme.typography.titleLarge) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(itensCarrinho) { item ->
                    ItemCarrinhoCard(item = item)
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                LinhaResumo(
                    rotulo = "Subtotal bruto:",
                    valor = String.format(Locale("pt", "BR"), "R$ %.2f", subtotalBruto)
                )
                LinhaResumo(
                    rotulo = "Descontos aplicados:",
                    valor = String.format(Locale("pt", "BR"), "-R$ %.2f", totalDesconto)
                )
                Spacer(modifier = Modifier.height(4.dp))
                LinhaResumo(
                    rotulo = "VALOR TOTAL FINAL:",
                    valor = String.format(Locale("pt", "BR"), "R$ %.2f", totalFinal),
                    destaque = true
                )
            }
        }
    }
}

@Composable
fun LinhaResumo(rotulo: String, valor: String, destaque: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = rotulo,
            style = if (destaque) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyMedium,
            fontWeight = if (destaque) FontWeight.Bold else FontWeight.Normal
        )
        Text(
            text = valor,
            style = if (destaque) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyMedium,
            fontWeight = if (destaque) FontWeight.Bold else FontWeight.Normal,
            color = if (destaque) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CarrinhoScreenPreview() {
    CarrinhoScreen()
}