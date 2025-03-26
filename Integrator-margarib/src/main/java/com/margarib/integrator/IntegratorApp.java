package com.margarib.integrator;

import com.margarib.integrator.dataSource.PhoneBook;

public class IntegratorApp {
    public static void main(String[] args) throws InterruptedException {
        PhoneBook phoneBook = new PhoneBook();

        Thread changePhoneNumberThread = new Thread(phoneBook::startProgram);
        changePhoneNumberThread.start();
        changePhoneNumberThread.join();
    }
}
