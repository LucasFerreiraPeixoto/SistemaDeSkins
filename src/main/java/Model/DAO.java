package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class DAO {
    private String driver = "org.mariadb.jdbc.Driver";
    private String url = "jdbc:mariadb://127.0.0.1:3306/skinmania?useTimezone=true&serverTimezone=UTC";
    private String user = "root";
    private String password = "";
    
    private Connection conectar() {
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            return con;
        } catch (Exception e) {
            System.out.println("Erro ao conectar: " + e);
            return null;
        }
    }
    
    public void testeConexao() {
        try {
            Connection con = conectar();
            System.out.println("Conexão: " + con);
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }
    
    public Usuario verificarLogin(String email, String senha) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, senha);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                usuario = new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("tipo")
                );
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao verificar login: " + e);
        }
        return usuario;
    }
    
    public ArrayList<Usuario> listarUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                usuarios.add(new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("tipo")
                ));
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao listar usuários: " + e);
        }
        return usuarios;
    }
    
    public ArrayList<Produto> listarProdutos() {
        ArrayList<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos ORDER BY nome_produto";
        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                produtos.add(new Produto(
                    rs.getInt("id_produto"),
                    rs.getString("nome_produto"),
                    rs.getString("descricao"),
                    rs.getDouble("preco"),
                    rs.getInt("estoque"),
                    rs.getString("jogo"),
                    rs.getString("raridade"),
                    rs.getString("tipo")
                ));
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao listar produtos: " + e);
        }
        return produtos;
    }
    
    public Produto buscarProdutoPorId(int id) {
        Produto produto = null;
        String sql = "SELECT * FROM produtos WHERE id_produto = ?";
        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                produto = new Produto(
                    rs.getInt("id_produto"),
                    rs.getString("nome_produto"),
                    rs.getString("descricao"),
                    rs.getDouble("preco"),
                    rs.getInt("estoque"),
                    rs.getString("jogo"),
                    rs.getString("raridade"),
                    rs.getString("tipo")
                );
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao buscar produto: " + e);
        }
        return produto;
    }
    
    public void inserirProduto(Produto produto) {
        String sql = "INSERT INTO produtos (nome_produto, descricao, preco, estoque, jogo, raridade, tipo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, produto.getNome_produto());
            pst.setString(2, produto.getDescricao());
            pst.setDouble(3, produto.getPreco());
            pst.setInt(4, produto.getEstoque());
            pst.setString(5, produto.getJogo());
            pst.setString(6, produto.getRaridade());
            pst.setString(7, produto.getTipo());
            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao inserir produto: " + e);
        }
    }
    
    public void atualizarProduto(Produto produto) {
        String sql = "UPDATE produtos SET nome_produto=?, descricao=?, preco=?, estoque=?, jogo=?, raridade=?, tipo=? WHERE id_produto=?";
        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, produto.getNome_produto());
            pst.setString(2, produto.getDescricao());
            pst.setDouble(3, produto.getPreco());
            pst.setInt(4, produto.getEstoque());
            pst.setString(5, produto.getJogo());
            pst.setString(6, produto.getRaridade());
            pst.setString(7, produto.getTipo());
            pst.setInt(8, produto.getId_produto());
            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao atualizar produto: " + e);
        }
    }
    
    public void removerProduto(int id) {
        String sql = "DELETE FROM produtos WHERE id_produto = ?";
        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao remover produto: " + e);
        }
    }
    
    public int consultarEstoque(int idProduto) {
        String sql = "SELECT estoque FROM produtos WHERE id_produto = ?";
        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idProduto);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("estoque");
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao consultar estoque: " + e);
        }
        return 0;
    }
    
    public void atualizarEstoque(int idProduto, int quantidade) {
        String sql = "UPDATE produtos SET estoque = estoque - ? WHERE id_produto = ?";
        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, quantidade);
            pst.setInt(2, idProduto);
            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao atualizar estoque: " + e);
        }
    }
    
    public void adicionarEstoque(int idProduto, int quantidade) {
        String sql = "UPDATE produtos SET estoque = estoque + ? WHERE id_produto = ?";
        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, quantidade);
            pst.setInt(2, idProduto);
            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao adicionar estoque: " + e);
        }
    }
    
    public void inserirVenda(Venda venda) {
        String sql = "INSERT INTO vendas (id_usuario, id_produto, quantidade, data_venda, valor_total) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, venda.getId_usuario());
            pst.setInt(2, venda.getId_produto());
            pst.setInt(3, venda.getQuantidade());
            pst.setDate(4, new java.sql.Date(venda.getData_venda().getTime()));
            pst.setDouble(5, venda.getValor_total());
            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao inserir venda: " + e);
        }
    }
    
    public ArrayList<Venda> listarVendas() {
        ArrayList<Venda> vendas = new ArrayList<>();
        String sql = "SELECT v.id_venda, u.nome AS nome_usuario, p.nome_produto, " +
                     "v.quantidade, v.data_venda, v.valor_total " +
                     "FROM vendas v " +
                     "INNER JOIN usuarios u ON v.id_usuario = u.id_usuario " +
                     "INNER JOIN produtos p ON v.id_produto = p.id_produto " +
                     "ORDER BY v.data_venda DESC";
        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                vendas.add(new Venda(
                    rs.getInt("id_venda"),
                    rs.getString("nome_usuario"),
                    rs.getString("nome_produto"),
                    rs.getInt("quantidade"),
                    rs.getDate("data_venda"),
                    rs.getDouble("valor_total")
                ));
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao listar vendas: " + e);
        }
        return vendas;
    }
}

