<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.Produto, Model.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    
    Produto produto = (Produto) request.getAttribute("produto");
    if (produto == null) {
        response.sendRedirect("controller?action=listarProdutos");
        return;
    }
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Editar Produto - SkinMania</title>
    <link rel="stylesheet" type="text/css" href="Estilo/style.css">
</head>
<body>
    <div class="header">
        <h1>SkinMania</h1>
        <a href="controller?action=listarProdutos" class="btn-voltar">Voltar</a>
    </div>
    
    <div class="container">
        <h2>Editar Skin</h2>
        
        <form action="controller?action=atualizarProduto" method="post" class="form-produto">
            <input type="hidden" name="id" value="<%= produto.getId_produto() %>">
            
            <div class="form-group">
                <label for="nome">Nome da Skin:</label>
                <input type="text" id="nome" name="nome" value="<%= produto.getNome_produto() %>" required>
            </div>
            
            <div class="form-group">
                <label for="descricao">Descrição:</label>
                <textarea id="descricao" name="descricao" rows="3" required><%= produto.getDescricao() %></textarea>
            </div>
            
            <div class="form-group">
                <label for="jogo">Jogo:</label>
                <input type="text" id="jogo" name="jogo" value="<%= produto.getJogo() != null ? produto.getJogo() : "" %>" required>
            </div>
            
            <div class="form-group">
                <label for="raridade">Raridade:</label>
                <select id="raridade" name="raridade" required>
                    <option value="Comum" <%= "Comum".equals(produto.getRaridade()) ? "selected" : "" %>>Comum</option>
                    <option value="Incomum" <%= "Incomum".equals(produto.getRaridade()) ? "selected" : "" %>>Incomum</option>
                    <option value="Rara" <%= "Rara".equals(produto.getRaridade()) ? "selected" : "" %>>Rara</option>
                    <option value="Épica" <%= "Épica".equals(produto.getRaridade()) ? "selected" : "" %>>Épica</option>
                    <option value="Lendária" <%= "Lendária".equals(produto.getRaridade()) ? "selected" : "" %>>Lendária</option>
                </select>
            </div>
            
            <div class="form-group">
                <label for="tipo">Tipo:</label>
                <select id="tipo" name="tipo" required>
                    <option value="Personagem" <%= "Personagem".equals(produto.getTipo()) ? "selected" : "" %>>Personagem</option>
                    <option value="Arma" <%= "Arma".equals(produto.getTipo()) ? "selected" : "" %>>Arma</option>
                    <option value="Veículo" <%= "Veículo".equals(produto.getTipo()) ? "selected" : "" %>>Veículo</option>
                    <option value="Acessório" <%= "Acessório".equals(produto.getTipo()) ? "selected" : "" %>>Acessório</option>
                    <option value="Emote" <%= "Emote".equals(produto.getTipo()) ? "selected" : "" %>>Emote</option>
                </select>
            </div>
            
            <div class="form-group">
                <label for="preco">Preço (R$):</label>
                <input type="number" id="preco" name="preco" step="0.01" min="0" value="<%= produto.getPreco() %>" required>
            </div>
            
            <div class="form-group">
                <label for="estoque">Estoque:</label>
                <input type="number" id="estoque" name="estoque" min="0" value="<%= produto.getEstoque() %>" required>
            </div>
            
            <div class="form-actions">
                <button type="submit" class="btn-primary">Atualizar</button>
                <a href="controller?action=listarProdutos" class="btn-secondary">Cancelar</a>
            </div>
        </form>
    </div>
</body>
</html>

