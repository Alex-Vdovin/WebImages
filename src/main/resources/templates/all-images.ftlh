<!DOCTYPE html>
<html>
<head>
    <title>WebImages</title>
</head>
<body>
<h1>WebImages</h1>
<h2> <b> Пользователь: ${user.username}<b></h2><br>
<hr>
<h4>Изображения всех пользователей</h4>
<div>
<#if isAdmin>
    <#list images as img>
    <img src="/images/${img.id}" height="100px"/><br><br>
    ${img.user.username}
        <form action="/admin/delete" method="POST">
                 <input type="hidden" name="imageId" value="${img.id}">
                 <button type="submit">Удалить</button>
                 <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>

    </#list>
<#else>
<#list images as img>
    <img src="/images/${img.id}" height="100px"/><br><br></n>
    ${img.user.username}
</#list>
</#if>
</div>
<#if images?has_content>
    <nav>
         <ul>
             <!-- Предыдущая страница -->
             <#if (currentPage > 1)>
                 <li><a href="?page=${currentPage-1}&size=${size}>Предыдущая</a></li>
             </#if>

             <!-- Список страниц -->
             <#if (totalPages gt 1)>
                 <#list 1..totalPages as page>
                     <li><a href="?page=${page-1}&size=${size}">${page}</a></li>
                 </#list>
             </#if>

             <!-- Следующая страница -->
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