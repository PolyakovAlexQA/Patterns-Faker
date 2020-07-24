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
        void PlanningOrderDeliveryCardTest() {
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
    void ReplayPlanningOrderDeliveryCardTest() {
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
        form.$("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        form.$("[data-test-id=date] input").setValue(date);
        form.$$("[role=button]").find(exactText("Запланировать")).click();
        $(withText("У вас уже запланирована встреча на другую дату. Перепланировать?")).waitUntil(visible, 15000);
        $$("div.notification__content > button").find(exactText("Перепланировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
        $(withText("Встреча успешно запланирована на")).waitUntil(visible, 15000);

                }

    }


