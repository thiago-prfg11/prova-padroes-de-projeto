package classes;

import enums.SituacaoEnum;
import interfaces.CalculadorValor;
import interfaces.ContextoAtendimento;
import interfaces.SituacaoAtendimento;

public class SituacaoCancelado implements SituacaoAtendimento {

    @Override
    public void iniciar(ContextoAtendimento contexto) {
        throw new IllegalStateException("Um atendimento cancelado não pode ser reiniciado.");
    }

    @Override
    public void finalizar(ContextoAtendimento contexto) {
        throw new IllegalStateException("Um atendimento cancelado não pode ser finalizado.");
    }

    @Override
    public void cancelar(ContextoAtendimento contexto) {
        throw new IllegalStateException("Atendimento já cancelado.");
    }

    @Override
    public void alterarCalculador(ContextoAtendimento contexto, CalculadorValor calculadorValor) {
        throw new IllegalStateException("Não é possível alterar o calculador de um atendimento encerrado.");
    }

    @Override
    public SituacaoEnum getSituacao() {
        return SituacaoEnum.CANCELADO;
    }
}
