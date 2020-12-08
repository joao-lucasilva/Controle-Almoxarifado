package projeto_etec.modelo;

public class Lista {
    private int id_lista;
    private int qtd_retirada;
    private Produto id_prod_fk;
    private Almoxarifado id_almx_fk;
    private String dt_lista;

    public String getDt_lista() {
        return dt_lista;
    }

    public void setDt_lista(String dt_lista) {
        this.dt_lista = dt_lista;
    }

    public int getId_lista() {
        return id_lista;
    }

    public void setId_lista(int id_lista) {
        this.id_lista = id_lista;
    }

    public int getQtd_retirada() {
        return qtd_retirada;
    }

    public void setQtd_retirada(int qtd_retirada) {
        this.qtd_retirada = qtd_retirada;
    }

    public Produto getId_prod_fk() {
        return id_prod_fk;
    }

    public void setId_prod_fk(Produto id_prod_fk) {
        this.id_prod_fk = id_prod_fk;
    }

    public Almoxarifado getId_almx_fk() {
        return id_almx_fk;
    }

    public void setId_almx_fk(Almoxarifado id_almx_fk) {
        this.id_almx_fk = id_almx_fk;
    }
    
}
