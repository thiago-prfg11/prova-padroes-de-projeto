package classes;

import enums.SituacaoEnum;
import interfaces.EventoAtendimento;

public class DadosEvento implements EventoAtendimento {

    private final SituacaoEnum situacao;
    private final String nomeAnimal;
    private final String nomeTutor;
    private final String nomeVeterinario;

    public DadosEvento(SituacaoEnum situacao, String nomeAnimal, String nomeTutor, String nomeVeterinario) {
        this.situacao = situacao;
        this.nomeAnimal = nomeAnimal;
        this.nomeTutor = nomeTutor;
        this.nomeVeterinario = nomeVeterinario;
    }

    @Override
    public SituacaoEnum getSituacao() {
        return situacao;
    }

    @Override
    public String getNomeAnimal() {
        return nomeAnimal;
    }

    @Override
    public String getNomeTutor() {
        return nomeTutor;
    }

    @Override
    public String getNomeVeterinario() {
        return nomeVeterinario;
    }
}
