<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>AnyWay Enter</title>
    <link href="resources\css\style.css" rel="stylesheet" type="text/css"/>
</head>

<body>
    <div class="page login-page">
      <div class="container">
        <div class="form-outer text-center d-flex align-items-center">
            <div class="form-inner">
              <div class="logo text-uppercase"><span>AnyWay</span><strong class="text-primary">Ticket</strong></div>
              <p>Для продолжения необходима авторизация:</p>
              <c:url var="loginUrl" value="/login" />
              <form action="${loginUrl}" method="post" class="text-left form-validate">
                  <c:if test="${param.error != null}">
                      <div class="alert alert-danger">
                          <p>Неверное имя пользователя и пароль</p>
                      </div>
                  </c:if>
                  <c:if test="${param.logout != null}">
                      <div class="alert alert-success">
                          <p>Вы вошли успешно</p>
                      </div>
                  </c:if>
                  <div class="form-group-material">
                      <input id="login-username" type="text" name="username" required class="input-material" placeholder="Имя пользователя">

                  </div>
                  <div class="form-group-material">
                      <input id="login-password" type="password" name="password" required class="input-material" placeholder="Пароль">

                  </div>
                  <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                  <div class="form-group text-center">
                      <input type="submit" class="btn btn-primary" value="Войти">
                  </div>
              </form>
                <small>У вас еще нет аккаунта? </small>
                <a href="registration" class="signup">Регистрация</a>
          </div>
        </div>
      </div>
    </div>
</body>
</html>