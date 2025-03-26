package com.margarib.integrator;

import com.margarib.integrator.common.PhoneBookUtils;
import com.margarib.integrator.dataSource.PhoneBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.time.Instant;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.margarib.integrator.common.PhoneBookConstants.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PhoneBookTest {
    private PhoneBook phoneBook;
    @Mock
    private PhoneBookUtils phoneBookUtils;
    @Mock
    private Logger logger;
    private ConcurrentHashMap<String, String> currentPhoneBook;
    private NavigableMap<Instant, Map<String, String>> history;
    private AtomicBoolean running;

    @BeforeEach
    void setUp() {
        currentPhoneBook = new ConcurrentHashMap<>();
        history = new TreeMap<>();
        running = new AtomicBoolean(false);
        phoneBook = new PhoneBook(phoneBookUtils, logger, currentPhoneBook, history, running);
    }

    @Test
    void startProgram_shouldRunForTSecondsAndPrintHistoricalRecords() throws InterruptedException {
        // Arrange
        int T = 2;
        int M = 1;
        when(phoneBookUtils.generateRandomNumber(MIN_T + 1, MAX_T + 1)).thenReturn(T);
        when(phoneBookUtils.generateRandomNumber(1, MAX_M + 1)).thenReturn(M);
        when(phoneBookUtils.generateRandomNumber(MIN_N, MAX_N + 1)).thenReturn(100);


        // Act
        Thread thread = new Thread(phoneBook::startProgram);
        thread.start();
        thread.join();


        // Assert
        ArgumentCaptor<Instant> instantCaptor = ArgumentCaptor.forClass(Instant.class);
        verify(logger, atLeastOnce()).info(contains("Saving current phone book at"), instantCaptor.capture());
        verify(logger, atLeastOnce()).info(ArgumentMatchers.contains("Program finished in {} seconds."), anyInt());
    }

}
