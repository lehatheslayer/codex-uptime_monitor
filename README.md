# Uptime Monitor

Сервис для отслеживания доступности сетевых ресурсов.

## Совместивость

Проверьте версию java:

```bash
java --version
```

Если у вас Java выше 11-ой версии, то все ок

## Установка
1. Установите [Apache Maven](https://maven.apache.org/download.cgi)

2. Установите [MySQL](https://www.mysql.com/downloads/) и создайте базу данных.

3. В файле [application.properties](src/main/resources/application.properties) вставьте свои значения в следуюших строках:

```properties
spring.datasource.url=jdbc:mysql://${*HOST_NAME*}:3306/*DATABASE_NAME*
spring.datasource.username=*USERNAME*
spring.datasource.password=*PASSWORD*
```

4. В файле [Addresses.xml](Addresses.xml) вписать адреса, котоыре нужно отслеживать и вебхук телеграм бота для оповещения

## Запуск

```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--status=running"
```

## Запуск через Docker

```bash
docker-compose up
```

Параметры окружения находятся в файле [.env](.env)



