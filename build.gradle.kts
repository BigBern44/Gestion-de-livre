plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.5.3"
	id("io.spring.dependency-management") version "1.1.7"
	id("jacoco")
	id("info.solidsoft.pitest") version "1.19.0-rc.1"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// Spring
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	// Kotlin
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// JPA + H2 (base de test en mémoire)
	runtimeOnly("com.h2database:h2")
	implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")

	// (Optionnel) Validation
	implementation("org.springframework.boot:spring-boot-starter-validation")

	// Tests
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
	testImplementation("io.kotest:kotest-assertions-core:5.9.1")

	testImplementation("org.pitest:pitest-junit5-plugin:1.2.3")

}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

jacoco {
	toolVersion = "0.8.11"
}

tasks.jacocoTestReport {
	dependsOn(tasks.test)
	reports {
		xml.required.set(true)
		html.required.set(true)
		csv.required.set(false)
	}
}
pitest {
	targetClasses.set(listOf("com.example.demo.domain.*")) // packages à tester
	targetTests.set(listOf("com.example.demo.*"))

	pitestVersion.set("1.19.6") // version de PIT
	threads.set(2)
	outputFormats.set(listOf("HTML"))
	timestampedReports.set(false)
}

