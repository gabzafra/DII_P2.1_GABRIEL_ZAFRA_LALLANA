<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="es">
        <jsp:include page="components/head.jsp" />

        <body>
            <jsp:include page="components/header.jsp" />
            <div id="contact-form" class="contact container mx-auto m-3">
                <form action="<%=request.getContextPath()%>/profile?update=${requestScope.user.id}" method="POST">
                    <fieldset>
                        <legend>Actualizar</legend>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" name="name" placeholder="Nombre"
                                value="${requestScope.user.name}"> <label for="name">Nombre</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" name="surnames" placeholder="Apellidos"
                                value="${requestScope.user.surnames}"> <label for="surnames">Apellidos</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="email" class="form-control" name="mail" placeholder="Email"
                                value="${requestScope.user.email}"> <label for="mail">Email</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="number" class="form-control" name="phone" placeholder="TelÃ©fono"
                                value="${requestScope.user.phone}">
                            <label for="phone">Teléfono</label>
                        </div>
                        <button type="submit" class="btn btn-primary">Actualizar</button>
                        <a href="<%=request.getContextPath()%>/profile" class="btn btn-danger">Volver</a>
                    </fieldset>
                </form>
                <p>${param.error}</p>
                <c:if test="${!empty requestScope.error}">
                    <jsp:include page="/components/error.jsp">
                        <jsp:param name="msj" value="${requestScope.error}" />
                    </jsp:include>
                </c:if>
            </div>
            <jsp:include page="components/footer.jsp" />
        </body>

        </html>