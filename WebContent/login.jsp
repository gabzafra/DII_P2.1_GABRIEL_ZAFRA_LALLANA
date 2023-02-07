<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="es">
        <jsp:include page="components/head.jsp" />

        <body>
            <jsp:include page="components/header.jsp" />
            <div id="contact-form" class="contact container mx-auto m-3">
                <form action="<%=request.getContextPath()%>/login" method="POST">
                    <fieldset>
                        <legend>Login</legend>
                        <div class="form-floating mb-3">
                            <input type="email" class="form-control" name="mail" placeholder="Email"
                                value="${requestScope.email}">
                            <label for="mail">Email</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="password" class="form-control" name="pass" placeholder="Password"
                                value="${requestScope.password}">
                            <label for="pass">Password</label>
                        </div>
                        <button type="submit" class="btn btn-primary">Entrar</button>
                        <a href="index.jsp" class="btn btn-danger">Volver</a>
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