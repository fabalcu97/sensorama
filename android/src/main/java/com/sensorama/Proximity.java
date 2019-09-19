package com.sensorama;

import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class Proximity extends ReactContextBaseJavaModule implements SensorEventListener {

    private final ReactApplicationContext reactContext;
    private final SensorManager sensorManager;
    private final Sensor sensor;
    private double lastReading = (double) System.currentTimeMillis();
    private int interval;
    private Arguments arguments;

    public Proximity(ReactApplicationContext reactContext) {
        super(reactContext);
        Log.d("Proximity", "Constructor");
        this.reactContext = reactContext;
        this.sensorManager = (SensorManager) reactContext.getSystemService(reactContext.SENSOR_SERVICE);
        this.sensor = this.sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    // RN Methods
    @ReactMethod
    public void isAvailable(Promise promise) {
        if (this.sensor == null) {
            promise.reject(new RuntimeException("No proximity sensor found"));
            return;
        }
        promise.resolve("Proximity sensor available!");
    }

    @ReactMethod
    public void getData(Promise promise) {
        if (this.sensor == null) {
            promise.reject(new RuntimeException("No proximity sensor found"));
            return;
        }
        WritableMap map = Arguments.createMap();
        map.putString("name", sensor.getName());
        map.putInt("type", sensor.getType());
        map.putDouble("maximumRange", sensor.getMaximumRange());
        map.putInt("minDelay", sensor.getMinDelay());
        map.putDouble("resolution", sensor.getResolution());
        promise.resolve(map);
    }

    @ReactMethod
    public void setUpdateInterval(int newInterval) {
        this.interval = newInterval;
    }

    @ReactMethod
    public void startUpdates() {
        sensorManager.registerListener(this, sensor, this.interval * 1000);
    }

    @ReactMethod
    public void stopUpdates() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public String getName() {
        return "Proximity";
    }

    // SensorEventListener Interface
    private void sendEvent(String eventName, @Nullable WritableMap params) {
        try {
            this.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, params);
        } catch (RuntimeException e) {
            Log.e("ERROR",
                    "java.lang.RuntimeException: Trying to invoke Javascript before CatalystInstance has been set!");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.d("Proximity", "onSensorChanged");
        double tempMs = (double) System.currentTimeMillis();
        if (tempMs - lastReading >= interval) {
            lastReading = tempMs;

            Sensor mySensor = sensorEvent.sensor;
            WritableMap map = arguments.createMap();

            if (mySensor.getType() == Sensor.TYPE_PROXIMITY) {
                map.putDouble("distance", sensorEvent.values[0]);
                sendEvent("Proximity", map);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}