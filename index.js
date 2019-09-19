import { NativeModules } from 'react-native';

const { ListSensors, Gyroscope: GyroscopeSensor, Proximity: ProximitySensor } = NativeModules;

if (!GyroscopeSensor && !ListSensors && !ProximitySensor) {
  throw new Error(
    'Native modules for sensors not available. Did react-native link run successfully?',
  );
}

export function getSensorList() {
  return ListSensors.getSensorList();
}

export let Gyroscope = {
  isAvailable: GyroscopeSensor.isAvailable,
  getData: GyroscopeSensor.getData,
  setUpdateInterval: GyroscopeSensor.setUpdateInterval,
  startUpdates: GyroscopeSensor.startUpdates,
  stopUpdates: GyroscopeSensor.stopUpdates,
};

export let Proximity = {
  isAvailable: ProximitySensor.isAvailable,
  getData: ProximitySensor.getData,
  setUpdateInterval: ProximitySensor.setUpdateInterval,
  startUpdates: ProximitySensor.startUpdates,
  stopUpdates: ProximitySensor.stopUpdates,
};
