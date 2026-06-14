package classes;

import interfaces.CalculadorValor;

public class BanhoPosConsulta implements CalculadorValor {

    private final CalculadorValor base;

    public BanhoPosConsulta(CalculadorValor base) {
        this.base = base;
    }

    @Override
    public double calcular() {
        return base.calcular() + Constantes.TAXA_BANHO_POS_CONSULTA;
    }
}