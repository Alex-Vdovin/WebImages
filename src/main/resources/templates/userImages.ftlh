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

        h1, h2, h4 {
            color: #ff6347;
            text-align: center;
        }

        h1 {
            margin-top: 0;
        }

        h2, h4 {
            margin-bottom: 15px;
        }

        hr {
            border: none;
            border-top: 1px solid #ccc;
            margin-bottom: 20px;
        }

        a {
            color: #ff6347;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

     .pagination {
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 10px;
            list-style-type: none;
            padding: 0;
            margin: 20px 0;
        }

     .pagination li {
            margin: 0 5px;
        }

     .current-page {
            font-weight: bold;
            background-color: #f0f0f0;
            border-radius: 5px;
            padding: 5px 10px;
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
            margin-bottom: 10px;
            border-radius: 5px;
        }

        button {
            background-color: #ff6347;
            color: white;
            border: none;
            padding: 8px 16px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }

        button:hover {
            background-color: #cc5500;
        }

        form input[type="hidden"] {
            display: none;
        }
    </style>
    <hr>
    <h1>Изображения</h1>
    <h1> <b> Пользователь: ${user.username}<b></h1><br>
    <div>
    <h2><a href="/webimages/user/createImage">Добавить изображение</a></h2><br>
    <h2><a href="/webimages/">Перейти ко всем изображениям</a></h2><br><br>
    <#list images as img>
             <div class="image-item">
                 <img src="/webimages/user/images/${img.id}" height="200px"/>
                 <form action="/webimages/user/images/delete" method="POST">
                     <input type="hidden" name="imageId" value="${img.id}">
                     <button type="submit">Удалить</button>
                     <input type="hidden" name="_csrf" value="${_csrf.token}">
                 </form>
             </div>
    </#list>
    </div>
    <#if images?has_content>
    <nav>
        <ul class="pagination">
            <#if (currentPage > 1)>
                <li><a href="?page=${currentPage-1}&size=${size}>Предыдущая</a></li>
            </#if>


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
</body>
</html>
