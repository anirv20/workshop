import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.topic.ITopic;
import domain.SensorSystemImpl;

import java.time.Instant;

public class Main {

    public static void main(String[] args) throws JsonProcessingException {

        //Create config for hz
        ClientConfig config = new ClientConfig();
        config.getNetworkConfig().addAddress("10.112.254.14:5701");

        ObjectMapper mapper = new ObjectMapper();
        //New hz instance
        HazelcastInstance hz = HazelcastClient.newHazelcastClient(config);

        //Get topic
        ITopic topic = hz.getTopic("sensor");


        SensorSystemImpl s = new SensorSystemImpl();

        SensorData co2Data = new SensorData(System.currentTimeMillis(), s.getCo2SensorName(), (float) s.getCo2SensorValue(),"ppm" );
        SensorData temperatureData = new SensorData(System.currentTimeMillis(), s.getTemperatureSensorName(), (float) s.getTemperatureSensorValue(), "celcius");

        String co2Json = mapper.writeValueAsString(co2Data);
        String tempJson = mapper.writeValueAsString(temperatureData);
        while(true){
            try{
                topic.publish(co2Json);
                topic.publish(tempJson);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
