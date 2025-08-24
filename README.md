# 콘서트 티켓 예매 시스템


## 프로젝트 목표

사용자들이 원하는 콘서트 티켓을 온라인으로 예메할 수 있는 시스템을 제공한다.
단, 이 시스템은 티켓 오픈 시점의 폭발적인 동시 접속 트래픽을 감당하고, 단 하나의 좌석도 중복 예매되지 않는 데이터의 정합성을 보장해야한다.


## 요구사항

### 1. 인증

#### 사용자는 회원가입하고 로그인 할 수 있다.

**세부 명세**
- 이메일 주소는 시스템 전체에서 유일해야 한다.
- 로그인에 성공하면, 서버는 사용자를 식별할 수 있는 인증 토큰 JWT 를 발급한다.
- 이후의 모든 요청은 이 인증 토큰을 포함해야만 접근 할 수 있다.


### 2. 정보 조회

#### 인증된 사용자는 자신의 정보를 조회하고 수정할 수 있다.

**세부 명세**
- 회원가입 정보를 조회 할 수 있다.
- 회원은 닉네임을 변경 할 수 있다.
#### 사용자는 예매 가능한 전체 콘서트 목록을 조회 할 수 있다.

**세부 명세**
- 각 콘서트의 이름, 공연 날짜 정보를 포함한다.

#### 사용자는 특정 콘서트를 선택했을 때 예매 가능한 좌석 정보를 조회할 수 있다.

**세부 명세**
- 각 좌석의 번호 , 등급 , 가격, 그리고 현재 **예매 가능 상태** (`AVAILABLE`) 를 포함한다.


### 3. 티켓 예매

#### 사용자는 원하는 좌석을 선택하여 예매를 요청할 수 있다.

**세부 명세**

- 오직 예매 가능 상태 (`AVAILABLE`) 에서만 예매할 수 있다.
- 예매 요청이 성공하면 해당 좌석의 상태는 (`BOOKED`) 로 변경된다.
- 예매 내역은 요청한 사용자의 계정에 귀속되어 저장된다.
- 예매한 좌석은 당일 하루 전에만 취소할 수 있다.

**세부명세**

- 여러 사용자가 정확히 동일한 좌석을 오차 없이 동시에 요청하더라도, 오직 단 한명의 사용자만 예매에 성공해야 한다.
- 대규모 트래픽 상황에서도 예매 API는 500ms 이내에 응답 해야한다.


## 프로젝트 제외 사항

- 실제 돈이 오가는 PG 연동은 하지 않는다. 예매 요청이 성공하면 결제도 성공했다고 가정하고 진행한다.
- 관리자 콘서트 등록 기능은 만들지 않는다. (테스트 데이터는 DB에 직접 입력한다.)






## API 명세서 초안


| Method  | API                             | 기능               |
| ------- | ------------------------------- | ---------------- |
| `POST`  | /api/users                      | 회원 가입            |
| `GET`   | /api/users/me                   | 회원 정보            |
| `PATCH` | /api/users/me                   | 회원 정보 수정         |
| `GET`   | /api/users/me/bookings          | 내 예매내역 조회        |
|         |                                 |                  |
| `GET`   | /api/concerts                   | 전체 콘서트 목록 조회     |
| `GET`   | /api/concerts/{concertId}       | 특정 콘서트 조회        |
| `GET`   | /api/concerts/{concertId}/seats | 특정 콘서트의 좌석 목록 조회 |
|         |                                 |                  |
| `POST`  | /api/bookings                   | 특정 좌석을 예매하는 요청   |



### 테이블


#### USERS

| 컬럼명         | 데이터타입        | 제약 조건 및 설정                   |
| ----------- | ------------ | ---------------------------- |
| id **(PK)** | BIGINT       | PRIMARY KEY , AUTO INCREMENT |
| email       | VARCHAR(255) | NOT NULL , UNIQUE            |
| password    | VARCHAR(255) | NOT NULL                     |
| nickname    | VARCHAR(50)  | NOT NULL                     |
| created_at  | DATETIME     | NOT NULL                     |



#### CONCERTS

| 컬럼명          | 데이터타입        | 제약 조건 및 설정                   |
| ------------ | ------------ | ---------------------------- |
| id **(PK)**  | BIGINT       | PRIMARY KEY , AUTO INCREMENT |
| name         | VARCHAR(255) | NOT NULL                     |
| concert_date | DATETIME     | NOT NULL                     |


#### SEATS

| 컬럼명                 | 데이터타입         | 제약 조건 및 설정                         |
| ------------------- | ------------- | ---------------------------------- |
| id **(PK)**         | BIGINT        | PRIMARY KEY , AUTO INCREMENT       |
| seat_number         | VARCHAR(20)   | NOT NULL (e.g "A1", "R25")         |
| status              | VARCHAR       | NOT NULL ( `AVAILABLE` , `BOOKED`) |
| price               | DECIMAL(10,2) | NOT NULL                           |
| concert_id **(FK)** | BIGINT        | NOT NULL , FOREIGN KEY             |

#### BOOKINGS

| 컬럼명              | 데이터타입    | 제약 조건 및 설정                       |
| ---------------- | -------- | -------------------------------- |
| id **(PK)**      | BIGINT   | PRIMARY KEY , AUTO INCREMENT     |
| booked_at        | DATETIME | NOT NULL                         |
| seat_id **(FK)** | BIGINT   | NOT NULL , FOREIGN KEY , UINIQUE |
| user_id **(FK)** | BIGINT   | NOT NULL , FOREIGN KEY           |



## 프로젝트 명세

- JAVA 17
- SPRING BOOT 3.4.9
- Gradle - Groovy


## 핵심 의존성

- Spring Web
- Spring Data JPA
- Lombok
- H2 Database
- MySQL Driver
- Validation


