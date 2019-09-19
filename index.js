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
  isAvailable: Gyroscope.isAvailable,
  getData: Gyroscope.getData,
  setUpdateInterval: Gyroscope.setUpdateInterval,
  startUpdates: Gyroscope.startUpdates,
  stopUpdates: Gyroscope.stopUpdates,
};

export let Proximity = {
  isAvailable: Proximity.isAvailable,
  getData: Proximity.getData,
  setUpdateInterval: Proximity.setUpdateInterval,
  startUpdates: Proximity.startUpdates,
  stopUpdates: Proximity.stopUpdates,
};
