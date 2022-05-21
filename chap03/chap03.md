# Chap03 데이터로 작업하기
## 📖 Chap03.1 에서 배우는 내용  
#### 🏁 [목표] Spring JDBC 개념과 사용법 익히기
#### 🏁 [익혀야 되는 개념]
> 1) Spring 에서 JDBC를 이용하여 Database를 다루는 큰 그림
> 2) Plain JDBC와 Spring JDBC의 차이, Spring JDBC를 사용하는 이유?
> 3) Datasource의 Connection Pool 개념
> 4) JdbcTemplate을 이용하여 CRUD동작 구현하기 
> 5) SimpleJdbcInsert사용법 익히기 (+objectMapper 개념)
> 6) 다른 방법? MyBatis, JPA, Hibernate, QueryDSL..등등 

## 3.1.0 Spring에서 Database를 다루는 큰그림
![image](https://user-images.githubusercontent.com/55049159/169668555-346e2b04-6c06-4f1e-9c26-3f8b308c4b75.png)

![image](https://user-images.githubusercontent.com/55049159/169668538-65d3d66e-739d-47ed-af38-8a1c2da0e364.png)

## 3.1.1 Plain JDBC vs Spring JDBC
> Plain JDBC <br>
> Write unnecessary code to handle exceptions, opening and closing database connections, etc

<br>

> Spring JDBC
> Takes care of all the low-level details 
> opening the connection
> prepare and execute the SQL statement
> process exceptions
> handle transactions
> close the connection

![image](https://user-images.githubusercontent.com/55049159/169668591-c044c9e1-0e96-4f6f-8907-1de1ebe186c9.png)

## 3.1.2 Datasource 과 Connection Pool의 개념 

![image](https://user-images.githubusercontent.com/55049159/169668576-f7d01c6e-d0d2-4143-b7aa-b84a5c23b8ed.png)


## 3.1.3 JdbcTemplate을 이용하여 CRUD동작 구현하기
## 3.1.4 SimpleJdbcInsert 사용하기


