<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wilder
  Date: 25/04/18
  Time: 23:08
  To change this template use File | Settings | File Templates.

--%>

<%@include file="headers.jsp" %>


<ul class="list-group list-group-flush">
    <c:forEach  var="githuber" items="${githubers}">
        <li class="list-group-item">
            <div class="card" style="width: 18rem;">
                <img class="card-img-top" src="${githuber.avatarUrl}" width=60%; alt="Avatar">
                <div class="card-body">
                    <p class="card-text">${githuber.login}</p>
                </div>
            </div>
        </li>
    </c:forEach>

</ul>


</body>
</html>
