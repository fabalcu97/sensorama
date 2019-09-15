import { NativeModules } from 'react-native';

const { ListSensors, Gyroscope, Accelerometer : AccelerometerRN } = NativeModules;

if (!Gyroscope || !ListSensors || !AccelerometerRN) {
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

export let Accelerometer = {
  isAvailable: AccelerometerRN.isAvailable,
  getData: AccelerometerRN.getData,
  setUpdateInterval: AccelerometerRN.setUpdateInterval,
  startUpdates: AccelerometerRN.startUpdates,
  stopUpdates: AccelerometerRN.stopUpdates,
};

