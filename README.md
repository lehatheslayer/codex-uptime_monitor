# Uptime Monitor

Сервис для отслеживания доступности сетевых ресурсов.

## Совместивость

Проверьте версию java:

```bash
java --version
```

Если у вас Java выше 11-ой версии, то все ок

## Установка для запуска через maven
1. Установите [Apache Maven](https://maven.apache.org/download.cgi)

2. Установите [MySQL](https://www.mysql.com/downloads/) и создайте базу данных.

3. В файле [application.properties](src/main/resources/application.properties) 
   вставьте свои значения в параметрах базы данных, поменяйте вебхук телеграм бота для оповещения:

```properties
webhook = *YOUR_WEBHOOK*
spring.datasource.url=jdbc:mysql://${*DB_HOST_NAME*}:3306/*DATABASE_NAME*
spring.datasource.username=*DB_USERNAME*
spring.datasource.password=*DB_PASSWORD*
```

5. В файле [Addresses.xml](Addresses.xml) вписать адреса, котоыре нужно отслеживать

## Запуск через maven

```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--status=running"
```

## Запуск через Docker

```bash
docker-compose up
```

Параметры окружения находятся в файле [.env](.env). Нужно вставить данные базы данных и вебхук телеграм бота



