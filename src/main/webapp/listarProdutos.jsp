<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Model.Produto, Model.Usuario" %>
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
    <title>Listar Produtos - SkinMania</title>
    <link rel="stylesheet" type="text/css" href="Estilo/style.css">
</head>
<body>
    <div class="header">
        <h1>SkinMania</h1>
        <div class="header-actions">
            <a href="controller?action=cadastrarProduto" class="btn-primary">Novo Produto</a>
            <a href="controller?action=dashboard" class="btn-voltar">Voltar</a>
        </div>
    </div>
    
    <div class="container">
        <h2>Lista de Produtos (Skins)</h2>
        
        <table class="tabela-produtos">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Jogo</th>
                    <th>Raridade</th>
                    <th>Tipo</th>
                    <th>Preço</th>
                    <th>Estoque</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <%
                    ArrayList<Produto> produtos = (ArrayList<Produto>) request.getAttribute("produtos");
                    if (produtos != null && !produtos.isEmpty()) {
                        for (Produto p : produtos) {
                %>
                <tr>
                    <td><%= p.getId_produto() %></td>
                    <td><%= p.getNome_produto() %></td>
                    <td><%= p.getJogo() != null ? p.getJogo() : "-" %></td>
                    <td><span class="raridade <%= p.getRaridade() != null ? p.getRaridade().toLowerCase() : "" %>">
                        <%= p.getRaridade() != null ? p.getRaridade() : "-" %>
                    </span></td>
                    <td><%= p.getTipo() != null ? p.getTipo() : "-" %></td>
                    <td>R$ <%= String.format("%.2f", p.getPreco()) %></td>
                    <td><%= p.getEstoque() %></td>
                    <td class="acoes">
                        <a href="controller?action=editarProduto&id=<%= p.getId_produto() %>" class="btn-edit">Editar</a>
                        <a href="controller?action=removerProduto&id=<%= p.getId_produto() %>" 
                           class="btn-delete" 
                           onclick="return confirm('Tem certeza que deseja remover este produto?')">Remover</a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="8" class="empty">Nenhum produto cadastrado.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>

