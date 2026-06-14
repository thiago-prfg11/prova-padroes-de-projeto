package classes;

import enums.SituacaoEnum;
import interfaces.EventoAtendimento;
import interfaces.ObservadorAtendimento;

public class AvisoTutor implements ObservadorAtendimento {

    private String ultimoAviso;

    @Override
    public void atualizar(EventoAtendimento evento) {
        if (evento.getSituacao() == SituacaoEnum.EM_ATENDIMENTO) {
            ultimoAviso = "Atenção Tutor " + evento.getNomeTutor()
                    + ": O atendimento de " + evento.getNomeAnimal() + " foi iniciado!";
        }
    }

    public String getUltimoAviso() {
        return ultimoAviso;
    }
}
