package projeto_etec.modelo;



public class Almoxarifado {
    
    private int id_almx;
    private Produto id_prod_fk;
    private Funcionario id_nome_fk;
    private String dt_retiro;

    public int getId_almx() {
        return id_almx;
    }

    public void setId_almx(int id_almx) {
        this.id_almx = id_almx;
    }

    public Produto getId_prod_fk() {
        return id_prod_fk;
    }

    public void setId_prod_fk(Produto id_prod_fk) {
        this.id_prod_fk = id_prod_fk;
    }

    public Funcionario getId_nome_fk() {
        return id_nome_fk;
    }

    public void setId_nome_fk(Funcionario id_nome_fk) {
        this.id_nome_fk = id_nome_fk;
    }

    public String getDt_retiro() {
        return dt_retiro;
    }

    public void setDt_retiro(String dt_retiro) {
        this.dt_retiro = dt_retiro;
    }

   
}
