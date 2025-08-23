import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

plugins {
  java
  kotlin("jvm") version "1.9.24"
}

kotlin {
  jvmToolchain(17)
}

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(11))
  }
}

repositories {
  mavenCentral()
}

dependencies {
  testImplementation(platform("org.junit:junit-bom:5.10.3"))
  testImplementation("org.junit.jupiter:junit-jupiter")
  // 선택: assertk나 kotest를 쓰고 싶으면 아래 추가
  // testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.27.0")
}

tasks.test {
  useJUnitPlatform()
  // 테스트 출력 보기 좋게
  testLogging {
    events("PASSED", "FAILED", "SKIPPED")
  }
}



/**
 * 기존 레포 구조를 그대로 쓰되, Gradle이 소스 디렉터리로 인식하게 매핑
 * - 풀이 코드: 백준/Java, 백준/Kotlin, 프로그래머스/Java, 프로그래머스/Kotlin
 * - 테스트 코드: tests/java, tests/kotlin
 * - 테스트 리소스(입출력 파일): tests/resources
 */
sourceSets {
  named("main") {
    java.srcDirs("work/Java")
    resources.srcDirs() // 기본값 유지(원하면 비워둠)
  }
  named("test") {
    java.srcDirs("test/java")
    resources.srcDirs("test/resources")
  }
}

// ✅ Kotlin 소스 디렉토리는 Kotlin 확장을 통해 별도로 지정
extensions.configure<KotlinJvmProjectExtension> {
  sourceSets {
    named("main") {
      kotlin.srcDirs("work/Kotlin")
    }
    named("test") {
      kotlin.srcDirs("test/kotlin")
    }
  }
}

// ✅ Kotlin 바이트코드 타깃은 11로 (백준 Java 11 환경 호환)
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
  kotlinOptions {
    jvmTarget = "11"
  }
}
