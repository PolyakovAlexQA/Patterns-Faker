package ru.netology;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.DataGenerator.generateUser;
import static ru.netology.DataGenerator.getDate;


public class PlanningOrderDeliveryCard {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("AllureSelenide");
    }

    Registration.RegistrationUser user = generateUser();
    String otherDate = getDate(7);


    @Test
    void PlanningOrderDeliveryCardTest() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue(user.getCity());
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        $("[data-test-id=date] input").setValue(user.getDate());
        $("[data-test-id=name] input").setValue(user.getFullName());
        $("[data-test-id=phone] input").setValue(user.getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $$("[role=button]").find(exactText("Запланировать")).click();
        $("[data-test-id=success-notification]").waitUntil(visible, 15000);
        $("[data-test-id=success-notification] .notification__content").shouldHave(text("Встреча успешно запланирована на")).shouldHave(text(user.getDate()));
    }

    @Test
    void ReplayPlanningOrderDeliveryCardTest() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue(user.getCity());
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        $("[data-test-id=date] input").setValue(user.getDate());
        $("[data-test-id=name] input").setValue(user.getFullName());
        $("[data-test-id=phone] input").setValue(user.getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $$("[role=button]").find(exactText("Запланировать")).click();
        $("[data-test-id=success-notification]").waitUntil(visible, 15000);
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        $("[data-test-id=date] input").setValue(otherDate);
        $$("[role=button]").find(exactText("Запланировать")).click();
        $(withText("У вас уже запланирована встреча на другую дату. Перепланировать?")).waitUntil(visible, 15000);
        $$("div.notification__content > button").find(exactText("Перепланировать")).click();
        $("[data-test-id=success-notification]").waitUntil(visible, 15000);
        $("[data-test-id=success-notification] .notification__content").shouldHave(text("Встреча успешно запланирована на")).shouldHave(text(otherDate));
    }

    @Test
    void EmptyDateOrderDeliveryCardTest() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue(user.getCity());
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        $("[data-test-id=name] input").setValue(user.getFullName());
        $("[data-test-id=phone] input").setValue(user.getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $$("[role=button]").find(exactText("Запланировать")).click();
        $(".calendar-input[data-test-id=date]").shouldHave(exactText("Неверно введена дата"));

    }

    @Test
    void EmptyCityOrderDeliveryCardTest() {
        open("http://localhost:9999");
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        $("[data-test-id=name] input").setValue(user.getFullName());
        $("[data-test-id=date] input").setValue(user.getDate());
        $("[data-test-id=phone] input").setValue(user.getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $$("[role=button]").find(exactText("Запланировать")).click();
        $(".input_invalid[data-test-id=city]").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void EmptyNumberPhoneOrderDeliveryCardTest() {
        open("http://localhost:9999");
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        $("[data-test-id=date] input").setValue(user.getDate());
        $("[data-test-id=name] input").setValue(user.getFullName());
        $("[data-test-id=city] input").setValue(user.getCity());
        $("[data-test-id=agreement]").click();
        $$("[role=button]").find(exactText("Запланировать")).click();
        $(".input_invalid[data-test-id=phone]").shouldHave(exactText("Мобильный телефон Поле обязательно для заполнения"));
    }

    @Test
    void EmptyNameOrderDeliveryCardTest() {
        open("http://localhost:9999");
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        $("[data-test-id=date] input").setValue(user.getDate());
        $("[data-test-id=city] input").setValue(user.getCity());
        $("[data-test-id=phone] input").setValue(user.getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $$("[role=button]").find(exactText("Запланировать")).click();
        $(".input_invalid[data-test-id=name]").shouldHave(exactText("Фамилия и имя Поле обязательно для заполнения"));
    }

    @Test
    void ValidationCheckCityOrderDeliveryCardTest() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Khabarovsk");
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        $("[data-test-id=date] input").setValue(user.getDate());
        $("[data-test-id=name] input").setValue(user.getFullName());
        $("[data-test-id=phone] input").setValue(user.getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $$("[role=button]").find(exactText("Запланировать")).click();
        $(".input_invalid[data-test-id=city]").shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void ValidationCheckDateOrderDeliveryCardTest() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue(user.getCity());
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        $("[data-test-id=date] input").setValue("00.00.0000");
        $("[data-test-id=name] input").setValue(user.getFullName());
        $("[data-test-id=phone] input").setValue(user.getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $$("[role=button]").find(exactText("Запланировать")).click();
        $(".calendar-input[data-test-id=date]").shouldHave(exactText("Неверно введена дата"));
    }

    @Test
    void ValidationCheckNameOrderDeliveryCardTest() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=city] input").setValue(user.getCity());
        form.$("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        form.$("[data-test-id=date] input").setValue(user.getDate());
        form.$("[data-test-id=name] input").setValue("Gogy");
        form.$("[data-test-id=phone] input").setValue(user.getPhoneNumber());
        form.$("[data-test-id=agreement]").click();
        form.$$("[role=button]").find(exactText("Запланировать")).click();
        $(".input_invalid[data-test-id=name]").shouldHave(exactText("Фамилия и имя Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void EmptyCheckBoxOrderDeliveryCardTest() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue(user.getCity());
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        $("[data-test-id=date] input").setValue(user.getDate());
        $("[data-test-id=name] input").setValue(user.getFullName());
        $("[data-test-id=phone] input").setValue(user.getPhoneNumber());
        $$("[role=button]").find(exactText("Запланировать")).click();
        $(".input_invalid[data-test-id=agreement]").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

    @Test
    void CityAndDateComplexElementsOrderDeliveryCardTest() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Са");
        $$(".menu-item__control").find(exactText("Санкт-Петербург")).click();
        $("[data-test-id=date] input").setValue(user.getDate());
        $("[data-test-id=name] input").setValue(user.getFullName());
        $("[data-test-id=phone] input").setValue(user.getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $$("[role=button]").find(exactText("Запланировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
        $(withText("Встреча успешно запланирована на")).waitUntil(visible, 15000);

    }

}

