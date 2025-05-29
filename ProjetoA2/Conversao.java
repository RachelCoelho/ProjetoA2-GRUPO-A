package temperatura; // Define o pacote onde a interface está localizada

// A interface Conversao define os métodos que uma classe de temperatura deve implementar
// para permitir a conversão entre diferentes escalas de temperatura.
public interface Conversao {

    // Método para converter uma temperatura para Celsius
    double converterParaCelsius();

    // Método para converter uma temperatura para Fahrenheit
    double converterParaFahrenheit();

    // Método para converter uma temperatura para Kelvin
    double converterParaKelvin();
}
