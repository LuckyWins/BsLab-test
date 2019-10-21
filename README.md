# Тестовое задание BsLab (Single-Activity app)

Вопросы:
- как организовать возможность смены стиля отображения карт?

для этого можно ввести в адаптер Enum-класс для различного вида карточек
- как открывать детализацию определенной карты по получению PUSH-уведомления?

При создании нотификации можно передавать id карты. После нажатия на уведомление делать запрос на сервер (поиск информации о карточке по её id) и открывать CardFragment с аргументом card (полученным из запроса)
- как минимальными изменениями в коде загружать данные с другого сервера (формат тот же)

Для этой задачи можно хранить url в SharedPreferences и подставлять url в Retrofit с помощью Dagger
- как при этом организовать кеширование для загружаемых данных?

Для этого необходимо при создании экземпляра retrofit'а в OkHttpClient добавить Cache
```
val cacheSize = (5 x 1024 x 1024).toLong()
val myCache = Cache(context.cacheDir, cacheSize)
OkHttpClient.Builder()
  .cache(myCache)
```
Если говорить о кешировании картинок, то в Glide тоже существует возможность настроить кеширование

## Screenshots:

| [![ScreenShot](https://raw.githubusercontent.com/LuckyWins/BsLab-test/master/screenshots/HomeFragment.png)]  | [![ScreenShot](https://raw.githubusercontent.com/LuckyWins/BsLab-test/master/screenshots/CardFragment.png)] |
|:---:|:---:|
| HomeFragment | CardFragment |
