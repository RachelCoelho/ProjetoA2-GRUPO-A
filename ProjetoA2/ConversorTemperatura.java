package temperatura;

import java.util.Scanner;           // Para entrada de dados do usuário
import java.text.DecimalFormat;     // Para formatar a saída dos números decimais

public class ConversorTemperatura {

    // Array para armazenar o histórico das últimas 5 conversões
    private static Temperatura[] historico = new Temperatura[5];
    private static int contador = 0; // Controla a quantidade de conversões feitas
    private static DecimalFormat df = new DecimalFormat("#.##"); // Formata os números para 2 casas decimais

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner para entrada de dados do usuário

        while (true) {
            // Exibe o menu principal
            System.out.println("======= CONVERSOR DE TEMPERATURA =======");
            System.out.println("1. Converter temperatura");
            System.out.println("2. Ver histórico de conversões");
            System.out.println("3. Ver estatísticas");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner após a leitura do número

            if (opcao == 1) {
                // Leitura da temperatura de entrada
                System.out.print("Digite a temperatura: ");
                String entradaStr = scanner.nextLine().replace(",", "."); // Aceita vírgula ou ponto
                double valorEntrada = Double.parseDouble(entradaStr);

                // Unidade de origem da temperatura
                System.out.print("Unidade de origem (C, F, K): ");
                String unidadeOrigem = scanner.nextLine().toUpperCase();

                // Unidade de destino para conversão
                System.out.print("Unidade de destino (C, F, K): ");
                String unidadeDestino = scanner.nextLine().toUpperCase();

                // Cria o objeto da temperatura com base na unidade de origem
                Temperatura entrada;
                if (unidadeOrigem.equals("C")) {
                    entrada = new Celsius(valorEntrada);
                } else if (unidadeOrigem.equals("F")) {
                    entrada = new Fahrenheit(valorEntrada);
                } else if (unidadeOrigem.equals("K")) {
                    entrada = new Kelvin(valorEntrada);
                } else {
                    System.out.println("Unidade inválida! Usando Celsius como padrão.");
                    entrada = new Celsius(valorEntrada);
                }

                // Realiza a conversão usando a interface Conversao
                Conversao conversao = (Conversao) entrada;
                Temperatura convertida;

                // Converte para a unidade de destino escolhida
                if (unidadeDestino.equals("C")) {
                    convertida = new Celsius(conversao.converterParaCelsius());
                } else if (unidadeDestino.equals("F")) {
                    convertida = new Fahrenheit(conversao.converterParaFahrenheit());
                } else if (unidadeDestino.equals("K")) {
                    convertida = new Kelvin(conversao.converterParaKelvin());
                } else {
                    System.out.println("Unidade inválida! Convertendo para Celsius.");
                    convertida = new Celsius(conversao.converterParaCelsius());
                }

                // Exibe o resultado da conversão formatado
                System.out.println("Resultado da conversão:");
                System.out.println(df.format(entrada.getValor()) + " " + formatarUnidade(unidadeOrigem) +
                        " = " + df.format(convertida.getValor()) + " " + formatarUnidade(unidadeDestino));

                // Armazena no histórico circular (sobrescreve os mais antigos quando cheio)
                historico[contador % historico.length] = convertida;
                contador++;

            } else if (opcao == 2) {
                // Exibe o histórico de conversões
                verHistorico();

            } else if (opcao == 3) {
                // Exibe estatísticas com base no histórico
                verEstatisticas();

            } else if (opcao == 4) {
                // Encerra o programa
                System.out.println("Programa encerrado.");
                break;

            } else {
                // Opção inválida
                System.out.println("Opção inválida!");
            }
        }

        scanner.close(); // Fecha o scanner ao final
    }

    // Exibe o histórico de conversões armazenadas
    private static void verHistorico() {
        System.out.println("----- HISTÓRICO DE CONVERSÕES -----");

        if (contador == 0) {
            System.out.println("Nenhuma conversão realizada ainda.");
            return;
        }

        int total = Math.min(contador, historico.length);

        for (int i = 0; i < total; i++) {
            Temperatura t = historico[i];
            // Determina a unidade da temperatura com base na instância
            String unidade = t instanceof Celsius ? "°C" :
                             t instanceof Fahrenheit ? "°F" :
                             "K";
            System.out.println((i + 1) + ". " + df.format(t.getValor()) + " " + unidade);
        }
    }

    // Calcula e exibe estatísticas (menor, maior e média) das temperaturas no histórico
    private static void verEstatisticas() {
        System.out.println("----- ESTATÍSTICAS -----");

        if (contador == 0) {
            System.out.println("Nenhum dado disponível para estatísticas.");
            return;
        }

        int total = Math.min(contador, historico.length);

        // Inicializa os valores com a primeira entrada do histórico
        double menor = historico[0].getValor();
        double maior = menor;
        double soma = menor;

        for (int i = 1; i < total; i++) {
            if (historico[i] == null) continue; // Evita NullPointerException
            double valor = historico[i].getValor();
            if (valor < menor) menor = valor;
            if (valor > maior) maior = valor;
            soma += valor;
        }

        double media = soma / total;

        // Define a unidade de exibição com base na primeira entrada
        String unidade = historico[0] instanceof Celsius ? "°C" :
                         historico[0] instanceof Fahrenheit ? "°F" : "K";

        // Exibe os resultados
        System.out.println("Menor temperatura: " + df.format(menor) + " " + unidade);
        System.out.println("Maior temperatura: " + df.format(maior) + " " + unidade);
        System.out.println("Média das temperaturas: " + df.format(media) + " " + unidade);
    }

    // Converte a letra da unidade para o símbolo correspondente
    private static String formatarUnidade(String unidade) {
        switch (unidade) {
            case "C": return "°C";
            case "F": return "°F";
            case "K": return "K";
            default: return unidade;
        }
    }
}
