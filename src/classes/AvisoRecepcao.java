package classes;

import enums.SituacaoEnum;
import interfaces.EventoAtendimento;
import interfaces.ObservadorAtendimento;

public class AvisoRecepcao implements ObservadorAtendimento {

    private String ultimoAviso;

    @Override
    public void atualizar(EventoAtendimento evento) {
        if (evento.getSituacao() == SituacaoEnum.FINALIZADO) {
            ultimoAviso = "Atenção Recepção: O atendimento de "
                    + evento.getNomeAnimal() + " foi finalizado!";
        }
    }

    public String getUltimoAviso() {
        return ultimoAviso;
    }
}
