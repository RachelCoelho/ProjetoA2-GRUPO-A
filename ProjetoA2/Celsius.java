package temperatura; // Define o pacote onde essa classe está localizada

// A classe Celsius estende a classe abstrata Temperatura e implementa a interface Conversao
public class Celsius extends Temperatura implements Conversao {

    // Construtor que recebe um valor do tipo double e chama o construtor da superclasse
    public Celsius(double valor) {
        super(valor);
    }

    // Construtor sobrecarregado que recebe um valor em formato de String
    // Também chama o construtor da superclasse, que provavelmente converte a String para double
    public Celsius(String valorStr) {
        super(valorStr);
    }

    // Implementa o método da interface para converter para Celsius
    // Como a temperatura já está em Celsius, apenas retorna o valor atual
    @Override
    public double converterParaCelsius() {
        return getValor(); // Método herdado da superclasse Temperatura
    }

    // Converte o valor de Celsius para Fahrenheit usando a fórmula: (°C × 9/5) + 32
    @Override
    public double converterParaFahrenheit() {
        return (getValor() * 9 / 5) + 32;
    }

    // Converte o valor de Celsius para Kelvin usando a fórmula: °C + 273,15
    @Override
    public double converterParaKelvin() {
        return getValor() + 273.15;
    }

    // Método genérico de conversão, que neste caso retorna o valor em Celsius
    @Override
    public double converter() {
        return converterParaCelsius();
    }
}
