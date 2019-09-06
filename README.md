
# react-native-sensorama

## Getting started

`$ npm install react-native-sensorama --save`

### Mostly automatic installation

`$ react-native link react-native-sensorama`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-sensorama` and add `RNSensorama.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNSensorama.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.sensorama.RNSensoramaPackage;` to the imports at the top of the file
  - Add `new RNSensoramaPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-sensorama'
  	project(':react-native-sensorama').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-sensorama/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-sensorama')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNSensorama.sln` in `node_modules/react-native-sensorama/windows/RNSensorama.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Sensorama.RNSensorama;` to the usings at the top of the file
  - Add `new RNSensoramaPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNSensorama from 'react-native-sensorama';

// TODO: What to do with the module?
RNSensorama;
```
  