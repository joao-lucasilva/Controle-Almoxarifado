package projeto_etec.controller;

import javax.swing.JOptionPane;
import projeto_etec.dao.SetorDAO;
import projeto_etec.modelo.Setor;

public class SetorController {
    
    public void salvar(String nome){
        Setor s=new Setor();
        s.setNome_setor(nome);
        SetorDAO sdao=new SetorDAO();
        sdao.adicionar(s);
        JOptionPane.showMessageDialog(null, "Gravado com Sucesso!");
    }
    public void alterar(String cod,String nome){
        Setor s=new Setor();
        s.setId_setor(Integer.parseInt(cod));
        s.setNome_setor(nome);
        SetorDAO sdao=new SetorDAO();
        sdao.alterar(s);
        JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");
    }
    public void excluir(String cod){
     Setor s=new Setor();
     int r=JOptionPane.showConfirmDialog(null, "Deseja Excluir este Setor?");
        if (r==0) {
            s.setId_setor(Integer.parseInt(cod));
            SetorDAO sdao=new SetorDAO();
            sdao.remover(s);
            JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");
        }
    }
    public void pesquisarSetor(String inf, int cb, Setor s){
    String campo=null;
    SetorDAO sd=new SetorDAO();
    switch(cb){
        case 0:
            campo="id_setor";
            break;
        case 1:
            campo="nome_setor";
            break;
        default:
            break;
    }
    sd.pesquisar(inf, campo, s);
    s.setId_setor(s.getId_setor());
    s.setNome_setor(s.getNome_setor());
    }
}
