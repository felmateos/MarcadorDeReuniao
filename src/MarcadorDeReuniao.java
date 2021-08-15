import java.time.*;
import java.util.*;

public class MarcadorDeReuniao {
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private Collection<String> listaDeParticipante;
    private Map<String, List<Intervalo>> disponibilidades = new HashMap<>();

    MarcadorDeReuniao() {}

    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal, Collection<String> listaDeParticipantes) {
        if (validaDatas(dataInicial, dataFinal)) return; //metodo de erro
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.listaDeParticipante = listaDeParticipantes;
        inicializaDisponibilidades();
    }

    public void inicializaDisponibilidades() {
        for(String p : getListaDeParticipante()) {
            List<Intervalo> intervalos = new ArrayList<>();
            getdisponibilidades().put(p, intervalos);
        }
    }

    public void indicaDisponibilidadeDe(String participante, LocalDateTime inicio, LocalDateTime fim) {
        if (verficaDatas(inicio, fim) && validaDatas(inicio, fim) || !(getdisponibilidades().containsKey(participante))) return; //metodo de erro
        getdisponibilidades().get(participante).add(new Intervalo(inicio, fim));
        Collections.sort(getdisponibilidades().get(participante));
    }

    public void mostraSobreposicao() {
        List<Intervalo> sobreposicoes = encontraSobreposicao();
        if (!sobreposicoes.isEmpty()) {
            System.out.println("SOBREPOSICOES: ");
            for(Intervalo intervalo : sobreposicoes) {
                System.out.println("   I: " + intervalo.getDataInicial() + " - F: " + intervalo.getDataFinal());
            }
        } else System.out.println("Nao existe um intervalo de tempo onde todos os participantes possam comparecer a reuniao.");
    }

    public List<Intervalo> encontraSobreposicao() {
        List<Intervalo> sobreposicoes = new LinkedList<>();
        LinkedList<Horario> horarios = addHorarios();
        LocalDateTime inicio = horarios.getFirst().getHorario();
        boolean valido = false;
        System.out.println("HORARIOS:");
        for(Horario h : horarios) {
            System.out.print("H: " + h.getHorario());
            if (h.getIdentificador().equals("I") && inicio.isBefore(h.getHorario())) {
                inicio = h.getHorario();
                System.out.print(" I");
                valido = true;
            } else if (h.getIdentificador().equals("F") && valido) {
                sobreposicoes.add(new Intervalo(inicio, h.getHorario()));
                System.out.print(" F");
                valido = false;
            }
            System.out.println();
        }
        return sobreposicoes;
    }

    public LinkedList<Horario> addHorarios() {
        LinkedList<Horario> horarios = new LinkedList<>();
        for(String s : getListaDeParticipante()) {
            for(Intervalo i : getdisponibilidades().get(s)) {
                horarios.add(new Horario(i.getDataInicial(), "I"));
                horarios.add(new Horario(i.getDataFinal(), "F"));
            }
        }
        Collections.sort(horarios);
        return horarios;
    }

    public void exibeDisponibilidades() {
        for (String participante : getListaDeParticipante()) {
            System.out.println(participante);
            if (!getdisponibilidades().get(participante).isEmpty()) {
                for (Intervalo intervalo : getdisponibilidades().get(participante))
                    System.out.println("   I: " + intervalo.getDataInicial() + " - F: " + intervalo.getDataFinal());
            } else System.out.println("   sem disponibilidades");
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
            dataFim = LocalDateTime.parse(fim.toString()+"T23:59");
        }
        return (!dataInicio.equals(dataFim) && !dataInicio.isAfter(dataFim));
    }

    public boolean verficaDatas(LocalDateTime inicio, LocalDateTime fim) {
        LocalDateTime inicioReuniao = LocalDateTime.parse(getDataInicial().toString()+"T00:00");
        LocalDateTime fimReuniao = LocalDateTime.parse(getDataFinal().toString()+"T23:59");
        return (inicio.isAfter(inicioReuniao) && fim.isBefore(fimReuniao));
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