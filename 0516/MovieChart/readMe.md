![image](https://github.com/user-attachments/assets/b68fdd19-e33f-4609-86d0-8c4b2d3aa567)
![Uploading image.png…]()
# 🎬 MovieChart 앱 소개

## 📱 개요

**MovieChart**는 영화 정보를 데이터베이스에 저장하고 조회, 수정, 삭제할 수 있는 Android 애플리케이션입니다. 사용자는 다양한 영화 정보를 입력하고, 이를 기반으로 간단한 영화 차트를 구성할 수 있습니다.

---

## 🧩 주요 기능

### 1. 🎥 영화 정보 등록
- 사용자가 영화 정보를 입력하면 SQLite 데이터베이스에 저장됩니다.
- 저장되는 항목:
  - 제목 (`title`)
  - 개봉 연도 (`year`)
  - 장르 (`genre`)
  - 감독 (`director`)
  - 평점 (`rating`)

### 2. 📋 영화 리스트 조회
- 저장된 영화 데이터를 리스트 형태로 화면에 표시합니다.
- 데이터는 `getAllMovies()` 메서드를 통해 불러옵니다.

### 3. ✏️ 영화 정보 수정
- 등록된 영화의 정보를 선택하여 수정할 수 있습니다.
- 수정된 내용은 SQLite에 반영됩니다.

### 4. 🗑️ 영화 삭제
- 영화 리스트에서 특정 영화를 삭제할 수 있습니다.

---

## 🗂️ DB 구조 (SQLite)

- 테이블명: `movies`

| 필드 이름   | 타입      | 설명         |
|------------|-----------|--------------|
| `id`       | INTEGER   | 기본 키 (자동 증가) |
| `title`    | TEXT      | 영화 제목     |
| `year`     | INTEGER   | 개봉 연도     |
| `genre`    | TEXT      | 장르         |
| `director` | TEXT      | 감독         |
| `rating`   | REAL      | 평점         |

---

## 🏗️ 주요 클래스 설명

### 📌 `DBHelper.java`
- SQLite 데이터베이스를 생성 및 관리
- `addMovie()`, `getAllMovies()`, `updateMovie()`, `deleteMovie()` 메서드 포함

### 📌 `Movie.java`
- 영화 데이터를 담는 모델 클래스
- 영화의 속성들을 필드로 정의하고, getter/setter 제공

---

## ✅ 사용 예시

```java
Movie movie = new Movie();
movie.setTitle("Inception");
movie.setYear(2010);
movie.setGenre("Sci-Fi");
movie.setDirector("Christopher Nolan");
movie.setRating(8.8);

DBHelper dbHelper = new DBHelper(context);
dbHelper.addMovie(movie);
