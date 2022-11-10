package com.demowebshop.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.demowebshop.config.TestConfig;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;


import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

import static com.demowebshop.test.TestData.*;

@Feature("Проверка сайта https://demowebshop.tricentis.com")
@Story("Проверка сайта")
@Owner("KirillCnv")
@Severity(SeverityLevel.CRITICAL)
@Link(value = "Testing", url = "https://demowebshop.tricentis.com")
@Tag("all")
@DisplayName("Проверка сайта https://demowebshop.tricentis.com (Jenkins)")

public class DemoWebShopApiTest extends TestConfig {
    @Test
    @DisplayName("Регистрация нового пользователя")
    void registrationTest() {
        step("Регистрация пользователя и установка cookie", () -> {
            String authCookieValue = given()
                    .log().all()
                    .contentType("application/x-www-form-urlencoded")
                    .cookie(requestToken, requestTokenValue)
                    .formParam(requestToken, requestTokenData)
                    .formParam("Gender", gender)
                    .formParam("FirstName", firstName)
                    .formParam("LastName", lastName)
                    .formParam("Email", email)
                    .formParam("Password", password)
                    .formParam("ConfirmPassword", password)
                    .when()
                    .post("/register")
                    .then()
                    .log().status()
                    .log().body()
                    .statusCode(302)
                    .extract()
                    .cookie(authCookieName);

            open("/Themes/DefaultClean/Content/images/logo.png");
            Cookie authCookie = new Cookie(authCookieName, authCookieValue);
            WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
        });

        step("проверка Email созданного пользователя на странице", () -> {
            open("");
            $(".account").shouldHave(Condition.text(email));
            open("");
            open("");
        });

    }

    @Test
    @DisplayName("Авторизация пользователя и редактирование профиля")
    void editUserWithAuthTest() {
        step("Регистрация пользователя и установка cookie", () -> {
            String authCookieValue = given()
                    .log().all()
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                    .formParam("Email", email)
                    .formParam("Password", password)
                    .when()
                    .post("/login")
                    .then()
                    .log().status()
                    .log().body()
                    .statusCode(302)
                    .extract()
                    .cookie(authCookieName);

            open("/Themes/DefaultClean/Content/images/logo.png");
            Cookie authCookie = new Cookie(authCookieName, authCookieValue);
            WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
        });

        step("Редактирование данных созданного пользователя", () -> {
            open("/customer/info");
            $("#FirstName").setValue(newFirstName);
            $("#LastName").setValue(newLastName);
            $(".save-customer-info-button").click();
        });

        step("Проверка успешного редактирования данных пользователя", () -> {
            $("#FirstName").shouldHave(value(newFirstName));
            $("#LastName").shouldHave(value(newLastName));
        });
    }
}
