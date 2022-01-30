Запуск UI тестов выполняется командой: ./gradlew cucumber -Dbrowser='chrome'. В browser можно указать chrome, firefox.

Запуск API тестов выполняется командой:  ./gradlew test -DincludeTags='api'

Сгенерировать allure отчет:  ./gradlew allureServe