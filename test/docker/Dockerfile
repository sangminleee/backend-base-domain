# 베이스 이미지로 OpenJDK를 사용합니다.
FROM openjdk:17-jdk-slim

# 빌드된 JAR 파일을 이미지에 복사합니다.
COPY test.jar /app/my-app.jar

# 작업 디렉터리를 설정합니다.
WORKDIR /app

# 컨테이너가 시작될 때 실행할 명령을 설정합니다.
ENTRYPOINT ["java", "-jar", "my-app.jar"]