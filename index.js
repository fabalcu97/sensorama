import { NativeModules } from "react-native";

const {
  ListSensors,
  Gyroscope: GyroscopeSensor,
  Proximity: ProximitySensor,
  Accelerometer: AccelerometerSensor,
  Light: LightSensor
} = NativeModules;

export function getSensorList() {
  return ListSensors.getSensorList();
}

function genExports(sensor) {
  return {
    isAvailable: sensor.isAvailable,
    getData: sensor.getData,
    setUpdateInterval: sensor.setUpdateInterval,
    startUpdates: sensor.startUpdates,
    stopUpdates: sensor.stopUpdates
  };
}

export let Gyroscope = {
  isAvailable: GyroscopeSensor.isAvailable,
  getData: GyroscopeSensor.getData,
  setUpdateInterval: GyroscopeSensor.setUpdateInterval,
  startUpdates: GyroscopeSensor.startUpdates,
  stopUpdates: GyroscopeSensor.stopUpdates
};

export let Proximity = {
  isAvailable: ProximitySensor.isAvailable,
  getData: ProximitySensor.getData,
  setUpdateInterval: ProximitySensor.setUpdateInterval,
  startUpdates: ProximitySensor.startUpdates,
  stopUpdates: ProximitySensor.stopUpdates
};

export let Accelerometer = {
  isAvailable: AccelerometerSensor.isAvailable,
  getData: AccelerometerSensor.getData,
  setUpdateInterval: AccelerometerSensor.setUpdateInterval,
  startUpdates: AccelerometerSensor.startUpdates,
  stopUpdates: AccelerometerSensor.stopUpdates
};

export let Light = genExports(LightSensor);
