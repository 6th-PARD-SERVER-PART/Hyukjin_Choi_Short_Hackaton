# 🚀 DormDry API Spec (v1.0 — Based on Swagger)

## 🔖 Base URL

```
http://localhost:8080
```

---

## 1. Member API

사용자가 처음 앱을 실행할 때 멤버 정보를 등록하는 API

### POST /api/member — Create Member

**Request**

```json
{
  "name": "최혁진",
  "phoneNumber": "01012341234"
}
```

**Response**

```
200 OK
```

---

## 2. Home API

유저의 "내 주문 목록"을 조회하는 홈 화면 API

### GET /api/home/{id} — Get My Orders

**Path Variable**

| Name | Type | Description |
| --- | --- | --- |
| id | Long | Member ID |

**Response**

```json
[
    {
        "orderId": 1,
        "memberName": "죽어랏",
        "phoneNumber": "01000000000",
        "placeName": "로뎀관",
        "pickupDate": "2026-11-25T10:00:00",
        "status": "PAID"
    },
    {
        "orderId": 2,
        "memberName": "죽어랏",
        "phoneNumber": "01000000000",
        "placeName": "벧엘관",
        "pickupDate": "2026-11-25T10:00:00",
        "status": "WAITING_PAYMENT"
    }
]
```

---

## 3. Schedule API

사용자가 날짜와 기숙사를 선택할 때 사용하는 API (주문 생성 전 선택 단계)

### GET /api/schedules — 모든 픽업 날짜 조회

**Response**

```json
[
    {
        "scheduleId": 1,
        "date": "2026년 11월 25일 (수)"
    },
    {
        "scheduleId": 2,
        "date": "2026년 12월 2일 (수)"
    },
    {
        "scheduleId": 3,
        "date": "2026년 12월 9일 (수)"
    },
    {
        "scheduleId": 4,
        "date": "2026년 12월 16일 (수)"
    },
    {
        "scheduleId": 5,
        "date": "2026년 12월 23일 (수)"
    },
    {
        "scheduleId": 6,
        "date": "2026년 12월 30일 (수)"
    },
    {
        "scheduleId": 7,
        "date": "2027년 1월 6일 (수)"
    },
    {
        "scheduleId": 8,
        "date": "2027년 1월 13일 (수)"
    },
    {
        "scheduleId": 9,
        "date": "2027년 1월 20일 (수)"
    },
    {
        "scheduleId": 10,
        "date": "2027년 1월 27일 (수)"
    },
    {
        "scheduleId": 11,
        "date": "2027년 2월 3일 (수)"
    },
    {
        "scheduleId": 12,
        "date": "2027년 2월 10일 (수)"
    }
]
```

### GET /api/schedules/{scheduleId}/places — 특정 날짜의 기숙사별 현재 주문 현황 조회

**Path Variable**

| Name | Type | Description |
| --- | --- | --- |
| scheduleId | Long | 픽업 스케줄 ID |

**Response**

```json
[
  {
    "schedulePlaceId": 101,
    "placeName": "Bethel Dorm",
    "currentOrders": 3,
    "maxOrders": 5
  },
  {
    "schedulePlaceId": 102,
    "placeName": "Logos Hall",
    "currentOrders": 1,
    "maxOrders": 5
  }
]
```

> 프론트는 여기서 schedulePlaceId 선택 → 주문 생성 단계로 이동

---

## 4. Order API

유저가 최종적으로 주문 생성하는 단계

### POST /api/orders — 주문 생성

**Request**

```json
{
  "memberId": 1,
  "pickupSchedulePlaceId": 4
}
```

**Response**

```json
{
    "orderId": 3,
    "stats": "WAITING_PAYMENT"
}
```

---

## 5. Admin API

세탁소 사장이 보는 웹용 페이지 API

### GET /api/admin/orders — 전체 주문 조회

**Response**

```json
[
    {
        "orderId": 1,
        "memberName": "죽어랏",
        "phoneNumber": "01000000000",
        "placeName": "로뎀관",
        "pickupDate": "2026-11-25T10:00:00",
        "status": "PAID"
    },
    {
        "orderId": 2,
        "memberName": "죽어랏",
        "phoneNumber": "01000000000",
        "placeName": "벧엘관",
        "pickupDate": "2026-11-25T10:00:00",
        "status": "PAID"
    },
    {
        "orderId": 3,
        "memberName": "죽어랏",
        "phoneNumber": "01000000000",
        "placeName": "창조관",
        "pickupDate": "2026-11-25T10:00:00",
        "status": "WAITING_PAYMENT"
    }
]
```

### PATCH /api/admin/orders/{orderId}/confirm — 입금 확인 처리

**Path Variable**

| Name | Type |
| --- | --- |
| orderId | Long |

**Response**

```
200 OK
```