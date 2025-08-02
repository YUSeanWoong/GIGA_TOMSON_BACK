# 1. Java 17이 설치된 경량 Alpine 리눅스 기반 이미지
FROM openjdk:17-jdk-alpine

# 2. .jar 파일 복사
COPY build/libs/GIGA_TOMSON-1.0.0.jar app.jar

# 3. 실행 명령
ENTRYPOINT ["java", "-jar", "/app.jar"]