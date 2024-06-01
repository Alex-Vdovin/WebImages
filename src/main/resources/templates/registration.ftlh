<!DOCTYPE html>
<html>
<head>
    <title>WebImages</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            color: #444;
            background-color: #f9f9f9;
            padding: 20px;
        }

        h1, h4 {
            color: #ff6347;
            text-align: center;
        }

        form {
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 20px;
            width: 80%;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }

        input[type="text"], input[type="password"], input[type="submit"] {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        input[type="submit"] {
            background-color: #ff6347;
            color: white;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #cc5500;
        }

        a {
            color: #ff6347;
            text-decoration: none;
            display: block;
            margin: 20px auto;
            text-align: center;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h1>WebImages</h1><hr>
<h4>Регистрация</h4>
<form action="/webimages/registration" method="post">
    Имя пользователя: <input type="text" name="username"><br><br>
    Пароль: <input type="password" name="password"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Вход"/>
    <h4><a href="/webimages/login">Уже зарегестрированы?</a></h4>
</form>
</body>
</html>
