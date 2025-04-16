# Chapter 05: 고급 위젯과 이벤트 처리하기

## 1. 안드로이드에서 이벤트 처리
- 사용자 입력(터치, 버튼 클릭 등)에 반응하는 기능을 구현하기 위한 기본 개념
- `setOnClickListener` 등 리스너(Listener)를 통해 이벤트 처리 가능

## 2. 이벤트 처리하기
- **리스너 인터페이스 사용**
  - 예: `View.OnClickListener`
- **익명 클래스 또는 람다식으로 간단하게 처리**
- **이벤트 종류**: 클릭, 터치, 포커스, 키 입력 등

## 3. 머티리얼 디자인
- 구글이 제안한 현대적인 디자인 시스템
- **대표 위젯**
  - `FloatingActionButton`
  - `Snackbar`
  - `TextInputLayout`
- **디자인 구성 요소**
  - 색상, 그림자, 애니메이션 등을 포함한 직관적인 UI 구성

## 4. 컴파운드 버튼 (Compound Button)
- 버튼에 체크 기능이 추가된 형태
- **대표 위젯**: `CheckBox`, `RadioButton`, `Switch`, `ToggleButton`
- **공통 속성**
  - `android:checked`
  - `setOnCheckedChangeListener`

## 5. 체크 박스 (CheckBox)
- 여러 항목 중 다중 선택 가능
- 예시:
  ```kotlin
  checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
      // 체크 여부 처리
  }
