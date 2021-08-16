import java.time.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Joe Mama");
        LocalDate inicioReuniao = LocalDate.of(2021, 6, 5);
        LocalDate finalReuniao = LocalDate.of(2021, 6, 6);

        List<String> participantes = new ArrayList<>();
        participantes.add("AdrianoBarbudo");
        participantes.add("Felmateos");

        LocalDateTime i1 = LocalDateTime.of(2021, 6, 5, 20, 0);
        LocalDateTime f1 = LocalDateTime.of(2021, 6, 5, 22, 0);
        LocalDateTime i2 = LocalDateTime.of(2021, 6, 5, 8, 0);
        LocalDateTime f2 = LocalDateTime.of(2021, 6, 5, 10, 0);
        LocalDateTime i3 = LocalDateTime.of(2021, 6, 5, 9, 0);
        LocalDateTime f3 = LocalDateTime.of(2021, 6, 5, 21, 0);

        MarcadorDeReuniao reuniao = new MarcadorDeReuniao();

        reuniao.marcarReuniaoEntre(inicioReuniao, finalReuniao, participantes);
        System.out.println(reuniao.getDataInicial());
        reuniao.indicaDisponibilidadeDe("AdrianoBarbudo", i1, f1);
        reuniao.indicaDisponibilidadeDe("AdrianoBarbudo", i2, f2);
        reuniao.indicaDisponibilidadeDe("Felmateos", i3, f3);
        reuniao.exibeDisponibilidades();
        reuniao.mostraSobreposicao();
    }
}
