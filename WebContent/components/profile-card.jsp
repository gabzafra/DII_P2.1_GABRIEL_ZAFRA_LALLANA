<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <div class="contact container mx-auto m-3">
        <a href="./profile?upd=${requestScope.detail.id}" class="btn btn-primary">Actualizar Datos</a>
        <a href="./change-pass.jsp" class="btn btn-primary">Cambiar password</a>
        <article>
            <h1>${requestScope.detail.name} ${requestScope.detail.surnames}</h1>
            <p><span>Email: </span><a href="mailto:email@email.com">${requestScope.detail.email}</a></p>
            <p><span>Teléfono: </span>${requestScope.detail.phone}</p>
        </article>
        <c:if test="${!empty requestScope.error}">
            <jsp:include page="/components/error.jsp">
                <jsp:param name="msj" value="${requestScope.error}" />
            </jsp:include>
        </c:if>
    </div>