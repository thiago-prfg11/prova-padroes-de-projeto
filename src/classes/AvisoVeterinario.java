package classes;

import enums.SituacaoEnum;
import interfaces.EventoAtendimento;
import interfaces.ObservadorAtendimento;

public class AvisoVeterinario implements ObservadorAtendimento {

    private String ultimoAviso;

    @Override
    public void atualizar(EventoAtendimento evento) {
        if (evento.getSituacao() == SituacaoEnum.CANCELADO) {
            ultimoAviso = "Atenção Veterinário " + evento.getNomeVeterinario()
                    + ": O atendimento de " + evento.getNomeAnimal() + " foi cancelado!";
        }
    }

    public String getUltimoAviso() {
        return ultimoAviso;
    }
}
