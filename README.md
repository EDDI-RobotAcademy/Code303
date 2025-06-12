# Gradle 프로젝트 Import 가이드

## ✅ Eclipse에서 Gradle 프로젝트 Import 방법

1. **File** → **Import...** 선택  
2. **Gradle** → **Existing Gradle Project** 선택  
3. 프로젝트 디렉토리 선택 후 **Finish**  
4. Gradle 의존성 및 설정 자동 로딩 확인

---

## ✅ IntelliJ IDEA에서 Gradle 프로젝트 Import 방법

- 기존 Import 방식과 동일  
- `File` → `Open` 또는 `Import Project` 사용  
- `build.gradle` 또는 `settings.gradle` 선택 시 자동으로 Gradle 프로젝트로 인식됨

---

## ⚠️ 한글 깨짐 현상 해결법 (IntelliJ)

한글이 콘솔 또는 실행 결과에서 깨지는 경우, 아래 설정을 변경하세요:

1. `File` → `Settings` (또는 `Ctrl + Alt + S`)
2. `Build, Execution, Deployment` → `Build Tools` → `Gradle`
3. **Build and run using** 항목을 `Gradle` → `IntelliJ` 로 변경
4. 적용 후 프로젝트 리빌드 (`Build → Rebuild Project`)

---

## 🦾 기타 팁

- `gradle-wrapper.properties`가 없다면, CLI에서 `gradle wrapper` 명령어로 생성
- `.iml`, `.idea`, `build/` 등은 `.gitignore`에 포함하여 관리 권장
- Gradle 빌드는 가능한 최신 버전을 사용하도록 설정 (`distributionUrl` 확인)

---
