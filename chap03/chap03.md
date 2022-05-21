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
> <b>Plain JDBC</b> <br>
> 모든거를 개발자가 직접 다 해야함. 오류처리 커낵션 연결 등등.. 

```java
public void GettingRows() {
    Connection conn=null;
    Statement stmt=null;
    Resultset rset=null;

   try {
        conn = dataSource.getConnection();
        stmt = conn.createStatement (); 
        rset = stmt.executeQuery ("select count(*) from customers");
        while (rset.next()) {
	   count = rs.getInt(1);
        }

   } catch (SQLException e) {
	LOGGER.error(e); throw e;
    }
   finally {
             //code to clean up resources
   }
```

<br>

> <b>Spring JDBC</b> <br> 
> low levels에 대해 관심을 끄게 해줌 <br>
> connection을 열어줌<br>
> SQL statement를 준비하고 실행시켜줌<br>
> 에러처리 <br>
> 트랜잭션 핸들링<br>
> connection 닫아줌

```java
@Repository
public class SpringDaoImpl {
    private JdbcTemplate jdbcTemplate;

    @Autowired
     public void setDataSource(DataSource dataSource) {
         this.jdbcTemplate = new JdbcTemplate(dataSource);
     }

     public int getCustomerCount() {
         return jdbcTemplate.queryForInt("select count(*) from customers");
     }
}
```

![image](https://user-images.githubusercontent.com/55049159/169668591-c044c9e1-0e96-4f6f-8907-1de1ebe186c9.png)

#### 결국 Spring jdbc를 사용하는 우리가 해야하는 부분?
- 커넥션 파라미터 정의
- SQL statement 쓰기
- 파라미터의 선언과 파라미터 value 제공

## 3.1.2 Datasource 과 Connection Pool의 개념 

![image](https://user-images.githubusercontent.com/55049159/169668576-f7d01c6e-d0d2-4143-b7aa-b84a5c23b8ed.png)


## 3.1.3 JdbcTemplate을 이용하여 CRUD동작 구현하기

### 의존성 추가

### 리포지터리 정의 (DAO) 
- 인터페이스를 만들고 그 함수들을 Override하는 방식으로 구현 

### RowMapper 사용 

### 스키마 정의 

#### 💡 update문을 사용하고 곧장 primary key를 return하고 싶을때는 기존의 jdbcTemplate을 사용하는 것보다 더 나은 방법이 존재한다. 

## 3.1.4 SimpleJdbcInsert 사용하기
- SimpleJdbcInsert는 간단하게 데이터를 저장하기 위해 만들어진 구현체이다.
- 기존의 의존성 주입으로 JdbcTemplate을 받는거와는 조금 다르게 설정을 해줘야 하는 부분이 있다.

- Map 자료구조로 필요한 파라미터 값들을 미리 대입시키고, SimpleJdbcInsert.executeAndReturnKey(Map).longValue() 를 수행하면 곧장 primary key 값을 얻어낼 수 있다.

- 이 동작이 가능하게 한 핵심은 생성자 쪽의 withTableName()과 usingGeneratedKeyColumns다.

- primary key 값을 얻어내는 과정은 우아해졌지만, Map 자료구조를 사용하는게 다소 아쉽다.
이를 보완하기 위해 SqlParameterSource 인터페이스가 활용되는데, 스프링에서 SqlParameterSource의 구현체를 다수 제공하고 있다.

- 
## 3.1.5 결론
최근 JPA 나 MyBatis 같은 더욱 강력한 도구들이 나와있지만, 간단한 초기 설정과 낮은 학습비용 덕분에 여전히 JDBC를 사용하는 곳이 존재한다. 또 몰라서 못쓰는 것과, 알고 안쓰는 것은 분명히 다르다고 생각한다. 
