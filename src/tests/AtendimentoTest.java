package tests;

import classes.*;
import enums.SituacaoEnum;
import interfaces.CalculadorValor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AtendimentoTest {

    private Tutor tutor;
    private Animal animal;
    private Animal animalAdotado;
    private ServicoVeterinario servico;
    private Veterinario veterinario;

    @BeforeEach
    void setUp() {
        tutor = new Tutor("Carlos", "carlos@email.com", "11999990000");
        animal = new Animal("Rex", "Cachorro", false);
        animalAdotado = new Animal("Bolinha", "Gato", true);
        servico = new ServicoVeterinario("Consulta Clínica", 200.0);
        veterinario = new Veterinario("Dra. Ana", "CRMV-12345");
    }

    @Test
    void deveIniciarAtendimentoAgendado() {
        Atendimento atendimento = new Atendimento(tutor, animal, veterinario, servico, new ValorBase(200.0));
        atendimento.iniciar();
        assertEquals(SituacaoEnum.EM_ATENDIMENTO, atendimento.getSituacaoAtual());
    }

    @Test
    void deveCancelarAtendimentoAgendado() {
        Atendimento atendimento = new Atendimento(tutor, animal, veterinario, servico, new ValorBase(200.0));
        atendimento.cancelar();
        assertEquals(SituacaoEnum.CANCELADO, atendimento.getSituacaoAtual());
    }

    @Test
    void deveFinalizarAtendimentoEmAndamento() {
        Atendimento atendimento = new Atendimento(tutor, animal, veterinario, servico, new ValorBase(200.0));
        atendimento.iniciar();
        atendimento.finalizar();
        assertEquals(SituacaoEnum.FINALIZADO, atendimento.getSituacaoAtual());
    }

    @Test
    void deveLancarExcecaoAoFinalizarAtendimentoAgendado() {
        Atendimento atendimento = new Atendimento(tutor, animal, veterinario, servico, new ValorBase(200.0));
        assertThrows(IllegalStateException.class, atendimento::finalizar);
    }

    @Test
    void deveLancarExcecaoAoIniciarAtendimentoEmAndamento() {
        Atendimento atendimento = new Atendimento(tutor, animal, veterinario, servico, new ValorBase(200.0));
        atendimento.iniciar();
        assertThrows(IllegalStateException.class, atendimento::iniciar);
    }

    @Test
    void deveLancarExcecaoAoCancelarAtendimentoEmAndamento() {
        Atendimento atendimento = new Atendimento(tutor, animal, veterinario, servico, new ValorBase(200.0));
        atendimento.iniciar();
        assertThrows(IllegalStateException.class, atendimento::cancelar);
    }

    @Test
    void deveLancarExcecaoAoCancelarAtendimentoFinalizado() {
        Atendimento atendimento = new Atendimento(tutor, animal, veterinario, servico, new ValorBase(200.0));
        atendimento.iniciar();
        atendimento.finalizar();
        assertThrows(IllegalStateException.class, atendimento::cancelar);
    }

    @Test
    void deveLancarExcecaoAoFinalizarAtendimentoCancelado() {
        Atendimento atendimento = new Atendimento(tutor, animal, veterinario, servico, new ValorBase(200.0));
        atendimento.cancelar();
        assertThrows(IllegalStateException.class, atendimento::finalizar);
    }

    @Test
    void deveLancarExcecaoAoIniciarAtendimentoCancelado() {
        Atendimento atendimento = new Atendimento(tutor, animal, veterinario, servico, new ValorBase(200.0));
        atendimento.cancelar();
        assertThrows(IllegalStateException.class, atendimento::iniciar);
    }

    @Test
    void deveLancarExcecaoAoIniciarAtendimentoFinalizado() {
        Atendimento atendimento = new Atendimento(tutor, animal, veterinario, servico, new ValorBase(200.0));
        atendimento.iniciar();
        atendimento.finalizar();
        assertThrows(IllegalStateException.class, atendimento::iniciar);
    }

    @Test
    void deveAvisarTutorQuandoAtendimentoForIniciado() {
        Atendimento atendimento = new Atendimento(tutor, animal, veterinario, servico, new ValorBase(200.0));
        AvisoTutor avisoTutor = new AvisoTutor();
        atendimento.adicionarObservador(avisoTutor);
        atendimento.iniciar();
        assertEquals("Atenção Tutor Carlos: O atendimento de Rex foi iniciado!", avisoTutor.getUltimoAviso());
    }

    @Test
    void deveAvisarVeterinarioQuandoAtendimentoForCancelado() {
        Atendimento atendimento = new Atendimento(tutor, animal, veterinario, servico, new ValorBase(200.0));
        AvisoVeterinario avisoVeterinario = new AvisoVeterinario();
        atendimento.adicionarObservador(avisoVeterinario);
        atendimento.cancelar();
        assertEquals("Atenção Veterinário Dra. Ana: O atendimento de Rex foi cancelado!", avisoVeterinario.getUltimoAviso());
    }

    @Test
    void deveAvisarRecepcaoQuandoAtendimentoForFinalizado() {
        Atendimento atendimento = new Atendimento(tutor, animal, veterinario, servico, new ValorBase(200.0));
        AvisoRecepcao avisoRecepcao = new AvisoRecepcao();
        atendimento.adicionarObservador(avisoRecepcao);
        atendimento.iniciar();
        atendimento.finalizar();
        assertEquals("Atenção Recepção: O atendimento de Rex foi finalizado!", avisoRecepcao.getUltimoAviso());
    }

    @Test
    void naoDeveAvisarTutorQuandoAtendimentoForCancelado() {
        Atendimento atendimento = new Atendimento(tutor, animal, veterinario, servico, new ValorBase(200.0));
        AvisoTutor avisoTutor = new AvisoTutor();
        atendimento.adicionarObservador(avisoTutor);
        atendimento.cancelar();
        assertNull(avisoTutor.getUltimoAviso());
    }

    @Test
    void naoDeveAvisarVeterinarioQuandoAtendimentoForFinalizado() {
        Atendimento atendimento = new Atendimento(tutor, animal, veterinario, servico, new ValorBase(200.0));
        AvisoVeterinario avisoVeterinario = new AvisoVeterinario();
        atendimento.adicionarObservador(avisoVeterinario);
        atendimento.iniciar();
        atendimento.finalizar();
        assertNull(avisoVeterinario.getUltimoAviso());
    }

    @Test
    void deveTodosObservadoresReceberemEventoNoMesmoAtendimento() {
        Atendimento atendimento = new Atendimento(tutor, animal, veterinario, servico, new ValorBase(200.0));
        AvisoTutor avisoTutor = new AvisoTutor();
        AvisoRecepcao avisoRecepcao = new AvisoRecepcao();
        atendimento.adicionarObservador(avisoTutor);
        atendimento.adicionarObservador(avisoRecepcao);
        atendimento.iniciar();
        atendimento.finalizar();
        assertEquals("Atenção Tutor Carlos: O atendimento de Rex foi iniciado!", avisoTutor.getUltimoAviso());
        assertEquals("Atenção Recepção: O atendimento de Rex foi finalizado!", avisoRecepcao.getUltimoAviso());
    }

    @Test
    void deveCalcularValorComDescontoAnimalAdotado() {
        CalculadorValor calculador = new DescontoAnimalAdotado(new ValorBase(200.0));
        Atendimento atendimento = new Atendimento(tutor, animalAdotado, veterinario, servico, calculador);
        assertEquals(180.0, atendimento.getValorFinal());
    }

    @Test
    void deveCalcularValorComTaxaDomiciliar() {
        CalculadorValor calculador = new TaxaAtendimentoDomiciliar(new ValorBase(200.0));
        Atendimento atendimento = new Atendimento(tutor, animal, veterinario, servico, calculador);
        assertEquals(250.0, atendimento.getValorFinal());
    }

    @Test
    void deveCalcularValorComBanhoPosConsulta() {
        CalculadorValor calculador = new BanhoPosConsulta(new ValorBase(200.0));
        Atendimento atendimento = new Atendimento(tutor, animal, veterinario, servico, calculador);
        assertEquals(230.0, atendimento.getValorFinal());
    }

    @Test
    void deveCalcularValorComMultiplasRegrasAplicadas() {
        CalculadorValor calculador = new BanhoPosConsulta(
                new TaxaAtendimentoDomiciliar(
                        new DescontoAnimalAdotado(
                                new ValorBase(200.0))));
        Atendimento atendimento = new Atendimento(tutor, animalAdotado, veterinario, servico, calculador);
        assertEquals(260.0, atendimento.getValorFinal());
    }

    @Test
    void deveAtualizarCalculadorEmAtendimentoAberto() {
        Atendimento atendimento = new Atendimento(tutor, animal, veterinario, servico, new ValorBase(200.0));
        atendimento.setCalculadorValor(new BanhoPosConsulta(new ValorBase(200.0)));
        assertEquals(230.0, atendimento.getValorFinal());
    }

    @Test
    void deveLancarExcecaoAoAlterarCalculadorDeAtendimentoFinalizado() {
        Atendimento atendimento = new Atendimento(tutor, animal, veterinario, servico, new ValorBase(200.0));
        atendimento.iniciar();
        atendimento.finalizar();
        assertThrows(IllegalStateException.class,
                () -> atendimento.setCalculadorValor(new BanhoPosConsulta(new ValorBase(200.0))));
    }

    @Test
    void deveLancarExcecaoAoAlterarCalculadorDeAtendimentoCancelado() {
        Atendimento atendimento = new Atendimento(tutor, animal, veterinario, servico, new ValorBase(200.0));
        atendimento.cancelar();
        assertThrows(IllegalStateException.class,
                () -> atendimento.setCalculadorValor(new TaxaAtendimentoDomiciliar(new ValorBase(200.0))));
    }
}
