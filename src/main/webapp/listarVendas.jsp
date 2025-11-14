<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Model.Venda, Model.Usuario" %>
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
    <title>Listar Vendas - SkinMania</title>
    <link rel="stylesheet" type="text/css" href="Estilo/style.css">
</head>
<body>
    <div class="header">
        <h1>SkinMania</h1>
        <div class="header-actions">
            <a href="controller?action=vender" class="btn-primary">Nova Venda</a>
            <a href="controller?action=dashboard" class="btn-voltar">Voltar</a>
        </div>
    </div>
    
    <div class="container">
        <h2>Histórico de Vendas</h2>
        
        <table class="tabela-vendas">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Vendedor</th>
                    <th>Produto</th>
                    <th>Quantidade</th>
                    <th>Valor Total</th>
                    <th>Data</th>
                </tr>
            </thead>
            <tbody>
                <%
                    ArrayList<Venda> vendas = (ArrayList<Venda>) request.getAttribute("vendas");
                    if (vendas != null && !vendas.isEmpty()) {
                        double totalGeral = 0;
                        for (Venda v : vendas) {
                            totalGeral += v.getValor_total();
                %>
                <tr>
                    <td><%= v.getId_venda() %></td>
                    <td><%= v.getNome_usuario() %></td>
                    <td><%= v.getNome_produto() %></td>
                    <td><%= v.getQuantidade() %></td>
                    <td>R$ <%= String.format("%.2f", v.getValor_total()) %></td>
                    <td><%= v.getData_venda() != null ? new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(v.getData_venda()) : "-" %></td>
                </tr>
                <%
                        }
                %>
                <tr class="total-row">
                    <td colspan="4"><strong>Total Geral:</strong></td>
                    <td colspan="2"><strong>R$ <%= String.format("%.2f", totalGeral) %></strong></td>
                </tr>
                <%
                    } else {
                %>
                <tr>
                    <td colspan="6" class="empty">Nenhuma venda registrada.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>

