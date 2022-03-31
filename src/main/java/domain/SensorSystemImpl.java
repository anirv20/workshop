package domain;

import Sensor.CO2SensorAdapter;
import Sensor.ISensor;
import Sensor.TemperatureSensorAdapter;

public class SensorSystemImpl implements SensorSystem {
    private final ISensor co2SensorAdapter;
    private final ISensor temperatureSensorAdapter;

    public SensorSystemImpl()
    {
        this.co2SensorAdapter = new CO2SensorAdapter("Room - 1 - co2");
        this.temperatureSensorAdapter = new TemperatureSensorAdapter("Room - 1 - Temperature");
    }

    public double getCo2SensorValue()
    {
        return co2SensorAdapter.getValue();
    }

    public String getCo2SensorName()
    {
        return co2SensorAdapter.getName();
    }

    public double getTemperatureSensorValue()
    {
        return temperatureSensorAdapter.getValue();
    }

    public String getTemperatureSensorName()
    {
        return temperatureSensorAdapter.getName();
    }


}
