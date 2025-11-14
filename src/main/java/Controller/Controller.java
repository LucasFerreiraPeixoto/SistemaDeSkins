package Controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import Model.DAO;
import Model.Usuario;
import Model.Produto;
import Model.Venda;

@WebServlet(urlPatterns = {"/Controller", "/main"})
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    DAO dao = new DAO();
    
    public Controller() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action != null && !action.equals("login") && !action.equals("logout")) {
            HttpSession session = request.getSession();
            Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
            if (usuarioLogado == null) {
                response.sendRedirect("login.jsp");
                return;
            }
        }
        
        if (action == null || action.equals("")) {
            response.sendRedirect("login.jsp");
        } else if (action.equals("login")) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if (action.equals("dashboard")) {
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } else if (action.equals("listarProdutos")) {
            listarProdutos(request, response);
        } else if (action.equals("cadastrarProduto")) {
            request.getRequestDispatcher("cadastrarProduto.jsp").forward(request, response);
        } else if (action.equals("editarProduto")) {
            buscarProduto(request, response);
        } else if (action.equals("removerProduto")) {
            removerProduto(request, response);
        } else if (action.equals("vender")) {
            request.getRequestDispatcher("vender.jsp").forward(request, response);
        } else if (action.equals("listarVendas")) {
            listarVendas(request, response);
        } else if (action.equals("logout")) {
            logout(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null || action.equals("")) {
            response.sendRedirect("login.jsp");
        } else if (action.equals("autenticar")) {
            autenticar(request, response);
        } else if (action.equals("salvarProduto")) {
            salvarProduto(request, response);
        } else if (action.equals("atualizarProduto")) {
            atualizarProduto(request, response);
        } else if (action.equals("confirmarVenda")) {
            confirmarVenda(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
    
    private void autenticar(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        Usuario usuario = dao.verificarLogin(email, senha);
        
        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", usuario);
            response.sendRedirect("controller?action=dashboard");
        } else {
            request.setAttribute("erro", "Email ou senha inválidos!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
    
    private void logout(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("login.jsp");
    }
    
    private void listarProdutos(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        ArrayList<Produto> lista = dao.listarProdutos();
        request.setAttribute("produtos", lista);
        request.getRequestDispatcher("listarProdutos.jsp").forward(request, response);
    }
    
    private void buscarProduto(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        Produto produto = null;
        if (idParam != null && !idParam.isEmpty()) {
            int id = Integer.parseInt(idParam);
            produto = dao.buscarProdutoPorId(id);
        }
        request.setAttribute("produto", produto);
        request.getRequestDispatcher("editarProduto.jsp").forward(request, response);
    }
    
    private void salvarProduto(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        double preco = Double.parseDouble(request.getParameter("preco"));
        int estoque = Integer.parseInt(request.getParameter("estoque"));
        String jogo = request.getParameter("jogo");
        String raridade = request.getParameter("raridade");
        String tipo = request.getParameter("tipo");
        
        Produto produto = new Produto();
        produto.setNome_produto(nome);
        produto.setDescricao(descricao);
        produto.setPreco(preco);
        produto.setEstoque(estoque);
        produto.setJogo(jogo);
        produto.setRaridade(raridade);
        produto.setTipo(tipo);
        
        dao.inserirProduto(produto);
        response.sendRedirect("controller?action=listarProdutos");
    }
    
    private void atualizarProduto(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        double preco = Double.parseDouble(request.getParameter("preco"));
        int estoque = Integer.parseInt(request.getParameter("estoque"));
        String jogo = request.getParameter("jogo");
        String raridade = request.getParameter("raridade");
        String tipo = request.getParameter("tipo");
        
        Produto produto = new Produto();
        produto.setId_produto(id);
        produto.setNome_produto(nome);
        produto.setDescricao(descricao);
        produto.setPreco(preco);
        produto.setEstoque(estoque);
        produto.setJogo(jogo);
        produto.setRaridade(raridade);
        produto.setTipo(tipo);
        
        dao.atualizarProduto(produto);
        response.sendRedirect("controller?action=listarProdutos");
    }
    
    private void removerProduto(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            int id = Integer.parseInt(idParam);
            dao.removerProduto(id);
        }
        response.sendRedirect("controller?action=listarProdutos");
    }
    
    private void confirmarVenda(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        
        if (usuarioLogado == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        int idProduto = Integer.parseInt(request.getParameter("id_produto"));
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        
        int estoqueAtual = dao.consultarEstoque(idProduto);
        
        if (estoqueAtual >= quantidade) {
            Produto produto = dao.buscarProdutoPorId(idProduto);
            double valorTotal = produto.getPreco() * quantidade;
            
            Venda venda = new Venda();
            venda.setId_usuario(usuarioLogado.getId_usuario());
            venda.setId_produto(idProduto);
            venda.setQuantidade(quantidade);
            venda.setData_venda(new java.util.Date());
            venda.setValor_total(valorTotal);
            
            dao.inserirVenda(venda);
            dao.atualizarEstoque(idProduto, quantidade);
            
            request.setAttribute("mensagem", "Venda realizada com sucesso! Valor total: R$ " + String.format("%.2f", valorTotal));
        } else {
            request.setAttribute("Erro", "Opa! Estoque insuficiente! Disponível: " + estoqueAtual);
        }
        
        request.getRequestDispatcher("vender.jsp").forward(request, response);
    }
    
    private void listarVendas(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        ArrayList<Venda> lista = dao.listarVendas();
        request.setAttribute("vendas", lista);
        request.getRequestDispatcher("listarVendas.jsp").forward(request, response);
    }
}

