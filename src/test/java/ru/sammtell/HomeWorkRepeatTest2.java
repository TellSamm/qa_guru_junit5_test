package ru.sammtell;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HomeWorkRepeatTest2 {

@DisplayName("Последний запуск!")
 @Test
 @Order(3)
    void simpleTest(){

     Selenide.open("https://mail.google.com/");
 }

@DisplayName("Внутри ордеров а заначит второй запуск!")
 @Test
 @Order(2)
    void vtoroyTest(){

     Selenide.open("https://www.youtube.com/");
 }
@DisplayName("Певрвый запуск ракеты!")
 @Test
 @Order(1)
    void tretiyTest(){

     Selenide.open("https://kazan.hh.ru/");
 }




}
