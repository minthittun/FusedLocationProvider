

public class MapBoxActivity extends AppCompatActivity implements LocationProvider.LocationCallback {


    private boolean network_enabled = false;
    private LocationProvider mLocationProvider;
    private double sourceLat = 0;
    private double sourceLng = 0;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
   }

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
