# Chap03 데이터로 작업하기
## 📖 이장에서 배우는 내용 
- 스프링 JdbcTemplate 사용하기
- simpleJdbcInsert를 사용해서 데이터 추가하기

####  ➕ 익혀야 되는 개념
1) Spring 에서 Database를 다루는 큰 그림
2) Plain JDBC(Java Jdbc) vs Spring JDBC(Spring JdbcTemplate)
3) Datasource의 Connection Pool 개념
4) JdbcTemplate을 이용하여 CRUD동작 구현하기 

## 3.1 JDBC를 사용해서 데이터를 읽고 쓰기
### 3.1.0 DB 사용법 두가지 - JDBC / JPA 
####  1. Spring JDBC 
- JdbcTemplate 클래스에 기반을 둠. 
- JDBC를 사용할때 요구되는 상투적인 코드없이 개발자가 RDBMS에 대한 SQL 연산을 수행 할 수 있는 방법을 제공  

#### ex 1. JdbcTemplate를 사용하지 않고 데이터베이스 쿼리사용

#### ex 2. JdbcTemplate를 사용하기 

### 3.1.1 퍼시스턴스를 고려한 도메인 객체 수정하기 

### 3.1.2 JdbcTemplate 사용하기 


#### 2. JPA   
