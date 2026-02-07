package Exercise2;

public class SmartThermostat extends SmartDevice implements Adjustable {

    private int temperature;

    public SmartThermostat(String deviceName) {
        super(deviceName);
        this.temperature = 70; // default comfortable temp
    }

    @Override
    public void turnOn() {
        System.out.println("HVAC System Starting...");
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

        if (level < 60 || level > 80) {
            System.out.println("Temperature must be between 60 and 80.");
            return;
        }

        temperature = level;
        System.out.println(deviceName + " temperature set to " + temperature);
    }

    @Override
    public void performSelfDiagnostic() {
        System.out.println("Running thermostat diagnostics...");
    }
}
