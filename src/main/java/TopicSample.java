import Actuator.Co2VAV;
import Actuator.Ventilator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.topic.ITopic;
import com.hazelcast.topic.Message;
import com.hazelcast.topic.MessageListener;
import domain.SensorSystemImpl;

public class TopicSample implements MessageListener {


    @Override
    public void onMessage(Message message) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        try {
            String json = mapper.writeValueAsString(message.getMessageObject());
            System.out.println(json);
            SensorData s = mapper.readValue(json, SensorData.class);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        //Create config for hz
        ClientConfig config = new ClientConfig();
        config.getNetworkConfig().addAddress("10.112.254.14:5701");

        //New hz instance
        HazelcastInstance hz = HazelcastClient.newHazelcastClient(config);

        //Get topic
        ITopic topic = hz.getTopic("sensor");


        topic.addMessageListener(new TopicSample());



        SensorSystemImpl s = new SensorSystemImpl();

        SensorData co2Data = new SensorData(System.currentTimeMillis(), s.getCo2SensorName(), (float) s.getCo2SensorValue(),"ppm" );
        SensorData temperatureData = new SensorData(System.currentTimeMillis(), s.getTemperatureSensorName(), (float) s.getTemperatureSensorValue(), "celcius");

        Ventilator v = new Ventilator(false);
        Co2VAV c = new Co2VAV(0);

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
