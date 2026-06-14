package classes;

import interfaces.CalculadorValor;

public class TaxaAtendimentoDomiciliar implements CalculadorValor {

    private final CalculadorValor base;

    public TaxaAtendimentoDomiciliar(CalculadorValor base) {
        this.base = base;
    }

    @Override
    public double calcular() {
        return base.calcular() + Constantes.TAXA_ATENDIMENTO_DOMICILIAR;
    }
}
