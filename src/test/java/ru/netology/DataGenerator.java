package ru.netology;

import com.github.javafaker.Faker;
import lombok.val;

import java.util.Locale;

    public class DataGenerator {
        public DataGenerator() {
        }


        public static Registration.RegistrationUser generateByNamePhoneCity() {
            Faker faker = new Faker(new Locale("RU"));
            return new Registration.RegistrationUser(
                    faker.address().city(),
                    faker.name().lastName()+" "+faker.name().firstName(),
                    faker.phoneNumber().phoneNumber());

        }
    }



