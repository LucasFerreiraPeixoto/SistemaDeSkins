<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Model.Produto, Model.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    
    ArrayList<Produto> produtos = (ArrayList<Produto>) request.getAttribute("produtos");
    if (produtos == null) {
        // Carregar produtos se não vierem na requisição
        Model.DAO dao = new Model.DAO();
        produtos = dao.listarProdutos();
    }
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Realizar Venda - SkinMania</title>
    <link rel="stylesheet" type="text/css" href="Estilo/style.css">
</head>
<body>
    <div class="header">
        <h1>SkinMania</h1>
        <a href="controller?action=dashboard" class="btn-voltar">Voltar</a>
    </div>
    
    <div class="container">
        <h2>Realizar Venda</h2>
        
        <% if (request.getAttribute("mensagem") != null) { %>
            <div class="sucesso">
                <%= request.getAttribute("mensagem") %>
            </div>
        <% } %>
        
        <% if (request.getAttribute("erro") != null) { %>
            <div class="erro">
                <%= request.getAttribute("erro") %>
            </div>
        <% } %>
        
        <form action="controller?action=confirmarVenda" method="post" class="form-venda">
            <div class="form-group">
                <label for="id_produto">Selecione o Produto:</label>
                <select id="id_produto" name="id_produto" required onchange="atualizarInfo()">
                    <option value="">Selecione um produto</option>
                    <%
                        if (produtos != null) {
                            for (Produto p : produtos) {
                                if (p.getEstoque() > 0) {
                    %>
                    <option value="<%= p.getId_produto() %>" 
                            data-preco="<%= p.getPreco() %>" 
                            data-estoque="<%= p.getEstoque() %>"
                            data-nome="<%= p.getNome_produto() %>">
                        <%= p.getNome_produto() %> - R$ <%= String.format("%.2f", p.getPreco()) %> (Estoque: <%= p.getEstoque() %>)
                    </option>
                    <%
                                }
                            }
                        }
                    %>
                </select>
            </div>
            
            <div class="form-group">
                <label for="quantidade">Quantidade:</label>
                <input type="number" id="quantidade" name="quantidade" min="1" value="1" required onchange="calcularTotal()">
                <span id="estoque-disponivel" class="info-estoque"></span>
            </div>
            
            <div class="form-group">
                <label>Valor Total:</label>
                <div class="valor-total" id="valor-total">R$ 0,00</div>
            </div>
            
            <div class="form-actions">
                <button type="submit" class="btn-primary">Confirmar Venda</button>
                <a href="controller?action=dashboard" class="btn-secondary">Cancelar</a>
            </div>
        </form>
    </div>
    
    <script>
        function atualizarInfo() {
            var select = document.getElementById("id_produto");
            var option = select.options[select.selectedIndex];
            var estoque = option.getAttribute("data-estoque");
            var preco = parseFloat(option.getAttribute("data-preco"));
            
            document.getElementById("estoque-disponivel").textContent = "Estoque disponível: " + estoque;
            document.getElementById("quantidade").max = estoque;
            calcularTotal();
        }
        
        function calcularTotal() {
            var select = document.getElementById("id_produto");
            var quantidade = parseInt(document.getElementById("quantidade").value) || 0;
            var option = select.options[select.selectedIndex];
            
            if (option.value) {
                var preco = parseFloat(option.getAttribute("data-preco"));
                var total = preco * quantidade;
                document.getElementById("valor-total").textContent = "R$ " + total.toFixed(2).replace(".", ",");
            } else {
                document.getElementById("valor-total").textContent = "R$ 0,00";
            }
        }
    </script>
</body>
</html>

