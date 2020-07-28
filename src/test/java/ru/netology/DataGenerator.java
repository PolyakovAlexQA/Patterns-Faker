package ru.netology;
import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;



public class DataGenerator {
    public DataGenerator() {
    }

    private static String getCity(String Locale) {

        String[] cityList = new String[]{
                "Абакан", "Анадырь", "Архангельск", "Астрахань", "Барнаул", "Белгород",
                "Биробиджан", "Благовещенск", "Брянск", "Великий Новгород", "Владивосток",
                "Владикавказ", "Владимир", "Волгоград", "Вологда", "Воронеж", "Горно-Алтайск",
                "Грозный", "Екатеринбург", "Иваново", "Ижевск", "Иркутск", "Йошкар-Ола",
                "Казань", "Калининград", "Калуга", "Кемерово", "Киров", "Кострома", "Краснодар",
                "Красноярск", "Курган", "Курск", "Кызыл", "Липецк", "Магадан", "Магас", "Майкоп",
                "Махачкала", "Москва", "Мурманск", "Нальчик", "Нарьян-Мар", "Нижний Новгород",
                "Новосибирск", "Омск", "Орёл", "Оренбург", "Пенза", "Пермь", "Петрозаводск",
                "Петропавловск-Камчатский", "Псков", "Ростов-на-Дону", "Рязань", "Салехард",
                "Самара", "Санкт-Петербург", "Саранск", "Саратов", "Севастополь", "Симферополь",
                "Смоленск", "Ставрополь", "Сыктывкар", "Тамбов", "Тверь", "Томск", "Тула", "Тюмень",
                "Улан-Удэ", "Ульяновск", "Уфа", "Хабаровск", "Ханты-Мансийск", "Чебоксары",
                "Челябинск", "Черкесск", "Чита", "Элиста", "Южно-Сахалинск", "Якутск", "Ярославль"};


    public static Registration.RegistrationUser generateByNamePhoneCity() {
        Faker faker = new Faker(new Locale("RU"));
        return new ru.netology.Registration.RegistrationUser(
                faker.name().lastName() + " " + faker.name().firstName(),
                faker.phoneNumber().phoneNumber());

    }
    public static String LocalDate(int daysToAdd) {
        LocalDate endDate = LocalDate.now().plusDays(daysToAdd);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(LocalDateTime.now().plusDays(7));
        return endDate.format(formatter);
    }
}



