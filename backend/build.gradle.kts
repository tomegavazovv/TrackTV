import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.0"
	id("io.spring.dependency-management") version "1.1.0"
	id("org.jetbrains.kotlin.plugin.jpa") version "1.8.22"
	id("org.flywaydb.flyway") version "9.20.1"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
}

group = "com.sorsix"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security:3.1.1")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
	runtimeOnly("org.postgresql:postgresql:42.6.0")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.postgresql:postgresql:42.6.0")
	implementation("org.flywaydb:flyway-core")
	implementation("org.springframework.boot:spring-boot-starter-validation:3.1.1")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation ("org.junit.jupiter:junit-jupiter-api:5.7.0")
	testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.7.0")
	testImplementation ("org.mockito:mockito-core:3.12.4")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

flyway {
	url = "jdbc:postgresql://localhost:5432/tracktv" // Replace with your database URL
	user = "postgres" // Replace with your database username
	password = "admin" // Replace with your database password
	locations = arrayOf("classpath:db/migration") // Location of your migration scripts
	baselineOnMigrate = true // Automatically create and execute the baseline migration on an empty schema
	validateOnMigrate = false // Set to true to validate applied migrations against resolved ones
}
