package temperatura; // Define o pacote onde a classe está inserida

// A classe Celsius representa temperaturas em graus Celsius.
// Ela estende (herda) da classe abstrata Temperatura e implementa a interface Conversao.
public class Celsius extends Temperatura implements Conversao {

    // Construtor que recebe um valor double (ex: 25.0 °C)
    public Celsius(double valor) {
        super(valor); // Chama o construtor da superclasse Temperatura para armazenar o valor
    }

    // Construtor sobrecarregado que recebe um valor como String (ex: "25,5" ou "25.5")
    public Celsius(String valorStr) {
        super(valorStr); // Converte a String para double e armazena na superclasse
    }

    // Método que retorna o valor em Celsius (sem conversão, já está em Celsius)
    @Override
    public double converterParaCelsius() {
        return getValor(); // Retorna diretamente o valor armazenado
    }

    // Método que converte Celsius para Fahrenheit
    @Override
    public double converterParaFahrenheit() {
        return (getValor() * 9 / 5) + 32; // Fórmula de conversão: (C × 9/5) + 32
    }

    // Método que converte Celsius para Kelvin
    @Override
    public double converterParaKelvin() {
        return getValor() + 273.15; // Fórmula de conversão: C + 273.15
    }

    // Implementação do método abstrato da classe Temperatura
    // Aqui, definimos que o método converter() retorna o valor em Celsius
    @Override
    public double converter() {
        return converterParaCelsius(); // Retorna o valor em Celsius como padrão
    }
}
