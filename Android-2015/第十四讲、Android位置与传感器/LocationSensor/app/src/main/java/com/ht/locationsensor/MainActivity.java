package com.ht.locationsensor;

import java.util.List;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	
	private Button getLocationBtn;
	private TextView locationTxt;
	
	private TextView accelerometer;
	private TextView magnetic;
	private TextView light;
	private TextView orientation;
	
	private SensorManager sensorMgr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getLocationBtn = (Button) findViewById(R.id.getLocationBtn);
		locationTxt = (TextView) findViewById(R.id.locationTxt);
		
		accelerometer = (TextView) findViewById(R.id.accelerometer);
		magnetic = (TextView) findViewById(R.id.magnetic);
		light = (TextView) findViewById(R.id.light);
		orientation = (TextView) findViewById(R.id.orientation);
		
		sensorMgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		
		getLocationBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
				Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				updateLocation(location);
				lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, new LocationListener() {

					@Override
					public void onLocationChanged(Location location) {
						updateLocation(location);
					}

					@Override
					public void onStatusChanged(String provider, int status, Bundle extras) {
			            switch (status) {
			            //GPS状态为可见时
			            case LocationProvider.AVAILABLE:
			                Log.d("location", "当前GPS状态为可见状态");
			                break;
			            //GPS状态为服务区外时
			            case LocationProvider.OUT_OF_SERVICE:
			                Log.d("location", "当前GPS状态为服务区外状态");
			                break;
			            //GPS状态为暂停服务时
			            case LocationProvider.TEMPORARILY_UNAVAILABLE:
			                Log.d("location", "当前GPS状态为暂停服务状态");
			                break;
			                default:break;
			            }
			        }

					@Override
					public void onProviderEnabled(String provider) {
						updateLocation(lm.getLastKnownLocation(provider));
					}

					@Override
					public void onProviderDisabled(String provider) {
						updateLocation(null);
					}
					
				});
			}
			
		});
		
	}
	
	private void updateLocation(Location location) {
		if (location == null) {
			locationTxt.setText("无可用的位置信息");
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("当前位置信息：\n");
			sb.append("经度：" + location.getLongitude() + "\n");
			sb.append("纬度：" + location.getLatitude() + "\n");
			sb.append("海拔：" + location.getAltitude() + "\n");
			sb.append("速度：" + location.getSpeed() + "\n");
			sb.append("方向：" + location.getBearing());
			locationTxt.setText(sb.toString());
		}
		
		getAllSensors();
		registerSensors();
	}
	
	private void getAllSensors() {
		List<Sensor> sensors = sensorMgr.getSensorList(Sensor.TYPE_ALL);
		String sensorInfo = "";
		for (Sensor sensor : sensors) {
			sensorInfo += sensor.getName() + ": " + sensor.getType() + "\n";
		}
		Log.d("sensors", "all sensors: " + sensorInfo);
	}
	
	private void registerSensors() {
		SensorEventListener sensorListener = new MySensorListener();
		sensorMgr.registerListener(sensorListener, 
				sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), 
				SensorManager.SENSOR_DELAY_FASTEST);
		sensorMgr.registerListener(sensorListener, 
				sensorMgr.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
				SensorManager.SENSOR_DELAY_FASTEST);
		sensorMgr.registerListener(sensorListener, 
				sensorMgr.getDefaultSensor(Sensor.TYPE_LIGHT), 
				SensorManager.SENSOR_DELAY_FASTEST);
		sensorMgr.registerListener(sensorListener, 
				sensorMgr.getDefaultSensor(Sensor.TYPE_ORIENTATION),
				SensorManager.SENSOR_DELAY_FASTEST);
	}
	
	private class MySensorListener implements SensorEventListener {
		@Override
		public void onSensorChanged(SensorEvent event) {
			int sensorType = event.sensor.getType();
			switch (sensorType) {
			case Sensor.TYPE_ACCELEROMETER:
				String info = "加速度：\n" + "x: " + event.values[0] + "\ny:" 
			+ event.values[1] + "\nz: " + event.values[2];
				accelerometer.setText(info);
				break;
			case Sensor.TYPE_MAGNETIC_FIELD:
				String info1 = "磁场：\n" + "x: " + event.values[0] + "\ny:" 
						+ event.values[1] + "\nz: " + event.values[2];
				magnetic.setText(info1);
				break;
			case Sensor.TYPE_LIGHT:
				light.setText("亮度： " + event.values[0]);
				break;
			case Sensor.TYPE_ORIENTATION:
				String info2 = "方向：\n" + "x: " + event.values[0] + "\ny:" 
						+ event.values[1] + "\nz: " + event.values[2];
				accelerometer.setText(info2);
				break;
				default:break;
			}
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
