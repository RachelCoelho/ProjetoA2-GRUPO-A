package temperatura; // Define o pacote onde a interface está localizada

// A interface Conversao define o contrato para classes que implementam conversões de temperatura
public interface Conversao {

    // Método para converter a temperatura para Celsius
    double converterParaCelsius();

    // Método para converter a temperatura para Fahrenheit
    double converterParaFahrenheit();

    // Método para converter a temperatura para Kelvin
    double converterParaKelvin();
}
