package classes;

import interfaces.CalculadorValor;

public class DescontoAnimalAdotado implements CalculadorValor {

    private final CalculadorValor base;

    public DescontoAnimalAdotado(CalculadorValor base) {
        this.base = base;
    }

    @Override
    public double calcular() {
        return base.calcular() * (1 - Constantes.PERCENTUAL_DESCONTO_ANIMAL_ADOTADO);
    }
}
