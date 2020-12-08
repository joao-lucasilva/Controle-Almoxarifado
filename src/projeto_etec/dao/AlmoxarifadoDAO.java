package projeto_etec.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import projeto_etec.connection.ConnectionFactory;
import projeto_etec.modelo.Almoxarifado;
import projeto_etec.modelo.Funcionario;
import projeto_etec.modelo.Produto;

public class AlmoxarifadoDAO {

    Connection c;

    public AlmoxarifadoDAO() {
        c = new ConnectionFactory().getConnection();
    }

    public void adcionar(Almoxarifado a) {
        String sql = "insert into tbl_almoxarifado (id_nome_fk) values ((select id_func from tbl_funcionario where nome_func=?))";
        try {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, a.getId_nome_fk().getNome_func());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
        }
    }

    public void alterar(Almoxarifado a) {
        String sql = "update tbl_almoxarifado set  id_nome_fk=(select id_func from tbl_funcionario where nome_func=?),"
                + "dt_retiro=?, where id_almx=?";
        try {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, a.getId_nome_fk().getNome_func());
            stmt.setString(2, a.getDt_retiro());
            stmt.setInt(3, a.getId_almx());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remover(Almoxarifado a) {
        try {
            PreparedStatement stmt = c.prepareStatement("delete from tbl_almoxarifado where id_almx=?");
            stmt.setInt(1, a.getId_almx());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void pesquisar(Almoxarifado a) {
        try {
            PreparedStatement stmt = this.c.prepareStatement(
                    "select id_almx,tbl_funcionario.nome_func,date_format(dt_retiro,'%d/%m/%Y') as dt_retiro from tbl_almoxarifado "
                  + "inner join tbl_funcionario on tbl_funcionario.id_func=tbl_almoxarifado.id_nome_fk where nome_func=");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto p = new Produto();
                Funcionario f = new Funcionario();
                a.setId_almx(rs.getInt("id_almx"));
//                a.setNome_almx(rs.getString("nome_almx"));
                f.setNome_func(rs.getString("nome_func"));
                a.setDt_retiro(rs.getString("dt_retiro"));
                p.setNome_prod(rs.getString("nome_prod"));

            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<Almoxarifado> getLista() {
        try {
            List<Almoxarifado> almoxarifados = new ArrayList<Almoxarifado>();
            PreparedStatement stmt = this.c.prepareStatement(
                    "select id_almx,tbl_funcionario.nome_func,date_format(dt_retiro,'%d/%m/%Y') as dt_retiro from tbl_almoxarifado inner join tbl_funcionario on tbl_funcionario.id_func=tbl_almoxarifado.id_nome_fk");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Almoxarifado a = new Almoxarifado();
               
                Funcionario f = new Funcionario();
                a.setId_almx(rs.getInt("id_almx"));
                f.setNome_func(rs.getString("nome_func"));
                a.setId_nome_fk(f);
                a.setDt_retiro(rs.getString("dt_retiro"));

                almoxarifados.add(a);
            }
            rs.close();
            stmt.close();
            return almoxarifados;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
