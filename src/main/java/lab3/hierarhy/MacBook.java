package lab3.hierarhy;

public class MacBook extends Laptop {
    private boolean hasTouchBar;
    private String appleSiliconModel;
    private int batteryCycleCount;
    private String colorFinish;
    private boolean isAirDropEnabled;

    public MacBook(double price, String name, String cpu, String vendor) {
        super(price, name, cpu, vendor);
    }

    public void airDropFile(String fileName) {
        if (isAirDropEnabled) {
            System.out.println("Файл " + fileName + " успешно отправлен через AirDrop с чипа " + appleSiliconModel + ".");
        }
    }

    public void invokeSiri() {
        System.out.println("Siri слушает... Чем я могу помочь?");
    }

    public void startTimeMachineBackup() {
        System.out.println("Создание резервной копии Time Machine начато.");
    }
}