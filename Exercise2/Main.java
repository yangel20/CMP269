package Exercise2;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<SmartDevice> homeHub = new ArrayList<>();

        SmartLight livingRoom = new SmartLight("Living Room");
        SmartThermostat hallway = new SmartThermostat("Hallway");
        SmartLight kitchen = new SmartLight("Kitchen");

        homeHub.add(livingRoom);
        homeHub.add(hallway);
        homeHub.add(kitchen);

        livingRoom.turnOn();
        hallway.turnOn();
        kitchen.setLevel(75);

        System.out.println("Active Devices: " + SmartDevice.activeDevicesCount);

        for (SmartDevice d : homeHub) {
            d.performSelfDiagnostic();
        }
    }
}
