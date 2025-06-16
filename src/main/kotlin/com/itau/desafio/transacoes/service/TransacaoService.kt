package com.itau.desafio.transacoes.service

import com.itau.desafio.transacoes.model.Estatistica
import com.itau.desafio.transacoes.model.Transacao
import org.springframework.stereotype.Service
import java.time.OffsetDateTime
import java.util.concurrent.CopyOnWriteArrayList

@Service
class TransacaoService {

    private val transacoes = CopyOnWriteArrayList<Transacao>()

    fun adicionar(transacao: Transacao): Boolean {
        val now = OffsetDateTime.now()

        if(transacao.valor < 0 || transacao.dataHora.isAfter(now)) {
            return false
        }

        transacoes.add(transacao)
        return true
    }

    fun limpar() {
        transacoes.clear()
    }

    fun calcularEstatisticas(): Estatistica {
        val limite = OffsetDateTime.now().minusSeconds(60)
        val ultimasTransacoes = transacoes.filter { it.dataHora.isAfter(limite) }

        val valores = ultimasTransacoes.map { it.valor }

        return if(valores.isEmpty()) {
            Estatistica(0,0.0, 0.0, 0.0, 0.0)

        } else {
            val count = valores.size.toLong()
            val sum = valores.sum()
            val avg = sum / count
            val min = valores.minOrNull() ?: 0.0
            val max = valores.maxOrNull() ?: 0.0

            Estatistica(count, sum, avg, min, max)
        }
    }
}