package bancoDeDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entidades.Produto;

/**
 * Classe ProdutoDAO para operações CRUD em produtos no banco de dados.
 * 
 * Aplica o DIP por depender da interface DAO
 * para definir suas operações. 
 * 
 * Segue o SRP pois é focada apenas nos objeto do tipo produto.
 */

public class ProdutoDAO implements DAO {
    private Connection conn;

    public ProdutoDAO() throws SQLException, ClassNotFoundException {
        this.conn = ConnectDAO.getConnection();
    }

    public void inserir(Produto produto) throws SQLException {
        String sql = "INSERT INTO produto (codigo, nome, descricao) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, produto.getCodigo());
        stmt.setString(2, produto.getNome());
        stmt.setString(3, produto.getDescricao());
        stmt.executeUpdate();
        stmt.close();
    }

    public Produto buscar(int codigo) throws SQLException {
        String sql = "SELECT * FROM produto WHERE codigo = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, codigo);
        ResultSet rs = stmt.executeQuery();
        Produto produto = null;
        if (rs.next()) {
            produto = new Produto(codigo, sql, sql);
            produto.setCodigo(rs.getInt("codigo"));
            produto.setNome(rs.getString("nome"));
            produto.setDescricao(rs.getString("descricao"));
        }
        rs.close();
        stmt.close();
        return produto;
    }

    public void atualizar(Produto produto) throws SQLException {
        String sql = "UPDATE produto SET nome = ?, descricao = ? WHERE codigo = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, produto.getNome());
        stmt.setString(2, produto.getDescricao());
        stmt.setInt(3, produto.getCodigo());
        stmt.executeUpdate();
        stmt.close();
    }

    public void remover(int codigo) throws SQLException {
        String sql = "DELETE FROM produto WHERE codigo = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, codigo);
        stmt.executeUpdate();
        stmt.close();
    }
}