package projeto_etec.controller;

import javax.swing.JOptionPane;
import projeto_etec.modelo.Almoxarifado;
import projeto_etec.modelo.Produto;
import projeto_etec.modelo.Funcionario;
import java.util.Date;
import projeto_etec.dao.AlmoxarifadoDAO;

public class AlmoxarifadoController {
    
    public void salvar(String nome) {
        Almoxarifado a = new Almoxarifado();
        Funcionario f = new Funcionario();
        f.setNome_func(nome);
        a.setId_nome_fk(f);
        AlmoxarifadoDAO adao = new AlmoxarifadoDAO();
        adao.adcionar(a);
        JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
    }
    
    public void alterar(String cod, String nome, String fun, String dt) {
        Almoxarifado a = new Almoxarifado();
        Funcionario f = new Funcionario();
        f.setNome_func(fun);
        a.setId_nome_fk(f);
        a.setDt_retiro(dt);
        AlmoxarifadoDAO adao = new AlmoxarifadoDAO();
        adao.alterar(a);
    }
    
    public void excluir(String cod) {
        Almoxarifado a = new Almoxarifado();
        int r = JOptionPane.showConfirmDialog(null, "Deseja excluir este Registro?");
        if (r == 0) {
            a.setId_almx(Integer.parseInt(cod));
            
            AlmoxarifadoDAO adao = new AlmoxarifadoDAO();
            adao.remover(a);
            JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");
        }
    }
    
}
