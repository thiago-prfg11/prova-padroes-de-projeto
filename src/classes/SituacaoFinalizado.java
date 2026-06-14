package classes;

import enums.SituacaoEnum;
import interfaces.CalculadorValor;
import interfaces.ContextoAtendimento;
import interfaces.SituacaoAtendimento;

public class SituacaoFinalizado implements SituacaoAtendimento {

    @Override
    public void iniciar(ContextoAtendimento contexto) {
        throw new IllegalStateException("Um atendimento finalizado não pode ser reiniciado.");
    }

    @Override
    public void finalizar(ContextoAtendimento contexto) {
        throw new IllegalStateException("O atendimento já foi finalizado.");
    }

    @Override
    public void cancelar(ContextoAtendimento contexto) {
        throw new IllegalStateException("Um atendimento finalizado não pode ser cancelado.");
    }

    @Override
    public void alterarCalculador(ContextoAtendimento contexto, CalculadorValor calculadorValor) {
        throw new IllegalStateException("Não é possível alterar o calculador de um atendimento encerrado.");
    }

    @Override
    public SituacaoEnum getSituacao() {
        return SituacaoEnum.FINALIZADO;
    }
}
