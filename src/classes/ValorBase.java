package classes;

import interfaces.CalculadorValor;

public class ValorBase implements CalculadorValor {

    private final double valor;

    public ValorBase(double valor) {
        this.valor = valor;
    }

    @Override
    public double calcular() {
        return valor;
    }
}
