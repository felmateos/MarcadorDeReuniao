import java.time.*;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        //teste0();
        gerenciarReuniao(); // opcional
    }

    public static void gerenciarReuniao() {
        LocalDate inicioReuniao;
        LocalDate fimReuniao;
        int i = 1;
        int escolha;
        List<String> participantes = new LinkedList<>();
        MarcadorDeReuniao reuniao = new MarcadorDeReuniao();
        System.out.println("Quando a reunião terá inicio?");
        inicioReuniao = LocalDate.parse(scanner.nextLine());
        System.out.println("Quando a reunião terá fim?");
        fimReuniao = LocalDate.parse(scanner.nextLine());
        System.out.println("Quantos participantes a reunião terá?");
        int qntParticipantes = Integer.parseInt(scanner.nextLine());
        while (i <= qntParticipantes) {
            System.out.println("Qual o nome do " + i + "º participante?");
            participantes.add(scanner.nextLine());
            i++;
        }
        reuniao.marcarReuniaoEntre(inicioReuniao, fimReuniao, participantes);
        while (true) {
            System.out.println("Escolha o numero correspondente a ação desejada.");
            escolha = Integer.parseInt(scanner.nextLine());
            if(escolha == 1) indicarDisponibilidade(reuniao);
            else if(escolha == 2) reuniao.exibeDisponibilidades();
            else if(escolha == 3) reuniao.mostraSobreposicao();
            else if(escolha == 4) gerenciarSala();
            else if(escolha == 5)break;
        }
    }

    public static void indicarDisponibilidade(MarcadorDeReuniao reuniao) {
        System.out.println("Digite o nome do participante.");
        String participante = scanner.nextLine();
        System.out.println("Digite a data inicial da disponibilidade.");
        LocalDateTime inicio = LocalDateTime.parse(scanner.nextLine());
        System.out.println("Digite a data final da disponibilidade.");
        LocalDateTime fim = LocalDateTime.parse(scanner.nextLine());
        reuniao.indicaDisponibilidadeDe(participante, inicio, fim);
    }

    public static void gerenciarSala() {
        GerenciadorDeSalas gerenciadorDeSalas = new GerenciadorDeSalas();
        int escolha;
        while (true) {
            System.out.println("Escolha o numero correspondente a ação desejada.");
            escolha = Integer.parseInt(scanner.nextLine());
            if(escolha == 1) adicionarSalaChamada(gerenciadorDeSalas);
            else if(escolha == 2) removerSalaChamada(gerenciadorDeSalas);
            else if(escolha == 3) imprimirListaDeSalas(gerenciadorDeSalas);
            else if(escolha == 4) gerenciarReservas(gerenciadorDeSalas);
            else if(escolha == 5) break;
        }
    }

    public static void adicionarSalaChamada(GerenciadorDeSalas gerenciadorDeSalas) {
        System.out.println("Qual o nome da sala?");
        String nomeSala = scanner.nextLine();
        System.out.println("Qual a capacidade máxima da sala?");
        int capacidade = Integer.parseInt(scanner.nextLine());
        System.out.println("Digite as observacoes referentes a sala.");
        String observacoes = scanner.nextLine();
        gerenciadorDeSalas.adicionaSalaChamada(nomeSala, capacidade, observacoes);
    }

    public static void removerSalaChamada(GerenciadorDeSalas gerenciadorDeSalas) {
        System.out.println("Digite o nome da sala a ser removida.");
        gerenciadorDeSalas.removeSalaChamada(scanner.nextLine());
    }

    public static void imprimirListaDeSalas(GerenciadorDeSalas gerenciadorDeSalas) {
        for(Sala s : gerenciadorDeSalas.listaDeSalas()) {
            System.out.println("Sala: " + s.getNome() + "Capacidade Maxima: " + s.getCapacidadeMaxima() + " Observacoes: " + s.getObservacoes() + " Local: " + s.getLocal());
        }
    }

    public static void gerenciarReservas(GerenciadorDeSalas gerenciadorDeSalas) {
        int escolha;
        while (true) {
            System.out.println("Escolha o numero correspondente a ação desejada.");
            escolha = Integer.parseInt(scanner.nextLine());
            if(escolha == 1) reservarSalaChamada(gerenciadorDeSalas);
            else if(escolha == 2) cancelarReseva(gerenciadorDeSalas);
            else if(escolha == 3) imprimirReservasDaSala(gerenciadorDeSalas);
            else if(escolha == 4) break;
        }
    }

    public static void reservarSalaChamada(GerenciadorDeSalas gerenciadorDeSalas) {
        System.out.println("Qual o nome da sala para fazer a reserva?");
        String nomeSala = scanner.nextLine();
        System.out.println("Qual a data inicial da reserva?");
        LocalDateTime inicio = LocalDateTime.parse(scanner.nextLine());
        System.out.println("Qual a data final da reserva?");
        LocalDateTime fim = LocalDateTime.parse(scanner.nextLine());
        gerenciadorDeSalas.reservaSalaChamada(nomeSala, inicio, fim);
    }

    public static void cancelarReseva(GerenciadorDeSalas gerenciadorDeSalas) {
        List<Reserva> reservas = new LinkedList<>();
        int cancelada;
        System.out.println("Qual o nome da sala para cancelar a reserva?");
        String nomeSala = scanner.nextLine();
        System.out.println("Reservas da Sala " + nomeSala + ": ");
        int i = 1;
        for (Reserva r : gerenciadorDeSalas.getListaDeReservas()) {
            if (r.getNomeDaSala().equals(nomeSala)) {
                System.out.println(i + "-" + r.getNomeDaSala());
                reservas.add(r);
                i++;
            }
        }
        System.out.println("Qual deve ser removida?");
        cancelada = Integer.parseInt(scanner.nextLine());
        i = 1;
        for (Reserva r : reservas) {
            if (i == cancelada) gerenciadorDeSalas.getListaDeReservas().remove(r);
            i--;
        }
    }

    public static void imprimirReservasDaSala(GerenciadorDeSalas gerenciadorDeSalas) {
        System.out.println("Imprimir reservas de qual sala?");
        gerenciadorDeSalas.imprimeReservasDaSala(scanner.nextLine());
    }

    public static void teste0() {
        System.out.println("Joe Mama");
        LocalDate inicioReuniao = LocalDate.of(2021, 6, 5);
        LocalDate finalReuniao = LocalDate.of(2021, 6, 6);
        List<String> participantes = new ArrayList<>();
        participantes.add("AdrianoBarbudo");
        participantes.add("Felmateos");
        participantes.add("Fescobar");
        LocalDateTime i1 = LocalDateTime.of(2021, 6, 5, 12, 0);
        LocalDateTime f1 = LocalDateTime.of(2021, 6, 5, 16, 0);
        LocalDateTime i2 = LocalDateTime.of(2021, 6, 5, 12, 0);
        LocalDateTime f2 = LocalDateTime.of(2021, 6, 5, 14, 0);
        LocalDateTime i3 = LocalDateTime.of(2021, 6, 5, 14, 0);
        LocalDateTime f3 = LocalDateTime.of(2021, 6, 5, 16, 0);
        MarcadorDeReuniao reuniao = new MarcadorDeReuniao();
        reuniao.marcarReuniaoEntre(inicioReuniao, finalReuniao, participantes);
        System.out.println(reuniao.getDataInicial());
        reuniao.indicaDisponibilidadeDe("AdrianoBarbudo", i1, f1);
        reuniao.indicaDisponibilidadeDe("Felmateos", i2, f2);
        reuniao.indicaDisponibilidadeDe("Fescobar", i3, f3);
        reuniao.exibeDisponibilidades();
        reuniao.mostraSobreposicao();
    }
}