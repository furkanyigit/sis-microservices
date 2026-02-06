# 1. Temel Görüntü: Java 17 yüklü hafif bir Linux sürümü indirir
FROM eclipse-temurin:17-jdk-alpine

# 2. Çalışma Dizini: Konteynerin içinde '/app' adında bir klasör oluşturur ve içine girer
WORKDIR /app

# 3. Kopyalama: Senin bilgisayarındaki 'target' klasöründeki .jar dosyasını alır,
# konteynerin içine 'app.jar' adıyla kopyalar.
# (Buradaki *.jar taktiği sayesinde dosya adın ne olursa olsun çalışır)
COPY target/*.jar app.jar

# 4. Çalıştırma: Konteyner başladığında bu komutu çalıştırır
ENTRYPOINT ["java", "-jar", "app.jar"]