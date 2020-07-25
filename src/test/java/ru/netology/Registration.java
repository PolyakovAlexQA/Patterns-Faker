package ru.netology;

import lombok.Value;

public class Registration {

    @Value
    public static class RegistrationUser {
        private String fullName;
        private String phoneNumber;
        private String city;

    }
}
