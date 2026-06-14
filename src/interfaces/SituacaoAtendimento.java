package interfaces;

import enums.SituacaoEnum;

public interface SituacaoAtendimento {
    void iniciar(ContextoAtendimento contexto);
    void finalizar(ContextoAtendimento contexto);
    void cancelar(ContextoAtendimento contexto);
    void alterarCalculador(ContextoAtendimento contexto, CalculadorValor calculadorValor);
    SituacaoEnum getSituacao();
}
