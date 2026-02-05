FROM eclipse-temurin:17-jdk

WORKDIR /app

# プロジェクト全体をコピー
COPY . .

# mvnw に実行権限を付ける
RUN chmod +x mvnw

# ビルド
RUN ./mvnw clean package -DskipTests

# 起動
CMD ["java", "-jar", "target/hrms-0.0.1-SNAPSHOT.jar"]

