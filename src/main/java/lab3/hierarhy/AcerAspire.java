package lab3.hierarhy;

public class AcerAspire extends Laptop {
    private boolean hasNumPad;
    private String plasticColor;
    private boolean acerCareCenterActive;
    private int coolingFanSpeed;
    private boolean isEcoModeEnabled;

    public AcerAspire(double price, String name, String cpu, String vendor) {
        super(price, name, cpu, vendor);
    }

    public void launchAcerCareCenter() {
        acerCareCenterActive = true;
        System.out.println("Фирменное приложение Acer Care Center запущено.");
    }

    public void enableTurboBoost() {
        coolingFanSpeed = 5500;
        isEcoModeEnabled = false;
        System.out.println("Включен турбо-режим. Обороты кулера: " + coolingFanSpeed + " RPM.");
    }

    public void calibrateKeyboardBacklight() {
        System.out.println("Яркость подсветки клавиш Acer Aspire подстроена под освещение.");
    }
}