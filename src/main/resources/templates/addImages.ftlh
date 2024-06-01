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

        h2, h3 {
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

        input[type="file"], input[type="submit"] {
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

        div {
            text-align: center;
        }

        a {
            color: #ff6347;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h2>Добрый день, ${user.username}!</h2>
<h3>Добавить новое изображение</h3>
<form action="/webimages/user/createImage" method="post" enctype="multipart/form-data">
    Изображение: <input type="file" name="imageFile"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Добавить изображение"/>
</form>
<br><br>
<div><a href="/webimages/user/images">Перейти к своим изображениям</a></div><br>
<div><a href="/webimages/">Перейти ко всем изображениям</a></div><br><br>
</body>
</html>
