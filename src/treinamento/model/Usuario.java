package treinamento.model;

public class Usuario {

    private final String nome;
    private final Cargo cargo;

    public Usuario(String nome, Cargo cargo) {
        this.nome = nome;
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public boolean isGerente() {
        return cargo == Cargo.GERENTE;
    }
}
