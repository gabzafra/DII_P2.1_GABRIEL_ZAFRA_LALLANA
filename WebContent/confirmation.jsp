<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="es">
        <jsp:include page="components/head.jsp" />

        <body>
            <jsp:include page="components/header.jsp" />
            <div class="contact container mx-auto m-3">
                <a href="./admin?del=${requestScope.user.id}&conf=true" class="btn btn-success">Eliminar
                    ${requestScope.user.name}</a>
                <a href="./admin" class="btn btn-danger">Cancelar</a>
            </div>
            <jsp:include page="components/footer.jsp" />
        </body>

        </html>