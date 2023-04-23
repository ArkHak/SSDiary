# SSDiary

Приложение SimbirSoftDiary для записи дел.

v0.2.1

<img src="https://user-images.githubusercontent.com/81629278/233840819-60e50feb-cc8e-474a-b916-7052c04971eb.png" width="320"> <img src="https://user-images.githubusercontent.com/81629278/233840823-8463219a-4cc4-4bb2-9659-a102fdcc3618.png" width="320">

## <b>Возможности: </b>
1) При запуске приложения - отображается список дел на текущую дату.
2) Поменять вид отображения списка задач (полный - таблицей и часами дня, простой - список из задач на выбранный день).
<img width="240" alt="3" src="https://user-images.githubusercontent.com/81629278/233843418-9f0078d0-4a9c-424b-892d-91538dfde78c.png">

3) Посмотреть детальную информацию по задаче. (TAP по любой задаче)
<img width="240" alt="4" src="https://user-images.githubusercontent.com/81629278/233844337-069a69a1-6e79-4d17-997d-12e1356deca1.png">

4) Создать задачу с корректировкой времени и даты.
<img width="240" alt="5" src="https://user-images.githubusercontent.com/81629278/233844749-8f53c599-c64a-45b8-9488-cb9ca94f761c.png">

<img src="https://user-images.githubusercontent.com/81629278/233845195-aeb66bb7-f1bf-48ba-bc4a-5fd3cc55b43b.png" width="240"> <img src="https://user-images.githubusercontent.com/81629278/233844824-52f2bc62-75a3-4ee3-b871-78629d843c05.png" width="240">

<img src="https://user-images.githubusercontent.com/81629278/233845093-7d195c27-f468-4e6e-88cb-514456046dd5.png" width="240"> <img src="https://user-images.githubusercontent.com/81629278/233845108-69f3c4dd-4613-425c-80b2-4a8f7b9e5361.png" width="240">

## <b>Особенности: </b>
- при создании БД начальные данные подгружаются из файла assets/init_data.json;
- можно скроллом навигироваться по календарю;
- сохранение настроек отображения списка задач;
- minSdk 26 (Android 8+);
- ориентация - portrait;
- Unit тесты;
- SimbirSoftTheme;

## <b>Стек: </b>
- Clean Architecture;
- Single Activity;
- MVI + MVVM;
- Jetpack Compose;
- DI - Koin;
- Android Navigation Compose;
- Room;
- Compose Calendar Api (io.github.boguszpawlowski);
- GSON;
- Compose Data/Time Picker (io.github.vanpra);
