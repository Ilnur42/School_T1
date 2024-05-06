Задание: Реализация системы учета времени выполнения методов

Описание:
Необходимо разработать систему учета времени выполнения методов в приложении с использованием Spring AOP. Система должна быть способна асинхронно логировать и анализировать данные о времени выполнения методов.

Требования:
Создать аннотации @TrackTime и @TrackAsyncTime, которые можно применять к методам для отслеживания времени их выполнения.
Реализовать аспекты, используя Spring AOP, для асинхронного и синхронного отслеживания времени выполнения методов, помеченных соответствующими аннотациями.
Создать сервис, который будет асинхронно сохранять данные о времени выполнения методов в базе данных.
Реализовать REST API для получения статистики по времени выполнения методов (например, среднее время выполнения, общее время выполнения) для различных методов и их групп.
Настроить приложение с помощью конфигурации Spring для включения использования AOP и асинхронной обработки данных.

Описание решения.
1. Приложение представляет собой систему для работы с сущностями автор, книга и книжный магазин. Сущность книга является более тяжеловесной, т.к содержит в себе две другие сущности (для сравнения времени работы CRUD с разными по весу объектами)
	1.1 В качестве синхронных методов, работу которых будет засекать аспект - выступают методы работы с базой данных (добавление, поиск, обновление и тд. указанных сущностей).
	1.2 В качестве асинхронных методов, работу которых будет засекать отдельный аспект - выступают джобы (автоудаления старой информации из бд, и запись в лог информации о количестве сущностей в бд). Вызываются шедулером по крону.
2. База данных - Postgresql. Добавлен Liquibase для автоматической генерации нужных таблиц. 
3. Для возможности быстро начать работать с сущностями - добавлены классы рандомной генерации этих сущностей. При необходимости создания сущностей через запрос - в resources.request_examples.entity приведены примеры всех сущностей в json формате.
4. Для более простой работы с запросами добавлен swagger (без примеров успешных запросов). 
5. Т.к приложение представляет собой решение небольшой задачи, покрытие тестами - минимальное, без поднятия в них базы данных. Так же сервисы играют роль фасадов, т.к бизнес логики в приложении нет.