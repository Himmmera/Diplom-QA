# # Дипломный проект по профессии «Тестировщик»

Дипломный проект представляет собой автоматизацию тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка

# Инструкция по запуску авто-тестов
## 1. Подготовка окружения
### 1.1 Установленное ПО
- Git
- IntelliJ IDEA
- JDK 11
- Docker Desktop ([инструкция](https://github.com/netology-code/aqa-homeworks/blob/master/docker/installation.md), обратить внимание на системные требования)
### 1.2 Запуск Docker Desktop
1. Открыть Docker Desktop
2. При первом использовании может понадобиться авторизация на Docker Hub
### 1.3 Инициализация контейнеров с СУБД MySQL, PostgreSQL и симулятором банковских сервисов
1. В IntelliJ IDEA открыть терминал (Alt+F12)
2. Выполнить команду: `docker-compose up`
3. Дождаться сборки контейнера
### 1.4. Запуск SUT с подключением к MySQL
1. В IntelliJ IDEA открыть дополнительную вкладку с терминалом кликом по кнопке +
2. Выполнить команду: `java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar`

### 1.5. Запуск SUT с подключением к PostgreSQL
1. В IntelliJ IDEA открыть дополнительную с терминалом кликом по кнопке +
2. Выполнить команду: `java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar`

## 2. Запуск автотестов
1. В IntelliJ IDEA дважды нажать Ctrl и в командной строке «Run Anything» выполнить одну из команд в зависимости от выбранной СУБД:
- **MySQL:** `./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"`
- **PostgreSQL:** `./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"`

## 3. Создание отчёта Allure
1. В IntelliJ IDEA  выполнить команду:
   `./gradlew allureServe`

После выполнения всех тестов остановить docker контейнер командой в консоли: `docker-compose down`.

