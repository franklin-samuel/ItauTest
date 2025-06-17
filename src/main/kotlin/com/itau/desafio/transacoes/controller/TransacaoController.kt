package com.itau.desafio.transacoes.controller

import com.itau.desafio.transacoes.model.Transacao
import com.itau.desafio.transacoes.service.TransacaoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.validation.annotation.Validated
import jakarta.validation.Valid

@RestController
class TransacaoController(private val service: TransacaoService) {
    @PostMapping("/transacao")
    fun adicionarTransacao(@RequestBody @Valid transacao: Transacao): ResponseEntity<Void> {
        return try {
            if (service.adicionar(transacao)) {
                ResponseEntity.status(HttpStatus.CREATED).build()
            } else {
                ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build()
            }
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @DeleteMapping("/transacao")
    fun limparTransacoes(): ResponseEntity<Void> {
        service.limpar()
        return ResponseEntity.ok().build()
    }

    @GetMapping("/estatistica")
    fun obterEstatisticas() = ResponseEntity.ok(service.calcularEstatisticas())
}