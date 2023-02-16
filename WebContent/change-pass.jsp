<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<!DOCTYPE html>
		<html lang="es">
		<jsp:include page="components/head.jsp" />

		<body>
			<jsp:include page="components/header.jsp" />
			<div id="contact-form" class="contact container mx-auto m-3">
				<form action="<%=request.getContextPath()%>/profile?pass" method="POST">
					<fieldset>
						<legend>Change Pass</legend>
						<div class="form-floating mb-3">
							<input type="password" class="form-control" name="old-pass" placeholder="Password antiguo"
								value=""> <label for="old-pass">Password antiguo</label>
						</div>
						<div class="form-floating mb-3">
							<input type="password" class="form-control" name="new-pass1" placeholder="Nuevo password"
								value=""> <label for="new-pass1">Nuevo password</label>
						</div>
						<div class="form-floating mb-3">
							<input type="password" class="form-control" name="new-pass2"
								placeholder="Repetir nuevo password" value=""> <label for="new-pass2">Repetir nuevo
								password</label>
						</div>
						<button type="submit" class="btn btn-primary">Cambiar</button>
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