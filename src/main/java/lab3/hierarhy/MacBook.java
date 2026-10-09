package lab3.hierarhy;

public class MacBook extends Laptop {
    private boolean hasTouchBar = false;
    private String appleSiliconModel = "Apple Silicon";
    private int batteryCycleCount = 0;
    private String colorFinish = "Space Gray";
    private boolean isAirDropEnabled = true;

    public MacBook(double price, String name, String cpu, String vendor) {
        super(price, name, cpu, vendor);
    }

    public void airDropFile(String fileName) {
        if (isAirDropEnabled) {
            System.out.println("Файл " + fileName + " отправлен через AirDrop (" + appleSiliconModel + ").");
        } else {
            System.out.println("AirDrop отключён.");
        }
    }

    public void invokeSiri() {
        System.out.println("Siri готова к работе на " + colorFinish + " MacBook"
                + (hasTouchBar ? " с Touch Bar." : "."));
    }

    public void startTimeMachineBackup() {
        batteryCycleCount++;
        System.out.println("Создание резервной копии Time Machine начато. Циклов батареи: "
                + batteryCycleCount + ".");
    }
}