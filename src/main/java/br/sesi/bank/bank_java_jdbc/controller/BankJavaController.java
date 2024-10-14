package br.sesi.bank.bank_java_jdbc.controller;

import br.sesi.bank.bank_java_jdbc.domain.cliente.DadosCadastroCliente;
import br.sesi.bank.bank_java_jdbc.domain.conta.DadosAberturaConta;
import br.sesi.bank.bank_java_jdbc.exceptions.RegraDeNegocioException;
import br.sesi.bank.bank_java_jdbc.service.ContaService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;

public class BankJavaController {
    ContaService service;
    Scanner teclado;
    public BankJavaController() {
        this.service = new ContaService();
        this.teclado = new Scanner(System.in).useDelimiter("/n");
    }
    public static void main(String[] args) throws SQLException {
        BankJavaController controller = new BankJavaController();
        controller.start();
    }
    public void start() throws SQLException {
        Scanner teclado = new Scanner(System.in).useDelimiter("\n");

        var opcao = exibirMenu();
        while (opcao != 8) {
            try {
                switch (opcao) {
                    case 1:
                        listarContas();
                        break;
                    case 2:
                        abrirConta();
                        break;
                    case 3:
                        encerrarConta();
                        break;
                    case 4:
                        consultarSaldo();
                        break;
                    case 5:
                        realizarSaque();
                        break;
                    case 6:
                        realizarDeposito();
                        break;
                    case 7:
                        realizarTransferencia();
                        break;
                }
            } catch (RegraDeNegocioException e) {
                System.out.println("Erro: " +e.getMessage());
                System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu");
                teclado.next();
            }
            opcao = exibirMenu();
        }
        System.out.println("Finalizando a aplicação");
    }
    private int exibirMenu() {
        System.out.println("""
                BYTEBANK - ESCOLHA UMA OPÇÂO:
                1 - Listar contas abertas
                2 - Abertura de conta
                3 - Encerramento de conta
                4 - Consultar saldo de uma conta
                5 - Realizar saque de uma conta
                6 - Realizar deposito de uma conta
                7 - Realizar uma transferência
                8 - Sair
                """);
        return teclado.nextInt();
    }
    private void listarContas() {
        System.out.println("Contas cadastradas");
        var contas = service.listarContasAbertas();
        contas.stream().forEach(System.out::println);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
    }
    private void abrirConta() throws SQLException {
        System.out.println("Digite o numero da conta");
        var numeroDaConta = teclado.nextInt();

        System.out.println("Digite o nome do cliente");
        var nome = teclado.next();

        System.out.println("Digite o CPF do cliente");
        var cpf = teclado.next();

        System.out.println("Digite o email do cliente");
        var email = teclado.next();

        service.abrir(new DadosAberturaConta(numeroDaConta, new DadosCadastroCliente(nome, cpf, email)));

        System.out.println("Conta aberta com sucesso!");
        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();

    }
    private void encerrarConta() {
        System.out.println("Digite o numero da conta:");
        var numeroDaConta = teclado.nextInt();

        service.encerrar(numeroDaConta);

        System.out.println("Conta encerrada com sucesso!");
        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
    }
    private void consultarSaldo() {
        System.out.println("Digite o numero da conta");
        var numeroDaConta = teclado.nextInt();
        var saldo = service.consultarSaldo(numeroDaConta);
        System.out.println("Saldo da conta: " +saldo);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
    }
    private void realizarSaque() {
        System.out.println("Digite o numero da Conta:");
        var numeroDaConta = teclado.nextInt();

        System.out.println("Digite o valor do saque:");
        var valor = teclado.nextBigDecimal();

        service.realizarSaque(numeroDaConta, valor);

        System.out.println("Saque realizado com sucesso!");
        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
    }
    private void realizarDeposito() {
        System.out.println("Digite o numero da Conta:");
        var numeroDaConta = teclado.nextInt();

        System.out.println("Digite o valor do saque:");
        var valor = teclado.nextBigDecimal();

        service.realizarDeposito(numeroDaConta, valor);

        System.out.println("Deposito realizado com sucesso!");
        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();


    }
    private void realizarTransferencia() {
        System.out.println("Digite o numero da conta de ORIGEM: ");
        int numeroContaOrigem = teclado.nextInt();
        System.out.println("Digite o numerp da conta de DESTINO: ");
        int numeroContaDestino = teclado.nextInt();
        System.out.println("Informe o valor a ser transferido: ");
        BigDecimal valor = teclado.nextBigDecimal();

        this.service.realizaTransferencia(numeroContaOrigem, numeroContaDestino, valor);
        System.out.println("Transferencia realizado com sucesso!");
        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
    }
}