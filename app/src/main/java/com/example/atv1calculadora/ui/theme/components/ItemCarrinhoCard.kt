package com.example.atv1calculadora.ui.theme.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.atv1calculadora.model.ItemCarrinho
import java.util.Locale

@Composable
fun ItemCarrinhoCard(
    item: ItemCarrinho,
    modifier: Modifier = Modifier
) {
    val produto = item.produto
    val descricaoTexto = produto.descricao ?: "Sem descrição"

    val precoUnitarioFmt = String.format(Locale("pt", "BR"), "R$ %.2f", produto.precoUnitario)
    val totalItemFmt = String.format(Locale("pt", "BR"), "R$ %.2f", item.calcularTotal())

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = produto.nome,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = descricaoTexto,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$precoUnitarioFmt x${item.quantidade}",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = totalItemFmt,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}