package com.minthittun.bagan;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mapbox.api.directions.v5.DirectionsCriteria;
import com.mapbox.api.directions.v5.MapboxDirections;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.Polyline;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.utils.BitmapUtils;
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute;
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;
import com.minthittun.bagan.common.BaseActivity;
import com.minthittun.bagan.custom_control.MyanButton;
import com.minthittun.bagan.custom_control.MyanHtmlTextView;
import com.minthittun.bagan.custom_control.MyanTextProcessor;
import com.minthittun.bagan.custom_control.MyanTextView;
import com.minthittun.bagan.helper.ActivityAnimationHelper;
import com.minthittun.bagan.helper.LocationProvider;
import com.minthittun.bagan.helper.SharePreferenceHelper;
import com.minthittun.bagan.model.PlaceModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapBoxActivity extends BaseActivity implements LocationProvider.LocationCallback {


    private boolean network_enabled = false;
    private LocationProvider mLocationProvider;
    private double sourceLat = 0;
    private double sourceLng = 0;



    private void init()
    {
        mLocationProvider = new LocationProvider(this, this, false);
        network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @Override
    public void onStart() {
        super.onStart();
        
        mLocationProvider.connect();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
        if (network_enabled) {
            mLocationProvider.connect();
        } else {
            showDialog();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        mLocationProvider.disconnect();
    }


    @Override
    public void handleNewLocation(Location location) {


        sourceLat = location.getLatitude();
        sourceLng = location.getLongitude();


    }

    @Override
    public void getPermission() {

        ActivityCompat.requestPermissions(MapBoxActivity.this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //Granted
                    mLocationProvider.connect();
                } else {
                    //Deny
                    Toast.makeText(getApplicationContext(), "Permission denied!", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }


    }


    private void showDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Please enable location service");
        dialog.setPositiveButton("Setting", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                
                Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(myIntent);

            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {

            }
        });
        dialog.show();
    }



}
