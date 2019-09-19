package com.sensorama;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public abstract class BaseRNSensor extends ReactContextBaseJavaModule implements SensorEventListener {

    private final ReactApplicationContext reactContext;
    private final SensorManager sensorManager;
    private final Sensor sensor;
    private int interval;

    public BaseRNSensor(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        this.sensorManager = (SensorManager) reactContext.getSystemService(
                ReactApplicationContext.SENSOR_SERVICE
        );
        this.sensor = this.sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    // RN Methods

    @ReactMethod
    public void isAvailable(Promise promise) {
        promise.resolve(this.sensor != null);
    }

    @ReactMethod
    public void getData(Promise promise) {
        if (this.sensor == null) {
            promise.reject(new RuntimeException("No Gyroscope found"));
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
        // Milliseconds to Microseconds conversion
        sensorManager.registerListener(this, sensor, this.interval * 1000);
    }

    @ReactMethod
    public void stopUpdates() {
        sensorManager.unregisterListener(this);
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

}
