              Установка демо-версии проекта Books_Directory

1. Восстановить из GitHub проект Books_Directory, например на диск D в папку Books_Directory

2. Восстановить бд demo (вариант удаленной БД postgres)
2.1.
CREATE DATABASE demo
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Russian_Russia.1251'
       LC_CTYPE = 'Russian_Russia.1251'
       CONNECTION LIMIT = -1;
GRANT CONNECT, TEMPORARY ON DATABASE demo TO public;
GRANT ALL ON DATABASE demo TO postgres;

2.2.  Восстановить books_tar.backup  на бд demo (контрольный пример)

3. Запускаем package.bat для сборки проекта.

4. Запуск проекта, как вариант из Eclipse

    localhost:8800/hello.htm

5. Пароли для входа в систему (двойные кавычки не учитывать)

1) Login = "admin" 
   password = "a999"
2) Login = "user" 
   password="u000"

