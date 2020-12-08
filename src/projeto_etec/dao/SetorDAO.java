package projeto_etec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import projeto_etec.connection.ConnectionFactory;
import projeto_etec.modelo.Setor;

public class SetorDAO {

    Connection c;

    public SetorDAO() {
        c = new ConnectionFactory().getConnection();
    }

    //Adiciona Setor
    public void adicionar(Setor s) {
        String sql = "insert into tbl_setor (nome_setor) values (?)";
        try {
            //PreparedStatement para inserção
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, s.getNome_setor());
            //Execute 
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Método para alterar setor
    public void alterar(Setor s) {
        String sql = "update tbl_setor set nome_setor = ? where id_setor=?;";
        try {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, s.getNome_setor());
            stmt.setInt(2, s.getId_setor());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Método remove setor
    public void remover(Setor s) {
        try {
            PreparedStatement stmt = c.prepareStatement("delete from tbl_setor where id_setor=?");
            stmt.setInt(1, s.getId_setor());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Método para pesquisar 

    public void pesquisar(String inf,String campo,Setor s) {
        try {
            PreparedStatement stmt = this.c.prepareStatement
            ("select * from tbl_setor where "+ campo+" like '%"+inf+"%'");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                s.setId_setor(rs.getInt("id_setor"));
                s.setNome_setor(rs.getString("nome_setor"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Setor> getLista() {
        try {
            List<Setor> setores = new ArrayList<Setor>();
            PreparedStatement stmt = this.c.prepareStatement
            ("Select * from tbl_setor");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                //Criando o objeto Cliente
                Setor s = new Setor();
                s.setId_setor(rs.getInt("id_setor"));
                s.setNome_setor(rs.getString("nome_setor"));
                setores.add(s);
            }
            rs.close();
            stmt.close();
            return setores;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
