package classes;

import enums.SituacaoEnum;
import interfaces.CalculadorValor;
import interfaces.ContextoAtendimento;
import interfaces.SituacaoAtendimento;

public class SituacaoAgendado implements SituacaoAtendimento {

    @Override
    public void iniciar(ContextoAtendimento contexto) {
        contexto.setSituacao(new SituacaoEmAtendimento());
    }

    @Override
    public void finalizar(ContextoAtendimento contexto) {
        throw new IllegalStateException("Um atendimento agendado não pode ser diretamente finalizado.");
    }

    @Override
    public void cancelar(ContextoAtendimento contexto) {
        contexto.setSituacao(new SituacaoCancelado());
    }

    @Override
    public void alterarCalculador(ContextoAtendimento contexto, CalculadorValor calculadorValor) {
        contexto.aplicarCalculador(calculadorValor);
    }

    @Override
    public SituacaoEnum getSituacao() {
        return SituacaoEnum.AGENDADO;
    }
}
