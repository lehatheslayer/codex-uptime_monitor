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

4. В файле [Notifier.java](src/main/java/so/codex/monitor/service/monitor/Notifier.java) вставить свой вебхук 
[CodeX Bot](https://github.com/codex-bot/notify) для отправки уведомлений в чат
   
```
private static final String WEB_HOOK = *WEB_HOOK*
```

5. В файле [Addresses.xml](Addresses.xml) вписать адреса, котоыре нужно отслеживать

## Запуск

```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--status=running"
```

## Результаты

На данный момент результаты работы можно посмотреть только в базе данных, куда записываются результаты всех пингов

