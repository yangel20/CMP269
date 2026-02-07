package Exercise2;

public class SmartLight extends SmartDevice implements Adjustable {

    private int brightness;

    public SmartLight(String deviceName) {
        super(deviceName);
        this.brightness = 0;
    }

    @Override
    public void turnOn() {
        if (!isOn) {
            isOn = true;
            SmartDevice.activeDevicesCount++;
        }
    }

    @Override
    public void turnOff() {
        if (isOn) {
            isOn = false;
            SmartDevice.activeDevicesCount--;
        }
    }

    @Override
    public void setLevel(int level) {
        if (!isOn) {
            System.out.println("Cannot adjust: Device is OFF.");
            return;
        }

        if (level < 0 || level > 100) {
            System.out.println("Brightness must be between 0 and 100.");
            return;
        }

        brightness = level;
        System.out.println(deviceName + " brightness set to " + brightness);
    }

    @Override
    public void performSelfDiagnostic() {
        System.out.println("Checking LED health...");
    }
}
