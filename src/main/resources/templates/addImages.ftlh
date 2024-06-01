<!DOCTYPE html>
<html>
<head>
    <title>WebImages</title>
</head>
<body>
Добрый день, ${user.username}!
<h3>Добавить новое изображение</h3>
<form action="/user/createImage" method="post" enctype="multipart/form-data">
    Изображение: <input type="file" name="imageFile"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Добавить изображение"/>
</form>
</body>
</html>