public class SensorData {

    private long measurementTimeMs;
    private String sensorId;
    private float value;
    private String unit;


    public long getMeasurementTimeMs() {
        return measurementTimeMs;
    }

    public void setMeasurementTimeMs(long measurementTimeMs) {
        this.measurementTimeMs = measurementTimeMs;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public SensorData(long measurementTimeMs, String id, float value, String unit)
    {
        this.measurementTimeMs = measurementTimeMs;
        this.sensorId = id;
        this.value = value;
        this.unit = unit;
    }
}
