import java.time.*;
import java.util.*;

public class MarcadorDeReuniao {
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private Collection<String> listaDeParticipante;
    private Map<String, List<Intervalo>> disponibilidades = new HashMap<>();

    MarcadorDeReuniao() {}

    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal, Collection<String> listaDeParticipantes) {
        if (validaDatas(dataInicial, dataFinal)) return;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.listaDeParticipante = listaDeParticipantes;
        inicializaDisponibilidades();
    }

    public void indicaDisponibilidadeDe(String participante, LocalDateTime inicio, LocalDateTime fim) {
        if (validaDatas(inicio, fim) || !(disponibilidades.containsKey(participante))) return;
        disponibilidades.get(participante).add(new Intervalo(inicio, fim));
        Collections.sort(disponibilidades.get(participante));
    }

    //incompleto
    public void mostraSobreposicao() {
        List<Intervalo> sobreposicoes = encontraSobreposicao();
        System.out.println("SOBREPOSICOES: ");
        for(Intervalo intervalo : sobreposicoes) {
            System.out.println("   I: " + intervalo.getDataInicial() + " - F: " + intervalo.getDataFinal());
        }

    }

    public void inicializaDisponibilidades() {
        for(String p : listaDeParticipante) {
            List<Intervalo> intervalos = new ArrayList<>();
            disponibilidades.put(p, intervalos);
        }
    }

    public List<Intervalo> encontraSobreposicao() {
        List<Intervalo> sobreposicoes = new ArrayList<>();
        List<Horario> disps = new LinkedList<>();
        LocalDateTime inicio = null;
        for(List<Intervalo> l : disponibilidades.values()) {
            for(Intervalo i : l) {
                disps.add(new Horario(i.getDataInicial(), "I"));
                disps.add(new Horario(i.getDataFinal(), "F"));
            }
        }
        Collections.sort(disps);
        System.out.println("DISPS:");
        for(Horario h : disps) {
            if (inicio == null || (inicio.isBefore(h.getHorario()) && h.getIdentificador().equals("I")))
                inicio = h.getHorario();
            if (h.getIdentificador().equals("F")) {
                sobreposicoes.add(new Intervalo(inicio, h.getHorario()));
            }
            System.out.println("T: " + h.getHorario());
        }
        return sobreposicoes;
    }
    
    public void exibeDisponibilidades() {
        for (String participante : listaDeParticipante) {
            System.out.println(participante);
            if (!disponibilidades.get(participante).isEmpty()) {
                for (Intervalo intervalo : disponibilidades.get(participante))
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

    public Map<String, List<Intervalo>> getdisponibilidades() {
        return disponibilidades;
    }
}
