package projeto_etec.controller;

import javax.swing.JOptionPane;
import projeto_etec.dao.FuncionarioDAO;
import projeto_etec.modelo.Funcionario;
import projeto_etec.modelo.Setor;

public class FuncionarioController {

    public void salvar(String setor, String nome) {
        Setor s = new Setor();
        s.setNome_setor(setor);
        Funcionario f = new Funcionario();
        f.setNome_func(nome);
        f.setId_setor_fk(s);

        FuncionarioDAO d = new FuncionarioDAO();
        d.adicionar(f);
        JOptionPane.showMessageDialog(null, "Gravado com Sucesso!");
    }

    public void alterar(String cod, String setor, String nome) {
        Funcionario f = new Funcionario();
        f.setId_func(Integer.parseInt(cod));
        f.setNome_func(nome);
        Setor s = new Setor();
        s.setNome_setor(setor);
        f.setId_setor_fk(s);
        FuncionarioDAO fdao = new FuncionarioDAO();
        fdao.alterar(f);
        JOptionPane.showMessageDialog(null, "Funcionario Alterado com sucesso!");
    }
public void excluir(String cod){
Funcionario f=new Funcionario();
int r=JOptionPane.showConfirmDialog(null, "Deseja excluir este Funcionário:");
    if (r==0) {
        f.setId_func(Integer.parseInt(cod));
        FuncionarioDAO fdao=new FuncionarioDAO();
        fdao.excluir(f);
        JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");
    }
}
}
