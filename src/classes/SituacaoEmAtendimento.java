package classes;

import enums.SituacaoEnum;
import interfaces.CalculadorValor;
import interfaces.ContextoAtendimento;
import interfaces.SituacaoAtendimento;

public class SituacaoEmAtendimento implements SituacaoAtendimento {

    @Override
    public void iniciar(ContextoAtendimento contexto) {
        throw new IllegalStateException("Atendimento já em andamento.");
    }

    @Override
    public void finalizar(ContextoAtendimento contexto) {
        contexto.setSituacao(new SituacaoFinalizado());
    }

    @Override
    public void cancelar(ContextoAtendimento contexto) {
        throw new IllegalStateException("Um atendimento em andamento não pode ser cancelado.");
    }

    @Override
    public void alterarCalculador(ContextoAtendimento contexto, CalculadorValor calculadorValor) {
        contexto.aplicarCalculador(calculadorValor);
    }

    @Override
    public SituacaoEnum getSituacao() {
        return SituacaoEnum.EM_ATENDIMENTO;
    }
}
