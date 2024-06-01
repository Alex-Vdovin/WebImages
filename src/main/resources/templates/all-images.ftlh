<!DOCTYPE html>
<html>
<head>
    <title>WebImages</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h1>WebImages</h1>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            line-height: 1.6;
            color: #333;
            background-color: #F5F5F5;
            padding: 40px;
        }

        h1, h2 , h3, h4 {
            color: #708090;
            text-align: center;
        }

        h1 {
            margin-top: 0;
            text-align: center;
        }

        h2 {
            margin-bottom: 30px;
        }

        hr {
            border: none;
            border-top: 1px solid #eee;
            margin-bottom: 30px;
        }

        a {
            color: #708090;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

     .pagination {
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 15px;
            list-style-type: none;
            padding: 0;
            margin: 30px 0;
        }

     .pagination li {
            margin: 0 7px;
        }

     .current-page {
            font-weight: bold;
            background-color: #e9ecef;
            border-radius: 5px;
            padding: 7px 14px;
        }

      .image-item {
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 10px;
            margin-bottom: 20px;
        }

        img {
            max-width: 300px;
            height: auto;
            border-radius: 5px;
        }

        button {
            background-color: #007BFF;
            color: white;
            border: none;
            padding: 12px 24px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }

        button:hover {
            background-color: #0056b3;
        }

        form input[type="hidden"] {
            display: none;
        }
    </style>
    <h2> <b> Пользователь: ${user.username}<b></h2><br>
    <hr>
    <h2>Изображения всех пользователей</h2>
    <h2><a href="/webimages/user/createImage">Добавить изображение</a></h2><br>
    <h2><a href="/webimages/user/images">Перейти к своим изображениям</a></h2><br><br>
    <div class="image-container">
    <#if isAdmin>
        <#list images as img>
        <div class="image-item">
            <img src="/webimages/images/${img.id}" />
            Изображение пользователя: <b>${img.user.username}</b>
            <form action="/webimages/admin/delete" method="POST">
                <input type="hidden" name="imageId" value="${img.id}">
                <button type="submit">Удалить</button>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
            </form>
        </div>
        </#list>
    <#else>
        <#list images as img>
        <div class="image-item">
            <img src="/webimages/images/${img.id}" />
            Изображение пользователя: <b>${img.user.username}</b>
        </div>
        </#list>
    </#if>
    </div>
    <#if images?has_content>
    <nav>
        <ul class="pagination">
            <#if (currentPage > 1)>
                <li><a href="?page=${currentPage-1}&size=${size}>Предыдущая</a></li>
            </#if>

            <!-- Список страниц -->
            <#if (totalPages gt 1)>
                <#list 1..totalPages as page>
                    <li class="current-page"><a href="?page=${page-1}&size=${size}">${page}</a></li>
                </#list>
            </#if>


            <#if (currentPage lt totalPages - 1)>
                <li><a href="?page=${currentPage+1}&size=${size}>Следующая</a></li>
            </#if>
        </ul>
    </nav>
        </#if>
    <hr>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
</body>
</html>
