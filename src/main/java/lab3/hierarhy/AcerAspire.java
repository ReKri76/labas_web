package lab3.hierarhy;

public class AcerAspire extends Laptop {
    private boolean hasNumPad = true;
    private String plasticColor = "Silver";
    private boolean acerCareCenterActive = false;
    private int coolingFanSpeed = 1800;
    private boolean isEcoModeEnabled = true;

    public AcerAspire(double price, String name, String cpu, String vendor) {
        super(price, name, cpu, vendor);
    }

    public void launchAcerCareCenter() {
        acerCareCenterActive = true;
        System.out.println("Приложение Acer Care Center запущено. Цвет корпуса: " + plasticColor
            + ", цифровой блок: " + (hasNumPad ? "есть" : "отсутствует")
            + ", состояние: " + (acerCareCenterActive ? "активно" : "неактивно") + ".");
    }

    public void enableTurboBoost() {
        coolingFanSpeed = 5500;
        isEcoModeEnabled = false;
        System.out.println("Включён турбо-режим. Обороты кулера: " + coolingFanSpeed
            + " об/мин. Энергосбережение: " + (isEcoModeEnabled ? "включено" : "отключено") + ".");
    }

    public void calibrateKeyboardBacklight() {
        System.out.println("Подсветка клавиатуры Acer Aspire настроена. Режим энергосбережения: "
            + (isEcoModeEnabled ? "включён" : "отключён") + ".");
    }
}