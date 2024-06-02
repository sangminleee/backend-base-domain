plugins {
    kotlin("jvm") version "1.9.23"
}

group = "com.test"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "TestKt"
    }

    from(configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { if (it.isDirectory) it else zipTree(it) })
}

// 아래 부분을 수정하여 덮어쓰기 설정을 명시합니다.
tasks.register<Copy>("copyJarToDocker") {
    dependsOn(tasks.jar)
    from(tasks.jar.get().archiveFile)
    into("docker")  // Docker 디렉터리 경로
}

// build 후에 자동으로 jar 파일을 복사하도록 설정
tasks.build {
    finalizedBy(tasks.named("copyJarToDocker"))
}