<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listando</title>
    </head>
    <body>
        
        
        
        
        <table>
        <!-- percorre contatos montando as linhas da tabela -->
            <c:forEach var="contato" items="${contatos}" varStatus="id">
                <tr bgcolor="#${id.count % 2 == 0 ? 'aaee88' : 'ffffff'}">
                  <td>${contato.nome}</td>
                  <td>
                  <c:if test="${not empty contato.email}">
                    <a href="mailto:${contato.email}">${contato.email}</a>
                  </c:if>
                  </td>
                  <td>${contato.endereco}</td>
                  <td>${contato.data}</td>
                  <td><a href="removeContato?id=${contato.id}">Remover</a></td>
                 
                </tr>
            </c:forEach>
        </table>
        
     
        
    </body>
</html>


