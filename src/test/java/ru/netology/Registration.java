package ru.netology;


import lombok.Value;

public class Registration {

    @Value
    public static class RegistrationUser {
        private String city;
        private String date;
        private String fullName;
        private String phoneNumber;

    }
}
