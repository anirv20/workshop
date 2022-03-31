public class SensorData {

    private long measurementTimeMs;
    private String sensorId;
    private float value;
    private String unit;

    public SensorData() {}

    public SensorData(long measurementTimeMs, String sensorId, float value, String unit)
    {
        this.measurementTimeMs = measurementTimeMs;
        this.sensorId = sensorId;
        this.value = value;
        this.unit = unit;
    }

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

    @Override
    public String toString(){
        return "SensorData{" +
                "measurementTimeMs:" + measurementTimeMs +
                ", sensorId:" + sensorId + '\'' +
                ", value:" + value + '\'' +
                ", unit:" + unit + '\'' +
                '}';
    }


}
