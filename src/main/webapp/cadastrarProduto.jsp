<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Cadastrar Produto - SkinMania</title>
    <link rel="stylesheet" type="text/css" href="Estilo/style.css">
</head>
<body>
    <div class="header">
        <h1>SkinMania</h1>
        <a href="controller?action=dashboard" class="btn-voltar">Voltar</a>
    </div>
    
    <div class="container">
        <h2>Cadastrar Nova Skin</h2>
        
        <form action="controller?action=salvarProduto" method="post" class="form-produto">
            <div class="form-group">
                <label for="nome">Nome da Skin:</label>
                <input type="text" id="nome" name="nome" required>
            </div>
            
            <div class="form-group">
                <label for="descricao">Descrição:</label>
                <textarea id="descricao" name="descricao" rows="3" required></textarea>
            </div>
            
            <div class="form-group">
                <label for="jogo">Jogo:</label>
                <input type="text" id="jogo" name="jogo" placeholder="Ex: League of Legends, CS:GO, Valorant" required>
            </div>
            
            <div class="form-group">
                <label for="raridade">Raridade:</label>
                <select id="raridade" name="raridade" required>
                    <option value="">Selecione</option>
                    <option value="Comum">Comum</option>
                    <option value="Incomum">Incomum</option>
                    <option value="Rara">Rara</option>
                    <option value="Épica">Épica</option>
                    <option value="Lendária">Lendária</option>
                </select>
            </div>
            
            <div class="form-group">
                <label for="tipo">Tipo:</label>
                <select id="tipo" name="tipo" required>
                    <option value="">Selecione</option>
                    <option value="Personagem">Personagem</option>
                    <option value="Arma">Arma</option>
                    <option value="Veículo">Veículo</option>
                    <option value="Acessório">Acessório</option>
                    <option value="Emote">Emote</option>
                </select>
            </div>
            
            <div class="form-group">
                <label for="preco">Preço (R$):</label>
                <input type="number" id="preco" name="preco" step="0.01" min="0" required>
            </div>
            
            <div class="form-group">
                <label for="estoque">Estoque:</label>
                <input type="number" id="estoque" name="estoque" min="0" required>
            </div>
            
            <div class="form-actions">
                <button type="submit" class="btn-primary">Salvar</button>
                <a href="controller?action=listarProdutos" class="btn-secondary">Cancelar</a>
            </div>
        </form>
    </div>
</body>
</html>

