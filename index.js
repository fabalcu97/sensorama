
import { NativeModules } from 'react-native';

const { ListSensors } = NativeModules;

export default ListSensors;

export function getSensorList() {
  return ListSensors.getSensorList()
}

