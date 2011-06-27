package kr.yui.android.simplegooglemapviewer;

import java.io.IOException;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends MapActivity {
	
	private EditText addressEditText;
	private SimpleMapView mapView;
	private Button getPosBtn;
	private MapController mapController;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        addressEditText = (EditText)findViewById(R.id.main_addresstext_id);
        
        ((Button)findViewById(R.id.main_searchbtn_id)).setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				alertSearchAddress();
			}
		});
        
        getPosBtn = (Button)findViewById(R.id.main_getposbtn_id);
        getPosBtn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				alertCurrentPosition();
			}

		});
        
        GeoPoint gp = new GeoPoint((int)(37.5643406 * 1e6), (int)(126.9756087 * 1e6));
        
        mapView = (SimpleMapView)findViewById(R.id.main_mapview_id);
        mapView.setBuiltInZoomControls(true);
        mapView.setGetPositionBtn(getPosBtn);
        mapView.setGeoPoint(gp);
        
        mapController = mapView.getController();
        mapController.setZoom(15);
        mapController.animateTo(gp);
    }
    
	protected void alertSearchAddress() {

		Geocoder gc = new Geocoder(this);
		List<Address> list;
		
		String search = addressEditText.getText().toString();
		
		try {
			list = gc.getFromLocationName(search, 1);
			if (list.size() > 0) {
				Address addr = list.get(0);
				GeoPoint newgp = new GeoPoint((int)(addr.getLatitude() * 1e6), (int)(addr.getLongitude() * 1e6));
				mapController.animateTo(newgp);
				String msg = addr.getAddressLine(0) + "\n\n" + newgp.getLatitudeE6() + ":" + newgp.getLongitudeE6();
				Toast.makeText(this, msg, 1).show();
				mapView.setGeoPoint(newgp);
			}
		} catch (IOException e) {
			Toast.makeText(this, "일치하는 위치가 없습니다.", 1).show();
		}
	}

	private void alertCurrentPosition() {
		GeoPoint gp = mapView.getGeoPoint();
		Geocoder gc = new Geocoder(this);
		List<Address> list;
		
		try {
			list = gc.getFromLocation(gp.getLatitudeE6() / 1e6, gp.getLongitudeE6() / 1e6, 1);
			if (list.size() > 0) {
				Address addr = list.get(0);
				String msg = addr.getAddressLine(0) + "\n\n" + gp.getLatitudeE6() + ":" + gp.getLongitudeE6();
				Toast.makeText(this, msg, 1).show();
			} else {
				Toast.makeText(this, "현재 위치를 몰라요.", 1).show();
			}
		} catch (IOException e) {
			Toast.makeText(this, "현재 위치를 몰라요.", 1).show();
		}
	}

	/* (non-Javadoc)
	 * @see com.google.android.maps.MapActivity#isLocationDisplayed()
	 */
	@Override
	protected boolean isLocationDisplayed() {
		// TODO Auto-generated method stub
		return super.isLocationDisplayed();
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}