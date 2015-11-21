<%@page import="br.com.fatec.loja.modelo.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
      <%User usu = (User)session.getAttribute("user"); %>
    
      <h1>Sessão iniciada com usuário <%=usu.getLogin()%> id:<%=usu.getId()%><a href="logout">Sair</a></h1>
    </body>
</html>
