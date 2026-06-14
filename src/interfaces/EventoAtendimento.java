package interfaces;

import enums.SituacaoEnum;

public interface EventoAtendimento {
    SituacaoEnum getSituacao();
    String getNomeAnimal();
    String getNomeTutor();
    String getNomeVeterinario();
}
