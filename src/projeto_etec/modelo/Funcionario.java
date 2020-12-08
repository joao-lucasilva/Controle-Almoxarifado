package projeto_etec.modelo;

public class Funcionario {

    private int id_func;
    private String nome_func;
    private Setor id_setor_fk;

    public int getId_func() {
        return id_func;
    }

    public void setId_func(int id_func) {
        this.id_func = id_func;
    }

    public String getNome_func() {
        return nome_func;
    }

    public void setNome_func(String nome_func) {
        this.nome_func = nome_func;
    }

    public Setor getId_setor_fk() {
        return id_setor_fk;
    }

    public void setId_setor_fk(Setor id_setor_fk) {
        this.id_setor_fk = id_setor_fk;
    }

}
