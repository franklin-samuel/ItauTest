package com.itau.desafio.transacoes.model

import java.time.OffsetDateTime

data class Transacao(
    val valor: Double,
    val dataHora: OffsetDateTime
)