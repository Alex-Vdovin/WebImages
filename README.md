Приложение для выкладывания изображений на страницу.

Реализованы следующие страницы:
1) Страница регистрации /webimages/registration
2) Страница входа /webimages/login
3) Страница со всеми изображениями /webimages/
4) Страница с изображениями текущего пользователя /webimages/user/images
5) Страница добавления изображения /webimages/user/createImage

Также реализован функционал администратора. Администратор может удалять любые фото, в то время как обычный пользователь может удалять только свои. Администратора через данные формы зарегестрировать нельзя. Нужно заводить отдельно через БД.

В ходе работы были использованы:
Spring Boot (version 2.5.6)
Spring Security
Spring Data JPA
Lombok
Maven
PostgreSQL
Apache Freemarker
HTML/CSS

Также можно развернуть данное приложение на Tomcat 9. Архив webiamges.war находится в папке resources

