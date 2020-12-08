package projeto_etec.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import projeto_etec.connection.ConnectionFactory;
import projeto_etec.modelo.Almoxarifado;
import projeto_etec.modelo.Funcionario;
import projeto_etec.modelo.Lista;
import projeto_etec.modelo.Produto;

public class ListaDAO {

    Connection c;

    public ListaDAO() {
        c = new ConnectionFactory().getConnection();
    }

    public void GeraRel(Date inicio, Date fim) {
        try {
            Map dar = new HashMap();
            
            dar.put("dt_inicio", inicio);
            dar.put("dt_fim", fim);
            
            JasperPrint print = JasperFillManager.fillReport("src/projeto_etec/relatorios/relatorio.jasper", dar,c);
            JasperViewer jv=new JasperViewer(print,false);
            jv.setTitle("Relatório de Saídas");
            jv.setVisible(true);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }

    }

    public void adcionar(Lista l) {
        String sql = "call lista(?,?,?);";
        try {
            CallableStatement stmt = c.prepareCall(sql);
            stmt.setString(1, l.getId_prod_fk().getNome_prod());
            stmt.setInt(2, l.getQtd_retirada());
            stmt.setString(3, l.getId_almx_fk().getId_nome_fk().getNome_func());
            stmt.executeUpdate();           
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterar(Lista l) {
        String sql = "update tbl_lista set qtdo_retirada=?,"
                + "id_prod_fk=(select id_prod from tbl_produto where nome_prod=?)"
                + "id_almx_fk=(select id_almx from tbl_almoxarifado where id_nome_fk=("
                + "select id_func from tbl_funcionario where nome_func=?))"
                + "where id_lista=?;";
        try {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, l.getQtd_retirada());
            stmt.setString(2, l.getId_prod_fk().getNome_prod());
            stmt.setString(3, l.getId_almx_fk().getId_nome_fk().getNome_func());
            stmt.setInt(3, l.getId_lista());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remover(Lista l) {
        try {
            PreparedStatement stmt = c.prepareStatement(
                    "delete from tbl_lista where id_lista=?");
            stmt.setInt(1, l.getId_lista());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void pesquisar(Lista l) {
        try {
            PreparedStatement stmt = this.c.prepareStatement(
                    "select tbl_lista.id_lista,tbl_lista.qtd_retirada,tbl_produto.nome_prod,tbl_funcionario.nome_func"
                    + "from tbl_lista inner join tbl_produto on tbl_lista.id_prod_fk=tbl_produto.id_prod inner join"
                    + "tbl_funcionario on tbl_lista.id_almx_fk=tbl_funcionario.id_func where l.id_lista=" + l.getId_lista());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto p = new Produto();
                p.setNome_prod(rs.getString("nome_prod"));

                Funcionario f = new Funcionario();
                f.setNome_func(rs.getString("nome_func"));

                Almoxarifado a = new Almoxarifado();
                a.setId_nome_fk(f);

                l.setId_lista(rs.getInt("id_lista"));
                l.setQtd_retirada(rs.getInt("qtd_retirada"));
                l.setId_prod_fk(p);
                l.setId_almx_fk(a);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Lista> getLista() {

        try {
            List<Lista> listas = new ArrayList<Lista>();
            PreparedStatement stmt = this.c.prepareStatement(
                    "select tbl_lista.qtd_retirada,tbl_produto.nome_prod,tbl_funcionario.nome_func, date_format(dt_lista, '%d/%m/%Y') as dt_lista"
                    + " from tbl_lista inner join tbl_produto on tbl_lista.id_prod_fk=tbl_produto.id_prod inner join "
                    + "tbl_funcionario on tbl_lista.id_almx_fk=tbl_funcionario.id_func");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Lista l = new Lista();

                Produto p = new Produto();
                p.setNome_prod(rs.getString("nome_prod"));

                Funcionario f = new Funcionario();
                f.setNome_func(rs.getString("nome_func"));

                Almoxarifado a = new Almoxarifado();
                a.setId_nome_fk(f);
                l.setDt_lista(rs.getString("dt_lista"));
                l.setQtd_retirada(rs.getInt("qtd_retirada"));
                l.setId_prod_fk(p);
                l.setId_almx_fk(a);

                listas.add(l);
            }
            rs.close();
            stmt.close();
            return listas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
