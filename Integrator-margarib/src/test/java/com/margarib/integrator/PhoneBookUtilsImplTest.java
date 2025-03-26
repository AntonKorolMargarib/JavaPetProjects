package com.margarib.integrator;

import com.margarib.integrator.common.PhoneBookUtils;
import com.margarib.integrator.common.PhoneBookUtilsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static com.margarib.integrator.common.PhoneBookConstants.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class PhoneBookUtilsImplTest {

    private PhoneBookUtilsImpl phoneBookUtils;
    private Map<String, String> currentPhoneBook;
    private Logger logger;

    @BeforeEach
    void setUp() {
        phoneBookUtils = new PhoneBookUtilsImpl();
        currentPhoneBook = new ConcurrentHashMap<>();
        logger = Mockito.mock(Logger.class);
    }

    @Test
    void initPhoneBook_shouldInitializePhoneBookWithCorrectSize() {
        // Act
        phoneBookUtils.initPhoneBook(currentPhoneBook, logger);

        // Assert
        assertThat(currentPhoneBook).hasSize(PHONEBOOK_SIZE);
        IntStream.range(0, PHONEBOOK_SIZE).forEach(i -> {
            String name = "Name" + i;
            assertThat(currentPhoneBook).containsKey(name);
            assertThat(currentPhoneBook.get(name)).isNotNull();
        });
        verify(logger).info("Phone book initialized with {} entries.", PHONEBOOK_SIZE);
    }

    @Test
    void generateRandomPhoneNumber_shouldGenerateValidPhoneNumber() {
        String phoneNumber = phoneBookUtils.generateRandomPhoneNumber();

        assertThat(phoneNumber).isNotNull();
        assertThat(phoneNumber).matches("\\+7-\\d{3}-\\d{3}-\\d{4}");
    }

    @Test
    void changeRandomNumbers_shouldChangeRandomNumbersInPhoneBook() {
        currentPhoneBook.put("Name1", "Number1");
        currentPhoneBook.put("Name2", "Number2");
        int initialSize = currentPhoneBook.size();
        int numberOfChanges = 5;

        //act
        phoneBookUtils.changeRandomNumbers(currentPhoneBook, logger);

        assertThat(currentPhoneBook).hasSize(initialSize);
        verify(logger, times(numberOfChanges)).debug(contains("Changed number for {} to {}."), anyString(), anyString());
    }

    @Test
    void generateRandomNumber_shouldGenerateNumberWithinRange() {
        int min = 10;
        int max = 20;

        int randomNumber = phoneBookUtils.generateRandomNumber(min, max);

        assertThat(randomNumber).isGreaterThanOrEqualTo(min).isLessThanOrEqualTo(max);
    }

}
