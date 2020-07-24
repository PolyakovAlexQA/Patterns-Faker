package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static ru.netology.DataGenerator.generateByNamePhone;

public class PlanningOrderDeliveryCard {

        Registration.RegistrationUser generateByNamePhone = generateByNamePhone("RU");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(LocalDateTime.now().plusDays(7));




        @Test
        void OrderDeliveryCardTest() {
            open("http://localhost:9999");
            SelenideElement form = $(".form");
            form.$("[data-test-id=city] input").setValue("Хабаровск");
            form.$("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.DELETE);
            form.$("[data-test-id=date] input").setValue(date);
            form.$("[data-test-id=name] input").setValue(generateByNamePhone.getFullName());
            form.$("[data-test-id=phone] input").setValue(generateByNamePhone.getPhoneNumber());
            form.$("[data-test-id=agreement]").click();
            form.$$("[role=button]").find(exactText("Запланировать")).click();
            $(withText("Встреча успешно запланирована на")).waitUntil(visible, 15000);

        }


        @Test
        void EmptyDateOrderDeliveryCardTest() {
            open("http://localhost:9999");
            SelenideElement form = $(".form");
            form.$("[data-test-id=city] input").setValue("Владивосток");
            form.$("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.DELETE);
            form.$("[data-test-id=phone] input").setValue("+79779778010");
            form.$("[data-test-id=name] input").setValue("Поляков Александр");
            form.$("[data-test-id=agreement]").click();
            form.$$("[role=button]").find(exactText("Забронировать")).click();
            $(".calendar-input[data-test-id=date]").shouldHave(exactText("Неверно введена дата"));
        }

        @Test
        void EmptyCityOrderDeliveryCardTest() {
            open("http://localhost:9999");
            SelenideElement form = $(".form");
            form.$("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.DELETE);
            form.$("[data-test-id=date] input").setValue(date);
            form.$("[data-test-id=name] input").setValue("Поляков Александр");
            form.$("[data-test-id=phone] input").setValue("+79779778010");
            form.$("[data-test-id=agreement]").click();
            form.$$("[role=button]").find(exactText("Запланировать")).click();
            $(".input_invalid[data-test-id=city]").shouldHave(exactText("Поле обязательно для заполнения"));
        }

        @Test
        void EmptyNumberPhoneOrderDeliveryCardTest() {
            open("http://localhost:9999");
            SelenideElement form = $(".form");
            form.$("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.DELETE);
            form.$("[data-test-id=date] input").setValue(date);
            form.$("[data-test-id=name] input").setValue("Поляков Александр");
            form.$("[data-test-id=city] input").setValue("Хабаровск");
            form.$("[data-test-id=agreement]").click();
            form.$$("[role=button]").find(exactText("Забронировать")).click();
            $(".input_invalid[data-test-id=phone]").shouldHave(exactText("Мобильный телефон Поле обязательно для заполнения"));
        }

        @Test
        void EmptyNameOrderDeliveryCardTest() {
            open("http://localhost:9999");
            SelenideElement form = $(".form");
            form.$("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.DELETE);
            form.$("[data-test-id=date] input").setValue(date);
            form.$("[data-test-id=city] input").setValue("Хабаровск");
            form.$("[data-test-id=phone] input").setValue("+79779778010");
            form.$("[data-test-id=agreement]").click();
            form.$$("[role=button]").find(exactText("Забронировать")).click();
            $(".input_invalid[data-test-id=name]").shouldHave(exactText("Фамилия и имя Поле обязательно для заполнения"));
        }

        @Test
        void ValidationCheckCityOrderDeliveryCardTest() {
            open("http://localhost:9999");
            SelenideElement form = $(".form");
            form.$("[data-test-id=city] input").setValue("Khabarovsk");
            form.$("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.DELETE);
            form.$("[data-test-id=date] input").setValue(date);
            form.$("[data-test-id=name] input").setValue("Ким Ю Чин");
            form.$("[data-test-id=phone] input").setValue("+79779778010");
            form.$("[data-test-id=agreement]").click();
            form.$$("[role=button]").find(exactText("Забронировать")).click();
            $(".input_invalid[data-test-id=city]").shouldHave(exactText("Доставка в выбранный город недоступна"));
        }

        @Test
        void ValidationCheckDateOrderDeliveryCardTest() {
            open("http://localhost:9999");
            SelenideElement form = $(".form");
            form.$("[data-test-id=city] input").setValue("Москва");
            form.$("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.DELETE);
            form.$("[data-test-id=date] input").setValue("00.00.0000");
            form.$("[data-test-id=name] input").setValue("Иосиф Сталин");
            form.$("[data-test-id=phone] input").setValue("+79779778010");
            form.$("[data-test-id=agreement]").click();
            form.$$("[role=button]").find(exactText("Забронировать")).click();
            $(".calendar-input[data-test-id=date]").shouldHave(exactText("Неверно введена дата"));

        }

        @Test
        void ValidationCheckNameOrderDeliveryCardTest() {
            open("http://localhost:9999");
            SelenideElement form = $(".form");
            form.$("[data-test-id=city] input").setValue("Владивосток");
            form.$("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.DELETE);
            form.$("[data-test-id=date] input").setValue(date);
            form.$("[data-test-id=name] input").setValue("Gogy");
            form.$("[data-test-id=phone] input").setValue("+79779778010");
            form.$("[data-test-id=agreement]").click();
            form.$$("[role=button]").find(exactText("Забронировать")).click();
            $(".input_invalid[data-test-id=name]").shouldHave(exactText("Фамилия и имя Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

        }

        @Test
        void ValidationCheckNumberOrderDeliveryCardTest() {
            open("http://localhost:9999");
            SelenideElement form = $(".form");
            form.$("[data-test-id=city] input").setValue("Москва");
            form.$("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.DELETE);
            form.$("[data-test-id=date] input").setValue(date);
            form.$("[data-test-id=name] input").setValue("Жак-Ив Кусто");
            form.$("[data-test-id=phone] input").setValue("898898989+");
            form.$("[data-test-id=agreement]").click();
            form.$$("[role=button]").find(exactText("Забронировать")).click();
            $(".input_invalid[data-test-id=phone]").shouldHave(exactText("Мобильный телефон Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

        }

        @Test
        void EmptyCheckBoxOrderDeliveryCardTest() {
            open("http://localhost:9999");
            SelenideElement form = $(".form");
            form.$("[data-test-id=city] input").setValue("Москва");
            form.$("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.DELETE);
            form.$("[data-test-id=date] input").setValue(date);
            form.$("[data-test-id=name] input").setValue("Жак-Ив Кусто");
            form.$("[data-test-id=phone] input").setValue("+79779778010");
            form.$$("[role=button]").find(exactText("Забронировать")).click();
            $(".input_invalid[data-test-id=agreement]").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
        }

        @Test
        void ICityAndDateСomplexElementsOrderDeliveryCardTest() {
            open("http://localhost:9999");
            SelenideElement form = $(".form");
            form.$("[data-test-id=city] input").setValue("Са");
            $$(".menu-item__control").find(exactText("Санкт-Петербург")).click();
            form.$("[data-test-id=date] input").setValue(date);
            form.$("[data-test-id=name] input").setValue("Поляков Александр");
            form.$("[data-test-id=phone] input").setValue("+79779778010");
            form.$("[data-test-id=agreement]").click();
            form.$$("[role=button]").find(exactText("Забронировать")).click();
            $(withText("Встреча успешно забронирована на")).waitUntil(visible, 15000);

        }

    }


