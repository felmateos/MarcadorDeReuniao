import java.time.*;
import java.util.*;

public class MarcadorDeReuniao {
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private Collection<String> listaDeParticipante;
    final private Map<String, List<Disponibilidade>> participantesDisp = new HashMap<>();


    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal, Collection<String> listaDeParticipantes) {
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.listaDeParticipante = listaDeParticipantes;
    }

    public void indicaDisponibilidadeDe(String participante, LocalDateTime inicio, LocalDateTime fim) {
        Disponibilidade disponibilidade = new Disponibilidade(inicio, fim);
        if (!participantesDisp.containsKey(participante)) {
            List<Disponibilidade> disponibilidades = new ArrayList<>();
            disponibilidades.add(disponibilidade);
            participantesDisp.put(participante, disponibilidades);
        } else {
            participantesDisp.get(participante).add(disponibilidade);
        }
    }

    public void mostraSobreposicao() {

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
}
