import java.time.*;
import java.util.*;

public class MarcadorDeReuniao {
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private Collection<String> listaDeParticipante;
    private Map<String, List<Intervalo>> participantesDisp = new HashMap<>();

    MarcadorDeReuniao() {}

    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal, Collection<String> listaDeParticipantes) {
        if (validaDatas(dataInicial, dataFinal)) return;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.listaDeParticipante = listaDeParticipantes;
        inicializaDisponibilidades();
    }

    public void indicaDisponibilidadeDe(String participante, LocalDateTime inicio, LocalDateTime fim) {
        if (validaDatas(inicio, fim) || !(participantesDisp.containsKey(participante))) return;
        Intervalo intervalo = new Intervalo(inicio, fim);
        participantesDisp.get(participante).add(intervalo);
        Collections.sort(participantesDisp.get(participante));
    }

    public void mostraSobreposicao() {
        List<Intervalo> sobreposicoes = encontraSobreposicao();

    }

    public void inicializaDisponibilidades() {
        for(String p : listaDeParticipante) {
            List<Intervalo> intervalos = new ArrayList<>();
            participantesDisp.put(p, intervalos);
        }
    }

    public List<Intervalo> encontraSobreposicao() {
        List<Intervalo> sobreposicoes = new ArrayList<>();

        return sobreposicoes;
    }
    
    public void exibeParticipantesDisp() {
        for (String participante : listaDeParticipante) {
            System.out.println(participante);
            if (!participantesDisp.get(participante).isEmpty()) {
                for (Intervalo intervalo : participantesDisp.get(participante))
                    System.out.println("   I: " + intervalo.getDataInicial() + " - F: " + intervalo.getDataFinal());
            } else System.out.println("   null");
        }
    }

    public boolean validaDatas(Object inicio, Object fim) {
        LocalDateTime dataInicio;
        LocalDateTime dataFim;
        try {
            dataInicio = (LocalDateTime) inicio;
            dataFim = (LocalDateTime) fim;
        } catch (Exception e) {
            dataInicio = LocalDateTime.parse(inicio.toString()+"T00:00");
            dataFim = LocalDateTime.parse(fim.toString()+"T00:00");
        }
        return (dataInicio.equals(dataFim) || dataInicio.isAfter(dataFim));
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public Collection<String> getListaDeParticipante() {
        return listaDeParticipante;
    }

    public Map<String, List<Intervalo>> getParticipantesDisp() {
        return participantesDisp;
    }
}
