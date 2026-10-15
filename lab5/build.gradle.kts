plugins {
    java
    war
    id("org.springframework.boot") version "4.1.1"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "io.rekri"
version = "0.0.1-SNAPSHOT"
description = "lab5"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation ("org.apache.tomcat.embed:tomcat-embed-jasper")
    implementation ("jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api:3.0.2")
    implementation ("org.glassfish.web:jakarta.servlet.jsp.jstl:3.0.1")
    implementation ("org.springframework.security:spring-security-taglibs")
    implementation("org.bouncycastle:bcprov-jdk18on:1.86")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat-runtime")
    testImplementation("org.springframework.boot:spring-boot-starter-security-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testCompileOnly("org.projectlombok:lombok")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testAnnotationProcessor("org.projectlombok:lombok")
    runtimeOnly ("org.xerial:sqlite-jdbc:3.53.4.0")
    runtimeOnly ("org.hibernate.orm:hibernate-community-dialects")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
