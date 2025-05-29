package temperatura; // Define o pacote onde a classe está localizada

// A classe Fahrenheit representa uma temperatura em graus Fahrenheit
// Ela estende a classe abstrata Temperatura e implementa a interface Conversao
public class Fahrenheit extends Temperatura implements Conversao {

    // Construtor que recebe um valor double e chama o construtor da superclasse
    public Fahrenheit(double valor) {
        super(valor);
    }

    // Construtor sobrecarregado que recebe o valor como String
    // Provavelmente a superclasse trata a conversão de String para double
    public Fahrenheit(String valorStr) {
        super(valorStr);
    }

    // Converte de Fahrenheit para Celsius
    // Fórmula: (°F - 32) × 5/9
    @Override
    public double converterParaCelsius() {
        return (getValor() - 32) * 5 / 9;
    }

    // Retorna o valor original, pois já está em Fahrenheit
    @Override
    public double converterParaFahrenheit() {
        return getValor();
    }

    // Converte de Fahrenheit para Kelvin
    // Fórmula: (°F - 32) × 5/9 + 273.15
    @Override
    public double converterParaKelvin() {
        return (getValor() - 32) * 5 / 9 + 273.15;
    }

    // Método genérico de conversão que, por padrão, retorna o valor em Fahrenheit
    @Override
    public double converter() {
        return converterParaFahrenheit();
    }
}

    @Override
    public double converterParaKelvin() {
        return (getValor() - 32) * 5 / 9 + 273.15;
    }

    @Override
    public double converter() {
        return converterParaCelsius();
    }
}
