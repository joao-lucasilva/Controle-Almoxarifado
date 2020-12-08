package projeto_etec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import projeto_etec.connection.ConnectionFactory;
import projeto_etec.modelo.Funcionario;
import projeto_etec.modelo.Setor;

public class FuncionarioDAO {

    Connection c;

    public FuncionarioDAO() {
        c = new ConnectionFactory().getConnection();
    }

    public void adicionar(Funcionario f) {
        String sql = "insert into tbl_funcionario(nome_func,id_setor_fk)"
                + "values(?,(select id_setor from tbl_setor where nome_setor=?))";
        try {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, f.getNome_func());
            stmt.setString(2, f.getId_setor_fk().getNome_setor());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterar(Funcionario f) {
        String sql = "update tbl_funcionario set nome_func=?,"
                + "id_setor_fk=(select id_setor from tbl_setor where nome_setor=?)"
                + "where id_func=?;";
        try {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, f.getNome_func());
            stmt.setString(2, f.getId_setor_fk().getNome_setor());
            stmt.setInt(3, f.getId_func());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluir(Funcionario f) {
        try {
            PreparedStatement stmt = c.prepareStatement("delete from tbl_funcionario where id_func=?");
            stmt.setInt(1, f.getId_func());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void pesquisar(Funcionario f) {
        try {
            PreparedStatement stmt = this.c.prepareStatement(
                    "select f.id_func as cod,f.nome_func as nome,s.nome_setor as setor"
                    + "                    from tbl_funcionario as f inner join tbl_setor as s on"
                    + "                    f.id_setor_fk=s.id_setor where nome_func like '%" + f.getNome_func() + "%'");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Setor s = new Setor();
                f.setId_func(rs.getInt("cod"));
                f.setNome_func(rs.getString("nome"));
                s.setNome_setor(rs.getString("setor"));
                f.setId_setor_fk(s);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Funcionário não encontrado");
            throw new RuntimeException(e);
        }
    }

    public List<Funcionario> getList() {
        try {
            List<Funcionario> funcionarios = new ArrayList<Funcionario>();
            PreparedStatement stmt = this.c.prepareStatement("select id_func,nome_func, tbl_setor.nome_setor from tbl_funcionario inner join tbl_setor\n"
                    + "on tbl_setor.id_setor=tbl_funcionario.id_setor_fk");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Funcionario f = new Funcionario();
                Setor s = new Setor();
                f.setId_func(rs.getInt("id_func"));
                f.setNome_func(rs.getString("nome_func"));
                s.setNome_setor(rs.getString("nome_setor"));
                f.setId_setor_fk(s);

                funcionarios.add(f);
            }

            rs.close();
            stmt.close();
            return funcionarios;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
