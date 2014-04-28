package com.sonymobile.smartconnect.extension.controlsample;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationRetriever extends Activity {

	private LocationListener mlocListener;
	private LocationManager mlocManager = null;
	private HashMap<String, Double> coordinates = new HashMap<String, Double>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getGPScoord();
	}

	private void getGPScoord() {

		mlocListener = new MyLocationListener();
		mlocManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);

		mlocManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);

		mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
				mlocListener);

		if (mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			if (MyLocationListener.latitude > 0) {
				System.out.println("Latitude:- " + MyLocationListener.latitude
						+ '\n');
				coordinates.put("latitude", MyLocationListener.latitude);
				System.out.println("Longitude:- "
						+ MyLocationListener.longitude + '\n');
				coordinates.put("longitude", MyLocationListener.longitude);
				
			} else {
				// it has to wait some seconds before getting the GPS
				// coordinates
			}
		} else {
			System.out.println("GPS is not turned on...");
		}

	}

	
	//needs to store the GPS coordinates
	//needs to be implemented
	private void storeGPScoord(double latitude, double longitude)
	{
		//store to database
	}
	
	@Override
	protected void onResume() {
		LocationRetriever.this.finish();
		super.onResume();
	}

}
