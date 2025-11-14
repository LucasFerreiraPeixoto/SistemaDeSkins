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
    <title>SkinMania - Dashboard</title>
    <link rel="stylesheet" type="text/css" href="Estilo/style.css">
</head>
<body>
    <div class="header">
        <h1>SkinMania - Sistema de Vendas de Skins</h1>
        <div class="user-info">
            <span>Bem-vindo, <strong><%= usuario.getNome() %></strong>!</span>
            <a href="controller?action=logout" class="btn-logout">Sair</a>
        </div>
    </div>
    
    <div class="container">
        <div class="menu-grid">
            <div class="menu-card">
                <h3>Produtos</h3>
                <a href="controller?action=listarProdutos" class="btn-menu">Listar Produtos</a>
                <a href="controller?action=cadastrarProduto" class="btn-menu">Cadastrar Produto</a>
            </div>
            
            <div class="menu-card">
                <h3>Vendas</h3>
                <a href="controller?action=vender" class="btn-menu">Realizar Venda</a>
                <a href="controller?action=listarVendas" class="btn-menu">Listar Vendas</a>
            </div>
        </div>
    </div>
</body>
</html>

