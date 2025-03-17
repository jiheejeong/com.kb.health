# 고객 건강 활동 정보 데이터 저장/조회

## API 호출
### josn 데이터 저장

* curl --location --request POST 'http://localhost:8080/api/healty-activity'

### 데이터 조회

* curl --location 'http://localhost:8080/api/healty-activity?searchType=DAILY'
* curl --location 'http://localhost:8080/api/healty-activity?searchType=MONTH'


## H2 DB 조회 방법
http://localhost:8080/h2-console

* Saved Settings: Generic H2 (Embedded)
* Driver Class: org.h2.Driver
* JDBC Url: jdbc:h2:mem:coding_test;MODE=Mysql
* User Name/password: test

## Mysql 데이터 조회

### Daily
* select DATE_FORMAT(start_time, '%Y-%m.%d') Daily, sum(steps) steps, sum(distance_value) distance, sum(calories_value) calories, record_key
from health_activity
group by record_key, DATE_FORMAT(start_time, '%Y-%m.%d');

### Monthly
* select DATE_FORMAT(start_time, '%Y-%m') Monthly, sum(steps) steps, sum(distance_value) distance, sum(calories_value) calories, record_key
from health_activity
group by record_key, DATE_FORMAT(start_time, '%Y-%m');

## 결과물 
https://github.com/jiheejeong/com.kb.health/tree/master/src/main/resources/dataresult


