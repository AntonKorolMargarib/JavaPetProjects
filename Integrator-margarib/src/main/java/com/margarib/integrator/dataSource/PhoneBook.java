package com.margarib.integrator.dataSource;

import com.margarib.integrator.common.PhoneBookUtils;

import static com.margarib.integrator.common.PhoneBookConstants.*;

import com.margarib.integrator.common.PhoneBookUtilsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Класс, представляющий телефонный справочник.
 */
public class PhoneBook {

    private static Logger logger = LoggerFactory.getLogger(PhoneBook.class);

    /**
     * Хранит текущие записи телефонного справочника.
     * ConcurrentHashMap для обеспечения потокобезопасности.
     */
    private ConcurrentHashMap<String, String> currentPhoneBook = new ConcurrentHashMap<>();
    /**
     * Хранит историю изменений телефонного справочника.  Ключ - timestamp (момент времени),
     * значение - копия справочника на тот момент.
     */
    private NavigableMap<Instant, Map<String, String>> history = new TreeMap<>();

    /**
     * Флаг, указывающий, запущен ли доп поток.
     */
    private AtomicBoolean running = new AtomicBoolean(false);

    PhoneBookUtils phoneBookUtils = new PhoneBookUtilsImpl();

    /**
     * Конструктор класса PhoneBook.
     */
    public PhoneBook() {
        phoneBookUtils.initPhoneBook(currentPhoneBook, logger);
    }

    /**
     * Конструктор для теста
     * @param phoneBookUtils - экземпляр
     * @param logger - логер
     * @param currentPhoneBook - справочник
     * @param history - запись справочника
     * @param running - флаг для потока измененния номеров
     */
    public PhoneBook(PhoneBookUtils phoneBookUtils, Logger logger, ConcurrentHashMap<String, String> currentPhoneBook, NavigableMap<Instant, Map<String, String>> history, AtomicBoolean running) {
        this.phoneBookUtils = phoneBookUtils;
        this.logger = logger;
        this.currentPhoneBook = currentPhoneBook;
        this.history = history;
        this.running = running;
    }


    /**
     * Запускает поток, изменяющий номера телефонов в справочнике.
     */
    public void startProgram() {
        int T = phoneBookUtils.generateRandomNumber(MIN_T + 1, MAX_T + 1);
        int M = phoneBookUtils.generateRandomNumber(1, MAX_M + 1);
        logger.info("Program will run for {} seconds.", T);
        logger.info("Retrieving phone book state {} seconds ago after program.", M);

        running.set(true);
        Instant startTime = Instant.now();

        while (running.get()) {
            int N = phoneBookUtils.generateRandomNumber(MIN_N, MAX_N + 1);
            try {
                Thread.sleep(N);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Thread interrupted.", e);
                break;
            }

            if (Instant.now().isAfter(startTime.plusSeconds(T))) {
                Instant endTime = Instant.now();
                stopProgram();
                printHistoricalRecords(endTime, M);
                break;
            }
            phoneBookUtils.changeRandomNumbers(currentPhoneBook, logger);
            if (Instant.now().isAfter(startTime.plusSeconds(T - M - (N / 1000 + 1)))
                    && Instant.now().isBefore(startTime.plusSeconds(T - M))) {
                saveCurrentPhoneBook();
            }
        }
        logger.info("Program finished in {} seconds.", T);
    }

    /**
     * Останавливает поток, отвечающий за изменение номеров
     * и вызывает метод выведения на экран снимок справочника по времени M
     */
    public void stopProgram() {
        running.set(false);
        logger.info("Program stopped.");
    }

    /**
     * Сохраняет текущее состояние справочника в историю (после обновления номеров).
     */
    private void saveCurrentPhoneBook() {
        history.put(Instant.now(), new ConcurrentHashMap<>(currentPhoneBook));
        logger.info("Saving current phone book at {}", history.lastEntry().getKey());
    }

    /**
     * Выводит в консоль записи из справочника, которые существовали M секунд назад.
     * @param endTime время завершения программы
     * @param M Количество секунд назад.
     */
    public void printHistoricalRecords (Instant endTime, int M) {
        Map.Entry<Instant, Map<String, String>> entry = history.floorEntry(endTime.minusSeconds(M));
        if (entry != null) {
            Map<String, String> historicalRecords = entry.getValue();
            System.out.println("PhoneBook records " + M + " seconds ago:");
            historicalRecords.forEach((name, number) -> System.out.println(name + ": " + number));
        } else {
            System.out.println("No records found " + M + " seconds ago");
        }
        logger.info("Printed records for {} seconds ago", M);
    }
}
