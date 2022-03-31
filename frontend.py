from flask import Flask, render_template
from flask_cors import CORS
from datetime import datetime
import hazelcast, time, json

app = Flask(__name__)
CORS(app)
sensorList = dict()
actuatorList = dict()

def add_listeners():
    client = hazelcast.HazelcastClient(cluster_members=["10.112.254.14:5701"])

    topic_actuator = client.get_topic("aktuator").blocking()
    topic_sensor = client.get_topic("sensor").blocking()

    topic_sensor.add_listener(sensor_listener)
    topic_actuator.add_listener(actuator_listener)

def actuator_listener(topic_message):
    actuatorObj = json.loads(topic_message.message)
    actuatorObj["measurementTimeMs"] = datetime.utcfromtimestamp(int(actuatorObj["measurementTimeMs"]/1000)).strftime('%Y-%m-%d %H:%M:%S')
    actuatorList[str(actuatorObj["sensorId"])] = actuatorObj
    #print(actuatorObj["sensorId"] + ": " + str(actuatorObj["value"]) + " " + actuatorObj["unit"] + " at time " + str(actuatorObj["measurementTimeMs"]))

def sensor_listener(topic_message):
    sensorObj = json.loads(topic_message.message)
    sensorObj["measurementTimeMs"] = datetime.utcfromtimestamp(int(sensorObj["measurementTimeMs"]/1000)).strftime('%Y-%m-%d %H:%M:%S')
    sensorList[str(sensorObj["sensorId"])] = sensorObj
    #print(sensorObj["sensorId"] + ": " + str(sensorObj["value"]) + " " + sensorObj["unit"] + " at time " + str(sensorObj["measurementTimeMs"]))

add_listeners()

@app.route('/')
def hello():
    return render_template('index.html', sensors=sensorList, actuators=actuatorList)

@app.route('/getSensors')
def getSensors():
    return str(list(sensorList.values()))

@app.route('/getActuators')
def getActuators():
    return str(list(actuatorList.values()))