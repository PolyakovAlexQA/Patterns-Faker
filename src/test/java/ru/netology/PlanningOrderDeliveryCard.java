package ru.netology;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.DataGenerator.generateUser;


public class PlanningOrderDeliveryCard {

    Registration.RegistrationUser user = generateUser();




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

}