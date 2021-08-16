public class Sala {
    String nome;
    int capacidadeMaxima;
    String descricao;
    String local;
    String observacoes;

    Sala (String nome, int capacidadeMaxima, String descricao) {
        this.nome = nome;
        this.capacidadeMaxima = capacidadeMaxima;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getLocal() {
        return local;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}