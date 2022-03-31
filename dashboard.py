from time import sleep
import hazelcast, time, json

def actuator_listener(topic_message):
    actuatorObj = json.loads(topic_message.message)
    #actuatorTime = time.strftime("%B %d %Y", str(actuatorObj["measurementTimeMs"]))
    #print(actuatorTime)
    print(actuatorObj["sensorId"] + ": " + str(actuatorObj["value"]) + " " + actuatorObj["unit"] + " at time " + str(actuatorObj["measurementTimeMs"]))

def sensor_listener(topic_message):
    sensorObj = json.loads(topic_message.message)
    #sensorTime = time.strftime("%B %d %Y", str(sensorObj["measurementTimeMs"]))
    #print(sensorTime)
    print(sensorObj["sensorId"] + ": " + str(sensorObj["value"]) + " " + sensorObj["unit"] + " at time " + str(sensorObj["measurementTimeMs"]))


client = hazelcast.HazelcastClient(cluster_members=["10.112.254.14:5701"])

topic_actuator = client.get_topic("aktuator").blocking()
topic_sensor = client.get_topic("sensor").blocking()


topic_sensor.add_listener(sensor_listener)
topic_actuator.add_listener(actuator_listener)

while True:
    pass
    #topic_sensor.publish("Hello to distributed world")

#client.shutdown()