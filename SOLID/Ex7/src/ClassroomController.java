public class ClassroomController {

    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) {
        this.reg = reg;
    }

    public void startClass() {

        PowerControllable projector = reg.getFirstOfType(PowerControllable.class);
        projector.powerOn();

        InputConnectable input = reg.getFirstOfType(InputConnectable.class);
        input.connectInput("HDMI-1");

        BrightnessControllable lights = reg.getFirstOfType(BrightnessControllable.class);
        lights.setBrightness(60);

        TemperatureControllable ac = reg.getFirstOfType(TemperatureControllable.class);
        ac.setTemperatureC(24);

        AttendanceScannable scanner = reg.getFirstOfType(AttendanceScannable.class);
        System.out.println(
                "Attendance scanned: present=" +
                scanner.scanAttendance()
        );
    }

    public void endClass() {

        System.out.println("Shutdown sequence:");

        for (Object d : reg.getAll()) {
            if (d instanceof PowerControllable pc) {
                pc.powerOff();
            }
        }
    }
}