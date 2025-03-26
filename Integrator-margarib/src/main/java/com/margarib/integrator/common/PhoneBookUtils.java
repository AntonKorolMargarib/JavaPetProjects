package com.margarib.integrator.common;

import org.slf4j.Logger;

import java.util.Map;

/**
 * Пишем интерфейс для утилит работы с телефонным справочником
 */
public interface PhoneBookUtils {

    void initPhoneBook(Map<String, String> currentPhoneBook, Logger logger);
    String generateRandomPhoneNumber();
    void changeRandomNumbers(Map<String, String> currentPhoneBook, Logger logger);
    int generateRandomNumber(int min, int max);
}
