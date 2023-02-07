<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<!DOCTYPE html>
		<html lang="es">
		<jsp:include page="components/head.jsp" />

		<body>
			<jsp:include page="components/header.jsp" />
			<div id="contact-form" class="contact container mx-auto m-3">
				<form action="<%=request.getContextPath()%>/register" method="POST">
					<fieldset>
						<legend>Register</legend>
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
						<div class="form-floating mb-3">
							<input type="password" class="form-control" name="pass1" placeholder="Password"
								value="${requestScope.user.password}"> <label for="pass1">Password</label>
						</div>
						<div class="form-floating mb-3">
							<input type="password" class="form-control" name="pass2" placeholder="Repetir password"
								value="${requestScope.pass2}"> <label for="pass2">Repetir password</label>
						</div>
						<button type="submit" class="btn btn-primary">Crear</button>
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