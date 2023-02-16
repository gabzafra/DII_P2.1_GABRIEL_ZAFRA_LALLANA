<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <header>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark p-1">
            <a class="navbar-brand" href="index.jsp">Home</a>
            <ul class="navbar-nav">

                <c:if test="${sessionScope.name == null}">
                    <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
                    <li class="nav-item"><a class="nav-link" href="register.jsp">Registro</a></li>
                </c:if>
                <c:if test="${sessionScope.name != null}">
                    <li class="nav-item"><a class="nav-link"
                            href="<%=request.getContextPath()%>/profile">${sessionScope.name}</a></li>
                    <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/login">Logout</a></li>
                </c:if>
            </ul>
        </nav>
    </header>