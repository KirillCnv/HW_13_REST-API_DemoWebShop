package com.demowebshop.test;

import com.github.javafaker.Faker;

public class TestData {

    static Faker faker = new Faker();
    static String authCookieName = "NOPCOMMERCE.AUTH";
    static String requestToken = "__RequestVerificationToken";
    static String requestTokenValue = "3KrmNU7d9Xjdz9O9FAfy02ViUtwsCdJjAGuFpMJtWzjrV1PR0t57VKWnruY2LPis7QMF3vfnP75KfMw1W3LfHvwU9V-JkqoDzPrT0M1Z7_o1";

    static String requestTokenData = "54f7E37uGbx9Qx6eKY121jlxt_RamAUveJOMVjP1zsz8i9Tz3ncokahDmizLSLJs0mZKlsNuC3zd1DLycUMxY7Y6x5Tc1q4KXDZy8wX8u_w1";
    static String firstName = faker.name().firstName();
    static String lastName = faker.name().lastName();
    static String gender = "M";
    static String email = faker.internet().emailAddress();
    static String password = faker.internet().password();
    static String newFirstName = faker.name().firstName();
    static String newLastName = faker.name().lastName();

}
