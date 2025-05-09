![image](https://github.com/user-attachments/assets/c3aeaf02-6d7d-4a97-89bf-f58e2b9dac80)
# 📋 Android ToDo List App

본 프로젝트는 간단한 할 일 목록(ToDo) 관리 안드로이드 애플리케이션입니다.

---

## 🔍 SWOT 분석

### ✅ 강점 (Strengths)
- 구조가 명확하며 유지보수 용이
- 비동기 처리로 사용자 경험 유지
- Gson 기반 데이터 저장
- RecyclerView 기반 깔끔한 UI

### ❌ 약점 (Weaknesses)
- DB 미사용 (SharedPreferences만 사용)
- 날짜 선택 기능 미구현
- 에러 처리 부족
- 다국어 미지원

### 📈 기회 (Opportunities)
- Room DB 연동 가능성
- 알림 기능 추가 가능
- Jetpack Compose로 UI 현대화 가능
- 클라우드 데이터 동기화 가능

### ⚠ 위협 (Threats)
- 시장 경쟁 앱 다수 존재
- 보안 기능 미흡
- 사용자 기대치 상승
- 기술 트렌드 변화 (예: Compose)

---

## 📂 주요 구성 요소

| 파일명 | 설명 |
|--------|------|
| `MainActivity.java` | 메인 UI 및 로직 제어 |
| `Task.java` | 할 일(Task) 데이터 모델 |
| `TaskAdapter.java` | RecyclerView 어댑터 |
| `activity_main.xml` | 메인 UI 레이아웃 |
| `task_item.xml` | 개별 할 일 아이템 UI |
| `main_menu.xml` | 앱 메뉴 설정 |

---

## 🚀 향후 개선 방향

- Room DB 도입
- DueDate를 위한 DatePicker 추가
- 다국어 리소스 분리 (`strings.xml`)
- Notification 및 알림 기능 추가
- Jetpack Compose 마이그레이션 검토

