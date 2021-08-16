import java.time.*;
import java.util.*;

public class GerenciadorDeSalas {
    List<Sala> listaDeSalas = new LinkedList<>();
    Collection<Reserva> listaDeReservas = new LinkedList<>();

    GerenciadorDeSalas() {}

    public void adicionaSalaChamada(String nome, int capacidadeMaxima, String descricao) {
        adicionaSala(new Sala(nome, capacidadeMaxima, descricao));
    }

    public void removeSalaChamada(String nomeDaSala) {
        for(Sala s : listaDeSalas()) {
            if (s.getNome().equals(nomeDaSala)) {
                listaDeSalas.remove(s);
                return;
            }
        }
        System.out.println("Sala nao encontrada.");
    }

    public List<Sala> listaDeSalas() {
        return listaDeSalas;
    }

    public void adicionaSala(Sala novaSala) {
        listaDeSalas.add(novaSala);
    }

    public Reserva reservaSalaChamada(String nomeDaSala, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        for (Sala s : listaDeSalas) {
            if (s.getNome().equals(nomeDaSala)) {
                Reserva r = new Reserva(nomeDaSala, dataInicial, dataFinal);
                r.setSala(s);
                getListaDeReservas().add(r);
                return r;
            }
        }
        return null;
    }

    public void cancelaReserva(Reserva cancelada) {
        reservasParaSala(cancelada.nomeDaSala).remove(cancelada);
        getListaDeReservas().remove(cancelada);
    }

    public Collection<Reserva> reservasParaSala(String nomeSala) {
        List<Reserva> reservasDaSala = new LinkedList<>();
        for (Reserva r : getListaDeReservas()) {
            if (r.getNomeDaSala().equals(nomeSala)) reservasDaSala.add(r);
        }
        return reservasDaSala;
    }

    public void imprimeReservasDaSala(String nomeSala) {
        int i = 1;
        for(Reserva r : reservasParaSala(nomeSala)) {
            System.out.println("Sala: " + nomeSala);
            System.out.println("Reserva " + i + ":  I:" + r.getInicio() + " - F: " + r.getFim());
            i++;
        }
    }

    public List<Sala> getListaDeSalas() {
        return listaDeSalas;
    }

    public Collection<Reserva> getListaDeReservas() {
        return listaDeReservas;
    }
}
