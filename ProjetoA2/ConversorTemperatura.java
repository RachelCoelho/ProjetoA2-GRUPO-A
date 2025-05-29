package temperatura;

import java.util.Scanner;           // Importa a classe Scanner para entrada de dados do usuário
import java.text.DecimalFormat;    // Importa DecimalFormat para formatar números com duas casas decimais

public class ConversorTemperatura {
    // Array para armazenar o histórico das últimas 5 conversões
    private static Temperatura[] historico = new Temperatura[5];
    // Contador para controlar a posição atual no histórico
    private static int contador = 0;
    // Objeto para formatar os números com até 2 casas decimais
    private static DecimalFormat df = new DecimalFormat("#.##");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Cria um Scanner para ler entrada do usuário

        while (true) {
            // Menu principal
            System.out.println("======= CONVERSOR DE TEMPERATURA =======");
            System.out.println("1. Converter temperatura");
            System.out.println("2. Ver histórico de conversões");
            System.out.println("3. Ver estatísticas");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt(); // Lê a opção do usuário
            scanner.nextLine(); // Limpa o buffer do Scanner

            if (opcao == 1) {
                // Lê o valor da temperatura e trata a vírgula como ponto
                System.out.print("Digite a temperatura: ");
                String entradaStr = scanner.nextLine().replace(",", ".");
                double valorEntrada = Double.parseDouble(entradaStr);

                // Lê a unidade de origem
                System.out.print("Unidade de origem (C, F, K): ");
                String unidadeOrigem = scanner.nextLine().toUpperCase();

                // Lê a unidade de destino
                System.out.print("Unidade de destino (C, F, K): ");
                String unidadeDestino = scanner.nextLine().toUpperCase();

                // Cria o objeto de temperatura com base na unidade de origem
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

                // Converte a temperatura para a unidade de destino
                Conversao conversao = (Conversao) entrada;
                Temperatura convertida;

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

                // Armazena a conversão no histórico (usando lógica circular)
                historico[contador % historico.length] = convertida;
                contador++;

            } else if (opcao == 2) {
                // Exibe o histórico de conversões
                verHistorico();
            } else if (opcao == 3) {
                // Exibe as estatísticas (menor, maior e média)
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

        scanner.close(); // Fecha o Scanner
    }

    // Exibe o histórico das últimas conversões feitas
    private static void verHistorico() {
        System.out.println("----- HISTÓRICO DE CONVERSÕES -----");
        if (contador == 0) {
            System.out.println("Nenhuma conversão realizada ainda.");
            return;
        }

        int total = Math.min(contador, historico.length); // Garante que não passe do limite

        for (int i = 0; i < total; i++) {
            Temperatura t = historico[i];
            // Define a unidade com base na instância da classe
            String unidade = t instanceof Celsius ? "°C" :
                             t instanceof Fahrenheit ? "°F" :
                             "K";
            // Exibe o valor e unidade
            System.out.println((i + 1) + ". " + df.format(t.getValor()) + " " + unidade);
        }
    }

    // Exibe estatísticas com base no histórico: menor, maior e média das temperaturas
    private static void verEstatisticas() {
        System.out.println("----- ESTATÍSTICAS -----");
        if (contador == 0) {
            System.out.println("Nenhum dado disponível para estatísticas.");
            return;
        }

        int total = Math.min(contador, historico.length);

        double menor = historico[0].getValor(); // Inicializa com o primeiro valor
        double maior = menor;
        double soma = menor;

        for (int i = 1; i < total; i++) {
            if (historico[i] == null) continue;
            double valor = historico[i].getValor();
            if (valor < menor) menor = valor;
            if (valor > maior) maior = valor;
            soma += valor;
        }

        double media = soma / total;

        // Define a unidade da primeira entrada do histórico
        String unidade = historico[0] instanceof Celsius ? "°C" :
                         historico[0] instanceof Fahrenheit ? "°F" : "K";

        // Exibe os resultados
        System.out.println("Menor temperatura: " + df.format(menor) + " " + unidade);
        System.out.println("Maior temperatura: " + df.format(maior) + " " + unidade);
        System.out.println("Média das temperaturas: " + df.format(media) + " " + unidade);
    }

    // Converte a letra da unidade para símbolo apropriado
    private static String formatarUnidade(String unidade) {
        switch (unidade) {
            case "C": return "°C";
            case "F": return "°F";
            case "K": return "K";
            default: return unidade;
        }
    }
}
