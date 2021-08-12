import java.time.*;

public class Disponibilidade implements Comparable<LocalDateTime> {
    private LocalDateTime dataInicial;
    private LocalDateTime dataFinal;

    Disponibilidade(LocalDateTime dataInicial, LocalDateTime dataFinal) {
       this.dataInicial = dataInicial;
       this.dataFinal = dataFinal;
    }

    public LocalDateTime getDataInicial() {
        return dataInicial;
    }

    public LocalDateTime getDataFinal() {
        return dataFinal;
    }


    @Override
    public int compareTo(LocalDateTime o) {
        return 0;
    }
}
