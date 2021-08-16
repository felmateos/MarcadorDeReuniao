import java.time.*;
import java.util.*;

public class GerenciadorDeSalas {
    List<Sala> listaDeSalas = new LinkedList<>();

    public void adicionaSalaChamada(String nome, int capacidadeMaxima, String descricao) {
        adicionaSala(new Sala(nome, capacidadeMaxima, descricao));
    }

    public void removeSalaChamada(String nomeDaSala) {
        for(Sala s : listaDeSalas()) {
            if (s.getNome().equals(nomeDaSala)) listaDeSalas.remove(s);
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
        return null;
    }

    public void cancelaReserva(Reserva cancelada) {
    }

    public Collection<Reserva> reservasParaSala(String nomeSala) {
        return null;
    }

    public void imprimeReservasDaSala(String nomeSala) {
    }


}
