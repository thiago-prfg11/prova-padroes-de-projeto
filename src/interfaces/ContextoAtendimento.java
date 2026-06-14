package interfaces;

public interface ContextoAtendimento {
    void setSituacao(SituacaoAtendimento situacao);
    void aplicarCalculador(CalculadorValor calculadorValor);
    double getValorFinal();
}
