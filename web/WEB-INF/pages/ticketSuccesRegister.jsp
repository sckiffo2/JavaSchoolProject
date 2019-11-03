<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<c:set var="title" value="Билет" />
<jsp:include page="parts/head.jsp">
    <jsp:param name="title" value="${title}"/>
</jsp:include>
<body>
<jsp:include page="parts/sidemenu.jsp"/>
<div class="main">
    <jsp:include page="parts/header.jsp"/>
    <div class="info">
        <section class="forms">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="card">
                            <div class="card-body">
                                <h4>Билет удачно зарегистрирован</h4>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>
</body>
</html>






