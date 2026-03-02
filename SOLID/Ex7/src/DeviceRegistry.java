import java.util.*;

public class DeviceRegistry {

    private final List<Object> devices = new ArrayList<>();

    public void add(Object device) {
        devices.add(device);
    }

    public <T> T getFirstOfType(Class<T> capabilityType) {
        for (Object d : devices) {
            if (capabilityType.isInstance(d)) {
                return capabilityType.cast(d);
            }
        }
        throw new IllegalStateException("Missing device for capability: "
                + capabilityType.getSimpleName());
    }

    public List<Object> getAll() {
    return Collections.unmodifiableList(devices);
}
}
