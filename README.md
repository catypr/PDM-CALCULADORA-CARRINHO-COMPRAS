# ATV1 - Calculadora de Carrinho de Compras Android

**Nome:** Catarine Pereira da Silva  
**Projeto:** Calculadora de Carrinho de Compras em Kotlin com Jetpack Compose  

---

## Sobre o Projeto

Este projeto é uma aplicação Android nativa. Ele simula o gerenciamento de um carrinho de compras, realizando o cálculo do subtotal bruto, desconto total aplicado e o valor total final a ser pago pelos produtos.

A arquitetura foi estruturada seguindo boas práticas de organização de código em camadas (Domain, Model, UI/Theme/Components e UI/Theme/Screens).

---

## Tecnologias e Ferramentas Utilizadas

- **Linguagem:** Kotlin
- **Interface UI:** Jetpack Compose (Material3)
- **IDE:** Android Studio
- **Conceitos Kotlin:** Interfaces (`Pagavel`), Data Classes (`Produto`, `ItemCarrinho`), Domain Services e Logcat para acompanhamento de execução.

---

## Capturas de Tela

### Tela do Carrinho de Compras (UI)
![Interface da Aplicação](screenshots/carrinho_screen.png)

### Saída de Logs no Logcat
![Saída Logcat](screenshots/logcat_output.png)

---

## Estrutura do Projeto

```text
com.example.atv1calculadora/
├── domain/
│   └── CarrinhoDomainService.kt   # Serviço de domínio (regras de negócio e logs)
├── model/
│   ├── Pagavel.kt                 # Interface para cálculo de totais
│   ├── Produto.kt                 # Data class do produto
│   └── ItemCarrinho.kt            # Data class do item no carrinho
├── ui/
│   └── theme/
│       ├── components/
│       │   └── ItemCarrinhoCard.kt # Componente de card para cada item
│       └── screens/
│           └── CarrinhoScreen.kt   # Tela principal com resumo e lista
└── MainActivity.kt                # Ponto de entrada do aplicativo
