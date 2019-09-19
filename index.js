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

export let Gyroscope = genExports(GyroscopeSensor);
export let Accelerometer = genExports(AccelerometerSensor);
export let Proximity = genExports(ProximitySensor);
export let Light = genExports(LightSensor);