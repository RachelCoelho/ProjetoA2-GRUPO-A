package temperatura; // Define o pacote onde a classe está inserida

// A classe Kelvin representa uma temperatura na escala Kelvin.
// Ela herda da classe abstrata Temperatura e implementa a interface Conversao.
public class Kelvin extends Temperatura implements Conversao {

    // Construtor que recebe um valor double e o repassa para a superclasse Temperatura
    public Kelvin(double valor) {
        super(valor);
    }

    // Construtor sobrecarregado que recebe o valor como String
    // A superclasse é responsável por converter a String para double
    public Kelvin(String valorStr) {
        super(valorStr);
    }

    // Converte de Kelvin para Celsius
    // Fórmula: K - 273.15
    @Override
    public double converterParaCelsius() {
        return getValor() - 273.15;
    }

    // Converte de Kelvin para Fahrenheit
    // Fórmula: (K - 273.15) × 9/5 + 32
    @Override
    public double converterParaFahrenheit() {
        return (getValor() - 273.15) * 9 / 5 + 32;
    }

    // Retorna o valor original, pois já está em Kelvin
    @Override
    public double converterParaKelvin() {
        return getValor();
    }

    // Método genérico de conversão (usado como padrão para retornar o valor em Celsius)
    @Override
    public double converter() {
        return converterParaCelsius();
    }
}
