📁 app
├── 📁 manifests
│   └── 📄 AndroidManifest.xml
│
├── 📁 java
│   └── 📁 com.example.japanesememo
│       ├── 📄 AddWordActivity             ← 새 단어 추가 화면
│       ├── 📄 DatabaseHelper              ← SQLite DB 관리
│       ├── 📄 JishoApiService             ← 사전 API 통신
│       ├── 📄 JishoWord                   ← 사전 결과 데이터 모델
│       ├── 📄 LocalSearchAdapter          ← 로컬 단어 리스트 어댑터
│       ├── 📄 MainActivity                ← 메인 화면
│       ├── 📄 SearchResultAdapter         ← 검색 결과 리스트 어댑터
│       ├── 📄 StudyActivity               ← 학습 모드 화면
│       ├── 📄 TranslationService          ← 번역 서비스 클래스
│       ├── 📄 Word                        ← 단어 모델 클래스
│       └── 📄 WordAdapter                 ← 저장된 단어 리스트 어댑터
│
├── 📁 res
│   ├── 📁 drawable
│   │   ├── ic_launcher_background.xml
│   │   ├── ic_launcher_foreground.xml
│   │   ├── rounded_background.xml              ← 둥근 배경 (기본)
│   │   ├── rounded_background_light.xml        ← 밝은 배경
│   │   └── rounded_button_background.xml       ← 버튼용 둥근 배경
│   │
│   ├── 📁 layout
│   │   ├── activity_add_word.xml               ← AddWordActivity UI
│   │   ├── activity_main.xml                   ← MainActivity UI
│   │   ├── activity_study.xml                  ← StudyActivity UI
│   │   ├── item_local_search_result.xml        ← 로컬 검색 항목 뷰
│   │   ├── item_search_result.xml              ← API 검색 결과 항목 뷰
│   │   └── item_word.xml                       ← 저장된 단어 항목 뷰
│   │
│   ├── 📁 mipmap                               ← 앱 아이콘
│   ├── 📁 values
│   │   ├── colors.xml                          ← 앱 테마 색상 정의
│   │   └── strings.xml                         ← 문자열 리소스
│   │
│   └── 📁 themes
│       └── themes.xml 등                      ← 다크/라이트 테마 설정


