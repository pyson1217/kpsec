# 개발 환경

- IDE : Intellij
- Language : Java(v8)
- DataBase : H2
- UI : Swagger-ui(v2.9.2)
- Framework : Spring Boot(v2.4.5), JPA
- Build Tool : Gradle
- Test : Junit(v4.12)

# 실행 방법

> 1. Github file download
> 2. Intellij project open
> 3. Project Run
> 4. URL : http://localhost:8080/swagger-ui.html Click!

# 문제해결 방법

### 문제 1

> 거래내역과 계좌정보를 조인해 데이터를 뽑고 거래금액은 거래성사 된 것 중에 (원금 - 수수료)로 금액을 뽑아 내림차순으로 정렬해 가장 높은 금액을 갖은 고객을 추출

```ts
GET
Response body
[
  {
    "year": 2018,
    "accountNo": "11111114",
    "accountName": "테드",
    "sumAmt": 28992000
  },
  ...
]

```

### 문제 2

> 거래가 없는 고객은 거래내역이 아에 없는 사람을 거래하지 않은 고객으로 판별해 거래 있는 고객들을 추출하고 계좌정보로 비교해 거래하지 않은 고객을 추출

```ts
GET
Response body
[
  {
    "year": 2018,
    "accountNo": "11111115",
    "accountName": "사라"
  },
  ...
]
```

### 문제 3

> 관리점 코드를 갖고있는 계좌정보와 계좌금액을 갖고있는 거래내역을 모두 조인해 추출 거래금액은 (원금 - 수수료)로 관리점별 수수료를 제외한 금액을 추출
> <br><br>`적용하지 못한 문제`<br>
> JPA Return 값을 interface Projection을 사용 하여 추출했는데 다중배열 처리를 하는 방법을 찾지 못해 아래와 같이 처리할 수 밖에 없었습니다.
> 예상 해결방법은 Repository에서 Query를 사용해서 추출한 방법이 아닌 Entity 설정한 곳에서 @ManyToOne 다대일, 일대다 등 맞는 상관관계를 JPA 어노테이션으로 설정해 Join 해서 값을 받아서 처리해주면 가능 할 것 같습니다.

```ts
GET
Response Body
[
  {
    "year": 2018,
    "sumAmt": 38484000,
    "branchCode": "B",
    "branchName": "분당점"
  },
  ...
]

요구된 JSON처리
GET
Response Body
[
  {
    "year": 2018,
        "dataList":[
            {"sumAmt": 38484000,
            "branchCode": "B",
            "branchName": "분당점"},
            ...
        ]
  },
  ...
]
```

### 문제 4

> 분당점을 판교점으로 변경하고 지점명을 받아 해당 지점 거래금액 합계 (원금 - 수수료)로 추출, 분당점 이외 없는 지점 검색 시 Exception 처리

```ts
POST (status = 200)
Request Body
{
  "brName": "판교점"
}
Response Body
{
  "sumAmt": 171181500,
  "branchCode": "A",
  "branchName": "판교점"
}

POST (status = 404)
Request Body
{
  "brName": "분당점"
}
Response Body
{
  "timestamp": 1626895471726,
  "status": 404,
  "error": "Not Found",
  "message": "br code not found error",
  "path": "/assignment/a4/branch/name/amount/sum"
}
```

# 테스트

- Junit4 사용하여 REST API 점검
- TEST 시 한글깨짐으로 UTF-8 Config을 추가
- REST API Get 3개, Post 1개 테스트
- Controller URL을 통한 호출을 통해 데이터 결과 검증
