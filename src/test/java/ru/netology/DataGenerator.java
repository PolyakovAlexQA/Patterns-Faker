package ru.netology;

import com.github.javafaker.Faker;
import lombok.val;

import java.util.Locale;

    public class DataGenerator {
        public DataGenerator() {
        }

        public static class Registration {
            private Registration() {
            }

        }

        public static ru.netology.Registration.RegistrationUser generateByNamePhone(String locale) {
            Faker faker = new Faker(new Locale("RU"));
            return new ru.netology.Registration.RegistrationUser(
                    faker.name().fullName(),
                    faker.phoneNumber().phoneNumber());

        }
    }



