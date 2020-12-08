package projeto_etec.dao;

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
import projeto_etec.modelo.Produto;

public class ProdutoDAO {

    Connection c;

    public ProdutoDAO() {
        c = new ConnectionFactory().getConnection();
    }

    public void cadastrar(Produto p) {
        String sql = "insert into tbl_produto(nome_prod,qtd_estoque)"
                + "values (?,?)";
        try {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, p.getNome_prod());
            stmt.setInt(2, p.getQtd_estoque());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterar(Produto p) {
        String sql = "update tbl_produto set nome_prod = ?,qtd_estoque = ? where id_prod = ?";
        try {
            PreparedStatement stmt = this.c.prepareStatement(sql);
            stmt.setString(1, p.getNome_prod());
            stmt.setInt(2, p.getQtd_estoque());
            stmt.setInt(3, p.getId_prod());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluir(Produto p) {
        try {
            PreparedStatement stmt = c.prepareStatement("delete  from tbl_produto where id_prod=?");
            stmt.setInt(1, p.getId_prod());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void pesquisar(Produto p) {
        try {
            PreparedStatement stmt = this.c.prepareStatement(
            "select * from tbl_produto where nome_prod like '%" + p.getNome_prod()+"%'");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                p.setId_prod(rs.getInt("id_prod"));
                p.setNome_prod(rs.getString("nome_prod"));
                p.setQtd_estoque(rs.getInt("qtd_estoque"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Produto> getLista() {
        try {
            List<Produto> produtos= new ArrayList<Produto>();
            PreparedStatement stmt = this.c.prepareStatement
        ("select id_prod,nome_prod,qtd_estoque,date_format(dt_cadastro,\"%d/%m/%Y\") as dt_cadastro from tbl_produto;");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto p = new Produto();
                p.setId_prod(rs.getInt("id_prod"));
                p.setNome_prod(rs.getString("nome_prod"));
                p.setQtd_estoque(rs.getInt("qtd_estoque"));
                p.setDt_cadastro(rs.getString("dt_cadastro"));
                produtos.add(p);
            }
            rs.close();
            stmt.close();
            return produtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public int puxarQuantidadeProd(String n){
        int x=0;
        try {
            PreparedStatement stmt = this.c.prepareStatement("select qtd_estoque from tbl_produto where id_prod=(select id_prod from tbl_produto where nome_prod='"+n+"')");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {                
                //x= p.setQtd_estoque(rs.getInt("qtd_estoque"));
                x=rs.getInt("qtd_estoque");
            }
            
            rs.close();
            stmt.close();
            return x;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void GeraRel() {
        try {
            Map p = new HashMap();
            JasperPrint print = JasperFillManager.fillReport("src/projeto_etec/relatorios/rel_estoque.jasper", p,c);
            JasperViewer jv=new JasperViewer(print,false);
            jv.setTitle("Relatório de Produtos");
            jv.setVisible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
