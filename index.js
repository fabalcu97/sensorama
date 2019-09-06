import { NativeModules } from 'react-native';

const { ListSensors, Gyroscope } = NativeModules;

if (!Gyroscope && !ListSensors) {
  throw new Error(
    'Native modules for sensors not available. Did react-native link run successfully?',
  );
}

export function getSensorList() {
  return ListSensors.getSensorList();
}

export let Gyro = {
  isAvailable: Gyroscope.isAvailable,
  getData: Gyroscope.getData,
  setUpdateInterval: Gyroscope.setUpdateInterval,
  startUpdates: Gyroscope.startUpdates,
  stopUpdates: Gyroscope.stopUpdates,
};
