package com.itau.desafio.transacoes.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PositiveOrZero
import java.time.OffsetDateTime

data class Transacao(

    @field:PositiveOrZero(message = "O valor deve ser positivo ou zero")
    val valor: Double,

    @field:NotNull(message = "A data/hora é obrigatória")
    val dataHora: OffsetDateTime
)