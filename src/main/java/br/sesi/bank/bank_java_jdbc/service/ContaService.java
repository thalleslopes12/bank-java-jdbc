package br.sesi.bank.bank_java_jdbc.service;

import br.sesi.bank.bank_java_jdbc.domain.conta.Conta;
import br.sesi.bank.bank_java_jdbc.domain.conta.DadosAberturaConta;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Set;

public class ContaService {
    public ContaService(){ }
    public Set<Conta> listarContasAbertas() {return null;}
    public BigDecimal consultarSaldo(Integer numeroDaConta) { return BigDecimal.ZERO;}
    public void abrir(DadosAberturaConta dadosDaConta) throws SQLException { }
    public void realizarSaque(Integer numeroDaConta, BigDecimal valor) { }
    public void realizarDeposito(Integer numeroDaConta, BigDecimal valor) { }
    public void realizaTransferencia(Integer numeroContaOrigem, Integer numeroContaDestino, BigDecimal valor){ }
    public void encerrar(Integer numeroDaConta) { }
    private Conta buscarContaPorNumero(Integer numero) { return null;}
    private void alterar(Integer numeroDaConta, BigDecimal valor) { }
}

