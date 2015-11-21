<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<style>
    .error {
        color: red; font-weight: bold;
    }
</style>
    <body>
        
        
        <h1>Bem vindo</h1>
        <form:form action="adicionaContato" commandName="contatoForm">
                Nome: <form:input path="nome" size="20" />
                <form:errors path="nome" cssClass="error"/>
                Email: <form:input path="email" size="20" />
                <form:errors path="email" cssClass="error"/>
                Endereco: <form:input path="endereco" size="30" />
                <form:errors path="endereco" cssClass="error"/>
                Data Nascimento: <input name="data" type="date" />
                <form:errors path="data" cssClass="error"/>
                
                <input type="submit" value="Enviar"/>
        </form:form>
     </body>
</html>
