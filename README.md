## 설정

### 사용 디펜던시

- LOMBOK
- H2
- JPA
- SPRING WEB

자바 버전 : 16

테스트 환경 : INTELLI J 

## 0. 테스트 데이터 추가

- API : /test
- METHOD : GET
- RETURN : VOID

테스트 데이터를 추가합니다. 

## A. 점포 추가

API : /addstore
METHOD : POST
RETURN : VOID

설명 : 이 부분은 과제에서 주는 JSON을 다르게 수정했습니다.
businesTimes의 day 항목을 버리고, date로 받아서 점포의 영업일을 하루하루 받게 했습니다. 이렇게 한 이유는 D 문제에서 3일치(앞으로 3일이내의  데이터)의 데이터를 계산하기 위해서는 요일을 데이터로 받아서는 계산할 수 없습니다.

사용 JSON

{
"name" :"태희수산",
"owner" : "태희",
"description" : "값싼 생선",
"level" : 0,
"address" : "구로구",
"phone" : "010-1234-1234",
"businessTimes" : [
{
"date": "2021-05-15",
"open" : "09:00",
"close" : "21:00"
}
]
}

## B. 점포 휴일 추가

API : /addholiday
METHOD : POST
RETURN : VOID

설명 : 과제와 똑같은 방식의 JSON을 사용합니다.

사용 JSON

{
"id" : 1,
"holidays" : [
"2021-05-11",
"2021-05-10"
]
}

## C. 점포 목록 조회

API : /simpleget
METHOD : GET
RETURN : LIST<SimpleStoreVo>

설명 : 조회한 날짜를 기준으로 모든 점포의 정보를 간단하게 출력합니다.

## D. 점포 상세 조회

API : /storeget?id=?
METHOD : GET
RETURN : LIST<StoreVo>

설명 : id와 조회한 날짜를 기준 앞으로 3일간의 점포의 영업일을 출력합니다.

## E. 점포 삭제

API : /delete?id=?
METHOD : DELETE
RETURN : VOID

설명 : id를 기준으로 점포 정보를 삭제 합니다.
