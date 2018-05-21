<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wilder
  Date: 25/04/18
  Time: 23:08
  To change this template use File | Settings | File Templates.

--%>

<%@include file="headers.jsp" %>

<table class="table">
    <c:forEach var="githuber" items="${githubers}" >
        <tr>
            <td><c:out value="${githuber.name}"/></td>
            <td><c:out value="${githuber.email}"/></td>
            <td><c:out value="${githuber.login}"/></td>
            <td>
                <div class="card" style="width: 18rem;">
                    <img class="card-img-top" src="${githuber.avatarUrl}" width=30%; alt="Avatar">
                </div>
            </td>
            <td>
                <form action="untrack" method="post">
                    <input name="id" type="hidden" value="${githuber.id}">
                    <input class="btn btn-primary" type="submit" value="Delete"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
