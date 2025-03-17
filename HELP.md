# 고객 건강 활동 정보 데이터 저장/조회

## API 호출
### josn 데이터 저장

* curl --location --request POST 'http://localhost:8080/api/healty-activity'

### 데이터 조회

* curl --location 'http://localhost:8080/api/healty-activity?searchType=DAILY'
* curl --location 'http://localhost:8080/api/healty-activity?searchType=MONTH'


## DB 조회
http://localhost:8080/h2-console

* Saved Settings: Generic H2 (Embedded)
* Driver Class: org.h2.Driver
* JDBC Url: jdbc:h2:mem:coding_test;MODE=Mysql
* User Name/password: test


