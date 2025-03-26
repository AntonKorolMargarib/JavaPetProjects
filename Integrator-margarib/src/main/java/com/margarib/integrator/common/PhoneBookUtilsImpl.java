package com.margarib.integrator.common;

import org.slf4j.Logger;
import static com.margarib.integrator.common.PhoneBookConstants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Реализация интерфейса PhoneBookUtils
 */
public class PhoneBookUtilsImpl implements PhoneBookUtils {

    /**
     * Заполняет телефонный справочник.
     * @param currentPhoneBook  Map для хранения текущих номеров.
     * @param logger Логгер для записи информации.
     */
    @Override
    public void initPhoneBook(Map<String, String> currentPhoneBook, Logger logger) {
        IntStream.range(0, PHONEBOOK_SIZE)
                        .forEach(i -> {
                            String name = "Name" + i;
                            String phoneNumber = generateRandomPhoneNumber();
                            currentPhoneBook.put(name, phoneNumber);
                        });
        logger.info("Phone book initialized with {} entries.", PHONEBOOK_SIZE);
    }

    /**
     * Генерирует случайный номер телефона.
     *
     * @return Случайный номер телефона в формате "XXX-XXX-XXXX".
     */
    @Override
    public String generateRandomPhoneNumber() {
        return String.format("+7-%03d-%03d-%04d",
                ThreadLocalRandom.current().nextInt(1000),
                ThreadLocalRandom.current().nextInt(1000),
                ThreadLocalRandom.current().nextInt(10000));
    }

    /**
     * Меняет случайное количество номеров в телефонном справочнике.
     * @param currentPhoneBook  Карта для хранения текущих номеров.
     * @param logger Логгер для записи информации.
     */
    @Override
    public void changeRandomNumbers(Map<String, String> currentPhoneBook, Logger logger) {
        int numberOfChanges = ThreadLocalRandom.current().nextInt(PHONEBOOK_SIZE / 1000) + 1; // Меняем до 0.1% номеров
        for (int i = 0; i < numberOfChanges; i++) {
            List<String> names = new ArrayList<>(currentPhoneBook.keySet());
            String name = names.get(ThreadLocalRandom.current().nextInt(names.size()));
            String newNumber = generateRandomPhoneNumber();
            currentPhoneBook.put(name, newNumber);
            logger.debug("Changed number for {} to {}.", name, newNumber);
        }
    }

    /**
     * Генерирует случайное число в заданном диапазоне.
     * @param min Минимальное значение.
     * @param max Максимальное значение.
     * @return Случайное число.
     */
    @Override
    public int generateRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
