<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>AnyWay Register</title>
    <link href="resources\css\style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="page login-page">
    <div class="container">
        <div class="form-outer text-center d-flex align-items-center">
            <div class="form-inner">
                <div class="logo text-uppercase">
                    <span>AnyWay</span><strong class="text-primary">Ticket</strong>
                </div>
                <p>Добро пожаловать! Для регистации введите данные</p>
                <form class="text-left form-validate" method="post" action="registerUser">
                    <div class="form-group-material">
                        <input id="register-username" type="text" name="username" required class="input-material" placeholder="Имя пользователя" pattern="[A-Za-z_]{6,15}">
                    </div>
                    <div class="form-group-material">
                        <input id="register-password" type="password" name="password" required class="input-material" placeholder="Пароль" pattern="^.{6,20}$">
                    </div>
                    <div class="form-group-material">
                        <input id="register-email" type="email" name="mail" required class="input-material" placeholder="Электронная почта">
                    </div>
                    <div class="form-group text-center">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input id="register" type="submit" value="Зарегестрироваться" class="btn btn-primary">
                    </div>
                </form>
                <small>У вас уже есть аккаунт?</small>
                <a href="login.html" class="signup">Войти</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>