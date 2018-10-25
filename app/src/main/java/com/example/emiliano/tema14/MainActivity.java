package com.example.emiliano.tema14;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

private SensorManager mSensorManager;
private Sensor mSensor;
private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.text);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        String s ="";

        if(deviceSensors != null){
            for(int i = 0; i < deviceSensors.size(); i++){
                Log.wtf("Listado sensores", "Nombre: " + deviceSensors.get(i).getName());
                s+= deviceSensors.get(i).getName() + " -- ";
                textView.setText(s);
            }
        }
        if (mSensor == null){
            if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
                mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            }else{
                textView.setText("No tengo acelerómetro");
            }
        }
    }
    @Override
    protected void onResumen(){
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
    /**
     * Called when there is a new sensor event.  Note that "on changed"
     * is somewhat of a misnomer, as this will also be called if we have a
     * new reading from a sensor with the exact same sensor values (but a
     * newer timestamp).
     *
     * <p>See {@link SensorManager SensorManager}
     * for details on possible sensor types.
     * <p>See also {@link SensorEvent SensorEvent}.
     *
     * <p><b>NOTE:</b> The application doesn't own the
     * {@link SensorEvent event}
     * object passed as a parameter and therefore cannot hold on to it.
     * The object may be part of an internal pool and may be reused by
     * the framework.
     *
     * @param SensorEvent the {@link SensorEvent SensorEvent}.
     */

    @Override
    public void onSensorChanged(SensorEvent SensorEvent){
        Log.wtf("onSensorChanged", " " + SensorEvent.values[0]);
        textView.setText("X: " + SensorEvent.values[0] + "   Y: " + SensorEvent.values[1] + "   Z: " + SensorEvent.values[2]);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }
}
