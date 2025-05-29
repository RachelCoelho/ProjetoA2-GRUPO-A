package temperatura; // Define o pacote onde a classe está localizada

// Classe abstrata que serve como base para diferentes escalas de temperatura (Celsius, Fahrenheit, Kelvin)
public abstract class Temperatura {
    private double valor; // Armazena o valor numérico da temperatura

    // Construtor que recebe um valor double
    public Temperatura(double valor) {
        this.valor = valor;
    }

    // Construtor sobrecarregado que recebe o valor como String
    // Também substitui vírgulas por pontos para aceitar formatos brasileiros
    public Temperatura(String valorStr) {
        this.valor = Double.parseDouble(valorStr.replace(",", "."));
    }

    // Método getter: retorna o valor da temperatura
    public double getValor() {
        return valor;
    }

    // Método setter: define um novo valor numérico para a temperatura
    public void setValor(double valor) {
        this.valor = valor;
    }

    // Método setter sobrecarregado: define o valor a partir de uma String
    // Também permite entrada com vírgula como separador decimal
    public void setValor(String valorStr) {
        this.valor = Double.parseDouble(valorStr.replace(",", "."));
    }

    // Método abstrato que deve ser implementado pelas subclasses
    // Cada tipo de temperatura definirá como esse método se comporta
    public abstract double converter();
}
