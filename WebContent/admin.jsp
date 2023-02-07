<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="es">
        <jsp:include page="components/head.jsp" />

        <body>
            <jsp:include page="components/header.jsp" />
            <jsp:include page="components/profile-card.jsp" />
            <div class="contact-list container">
                <form action="<%=request.getContextPath()%>/admin" method="POST">
                    <fieldset>
                        <legend>Buscar usuario</legend>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" name="filter" placeholder="Nombre"
                                value="${requestScope.filter}">
                            <label for="filter">Nombre</label>
                        </div>
                        <button type="submit" class="btn btn-success">Buscar</button>
                        <a href="./admin" class="btn btn-primary">Limpiar</a>                    
                    </fieldset>
                </form>
                <h1>Usuarios</h1>
                <c:forEach items="${requestScope.list}" var="user">
                    <div class="row align-items-center border p-2">
                        <div class="col">
                            <p>${user.name}</p>
                        </div>
                        <div class="col-1">
                            <a href="./admin?upd=${user.id}" class="btn btn-primary">Editar</a>
                        </div>
                        <div class="col-1">
                            <a href="./admin?del=${user.id}" class="btn btn-danger">Borrar</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <jsp:include page="components/footer.jsp" />
        </body>

        </html>