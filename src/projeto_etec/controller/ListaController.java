package projeto_etec.controller;

import javax.swing.JOptionPane;
import projeto_etec.dao.ListaDAO;
import projeto_etec.modelo.Lista;
import projeto_etec.modelo.Almoxarifado;
import projeto_etec.modelo.Funcionario;
import projeto_etec.modelo.Produto;

public class ListaController {

    public void salvar(String qtd, String prod, String amlx) {
        Lista l = new Lista();
        l.setQtd_retirada(Integer.parseInt(qtd));
        Produto p = new Produto();
        p.setNome_prod(prod);
        l.setId_prod_fk(p);
        
        Funcionario f = new Funcionario();
        f.setNome_func(amlx);
        Almoxarifado a = new Almoxarifado();
        a.setId_nome_fk(f);
        l.setId_almx_fk(a);
        
        ListaDAO ldao = new ListaDAO();
        ldao.adcionar(l);
        JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
    }

    public void alterar(String cod, String qtd, String prod, String amlx) {
        Funcionario f = new Funcionario();
        f.setNome_func(amlx);

        Almoxarifado a = new Almoxarifado();
        a.setId_nome_fk(f);

        Lista l = new Lista();
        l.setId_lista(Integer.parseInt(cod));
        l.setQtd_retirada(Integer.parseInt(qtd));
        Produto p = new Produto();
        p.setNome_prod(prod);
        l.setId_prod_fk(p);
        l.setId_almx_fk(a);

        ListaDAO ldao = new ListaDAO();
        ldao.alterar(l);
    }

    public void excluir(String cod) {
        Lista l = new Lista();
        int r = JOptionPane.showConfirmDialog(null, "Deseja excluir este Registro?");
        if (r == 0) {
            l.setId_lista(Integer.parseInt(cod));

            ListaDAO adao = new ListaDAO();
            adao.remover(l);
            JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");
        }
    }
}
