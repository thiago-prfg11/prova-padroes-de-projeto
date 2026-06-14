package classes;

import enums.SituacaoEnum;
import interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class Atendimento implements ContextoAtendimento {

    private final Tutor tutor;
    private final Animal animal;
    private final Veterinario veterinario;
    private final ServicoVeterinario servico;
    private SituacaoAtendimento situacao;
    private CalculadorValor calculadorValor;
    private final List<ObservadorAtendimento> observadores = new ArrayList<>();

    public Atendimento(Tutor tutor, Animal animal, Veterinario veterinario, ServicoVeterinario servico, CalculadorValor calculadorValor) {
        this.tutor = tutor;
        this.animal = animal;
        this.veterinario = veterinario;
        this.servico = servico;
        this.calculadorValor = calculadorValor;
        this.situacao = new SituacaoAgendado();
    }

    public void adicionarObservador(ObservadorAtendimento observador) {
        observadores.add(observador);
    }

    @Override
    public void setSituacao(SituacaoAtendimento situacao) {
        this.situacao = situacao;
        notificarObservadores();
    }

    @Override
    public void aplicarCalculador(CalculadorValor calculadorValor) {
        this.calculadorValor = calculadorValor;
    }

    public void setCalculadorValor(CalculadorValor calculadorValor) {
        situacao.alterarCalculador(this, calculadorValor);
    }

    private void notificarObservadores() {
        EventoAtendimento evento = new DadosEvento(
                situacao.getSituacao(),
                animal.getNome(),
                tutor.getNome(),
                veterinario.getNome()
        );
        for (ObservadorAtendimento observador : observadores) {
            observador.atualizar(evento);
        }
    }

    public void iniciar() {
        situacao.iniciar(this);
    }

    public void finalizar() {
        situacao.finalizar(this);
    }

    public void cancelar() {
        situacao.cancelar(this);
    }

    @Override
    public double getValorFinal() {
        return calculadorValor.calcular();
    }

    public SituacaoEnum getSituacaoAtual() {
        return situacao.getSituacao();
    }

    public Tutor getTutor() {
        return tutor;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public ServicoVeterinario getServico() {
        return servico;
    }
}
