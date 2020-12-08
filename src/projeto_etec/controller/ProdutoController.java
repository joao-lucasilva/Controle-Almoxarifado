package projeto_etec.controller;

import java.util.Date;
import javax.swing.JOptionPane;
import projeto_etec.dao.ProdutoDAO;
import projeto_etec.modelo.Produto;

public class ProdutoController {

    public void salvar(String nome, String quantidade) {
        Produto p = new Produto();
        p.setNome_prod(nome);
        p.setQtd_estoque(Integer.parseInt(quantidade));

        ProdutoDAO pdao = new ProdutoDAO();
        pdao.cadastrar(p);
        JOptionPane.showMessageDialog(null, "Produto Cadastrado");
    }

    public void alterar(String cod, String nome, String quantidade) {
        Produto p = new Produto();
        p.setId_prod(Integer.parseInt(cod));
        p.setNome_prod(nome);
        p.setQtd_estoque(Integer.parseInt(quantidade));
        System.out.println(cod);
        ProdutoDAO pdao = new ProdutoDAO();
        pdao.alterar(p);
        JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");
    }

    public void excluir(String cod) {
        Produto p = new Produto();
        int r = JOptionPane.showConfirmDialog(null, "Deseja Excluir este Registro?");
        if (r == 0) {
            p.setId_prod(Integer.parseInt(cod));
            ProdutoDAO pdao = new ProdutoDAO();
            pdao.excluir(p);
            JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");
        }
    }

}
