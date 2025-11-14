<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>SkinMania - Login</title>
    <link rel="stylesheet" type="text/css" href="Estilo/style.css">
</head>
<body>
    <div class="login-container">
        <h1>SkinMania</h1>
        <h2>Sistema de Vendas de Skins</h2>
        
        <form action="controller?action=autenticar" method="post" class="login-form">
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            
            <div class="form-group">
                <label for="senha">Senha:</label>
                <input type="password" id="senha" name="senha" required>
            </div>
            
            <% if (request.getAttribute("erro") != null) { %>
                <div class="erro">
                    <%= request.getAttribute("erro") %>
                </div>
            <% } %>
            
            <button type="submit" class="btn-primary">Entrar</button>
        </form>
    </div>
</body>
</html>

