package com.sensorama;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

import java.util.List;


public class ListSensors extends ReactContextBaseJavaModule {
    private static final String E_SENSOR_LIST_ERROR = "E_SENSOR_LIST_ERROR";

    private final SensorManager sensorManager;

    public ListSensors(ReactApplicationContext reactContext) {
        super(reactContext);
        this.sensorManager = (SensorManager) reactContext.getSystemService(
                ReactApplicationContext.SENSOR_SERVICE
        );

    }

    @Override
    public String getName() {
        return "ListSensors";
    }

    public WritableMap getSensorAttrs(Sensor sensor) {
        WritableMap map = Arguments.createMap();
        map.putInt("fifoMaxEventCount", sensor.getFifoMaxEventCount());
        map.putInt("fifoReservedEventCount", sensor.getFifoReservedEventCount());
        map.putDouble("maximumRange", sensor.getMaximumRange());
        map.putInt("minDelay", sensor.getMinDelay());
        map.putString("name", sensor.getName());
        map.putDouble("power", sensor.getPower());

        map.putDouble("resolution", sensor.getResolution());
        map.putInt("type", sensor.getType());


        if (Build.VERSION.SDK_INT >= 20) {
            map.putString("stringType", sensor.getStringType());
        }

        if (Build.VERSION.SDK_INT >= 21) {
            map.putInt("maxDelay", sensor.getMaxDelay());
            map.putInt("reportingMode", sensor.getReportingMode());
            map.putBoolean("isWakeUpSensor", sensor.isWakeUpSensor());
        }

        if (Build.VERSION.SDK_INT >= 24) {
            map.putInt("id", sensor.getId());
            map.putBoolean("isAdditionalInfoSupported", sensor.isAdditionalInfoSupported());
            map.putBoolean("isDynamicSensor", sensor.isDynamicSensor());
        }


        if (Build.VERSION.SDK_INT >= 26) {
            map.putInt("highestDirectReportRateLevel", sensor.getHighestDirectReportRateLevel());
            // TODO add support for isDirectionalChannelTypeSupported
        }

        return map;

    }


    @ReactMethod
    public void getSensorList(Promise promise) {
        try {
            List<Sensor> sensors = this.sensorManager.getSensorList(Sensor.TYPE_ALL);
            WritableArray array = Arguments.createArray();
            for (Sensor sensor : sensors) {
                array.pushMap(getSensorAttrs(sensor));
            }

            promise.resolve(array);

        } catch (Exception e) {
            promise.reject(E_SENSOR_LIST_ERROR, e);

        }
    }


}
