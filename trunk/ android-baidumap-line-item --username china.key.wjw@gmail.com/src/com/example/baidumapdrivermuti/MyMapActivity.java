package com.example.baidumapdrivermuti;

import java.util.Vector;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.MKSearch;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;

public class MyMapActivity extends MapActivity {

	static View mPopView = null; // 点击mark时弹出的气泡View
	BMapManager mBMapMan = null;
	MKSearch mMKSearch = null;
	Button btn_search = null;
	static MapView mMapView = null;
	final static Vector<GeoPoint> GeoPointsVec = new Vector<GeoPoint>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mBMapMan = new BMapManager(getApplication());
		mBMapMan.init("63245A98432B794F37BFA6821F1A11995A940863", null);
		super.initMapActivity(mBMapMan);

		mMapView = (MapView) findViewById(R.id.bmapsView);
		mMapView.setBuiltInZoomControls(true); // 设置启用内置的缩放控件

		MapController mMapController = mMapView.getController(); // 得到mMapView的控制权,可以用它控制和驱动平移和缩放
		GeoPoint point = new GeoPoint((int) (39.915 * 1E6),
				(int) (116.404 * 1E6)); // 用给定的经纬度构造一个GeoPoint，单位是微度 (度 * 1E6)
		mMapController.setCenter(point); // 设置地图中心点
		mMapController.setZoom(12); // 设置地图zoom级别

		double mLat1 = 39.90923;//39.9022; // point1纬度
	    double mLon1 = 116.397428;//116.3822; // point1经度
	 
	    double mLat2 = 39.9022;
	    double mLon2 = 116.3922;
	 
	    double mLat3 = 39.917723;
	    double mLon3 = 116.3722;

		GeoPoint point0, point1, point2;
		point0 = new GeoPoint((int) (mLat1 * 1E6), (int) (mLon1 * 1E6));
		point1 = new GeoPoint((int) (mLat2 * 1E6), (int) (mLon2 * 1E6));
		point2 = new GeoPoint((int) (mLat3 * 1E6), (int) (mLon3 * 1E6));
		GeoPointsVec.add(point0);
		GeoPointsVec.add(point1);
		GeoPointsVec.add(point2);

		for (int index = 0; index < GeoPointsVec.size(); index++) {

		}

		btn_search = (Button) findViewById(R.id.btn_search);
		btn_search.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				LineOverlay lineOverlay = new LineOverlay();
				mMapView.getOverlays().add(lineOverlay);

				Drawable marker = getResources().getDrawable(R.drawable.pop);  //得到需要标在地图上的资源
				OverItemT overItemT = new OverItemT(marker, MyMapActivity.this,0);
				mMapView.getOverlays().add(overItemT);

				MapController mapController = mMapView.getController();
				mapController.zoomIn();
			}
		});

		mPopView = super.getLayoutInflater().inflate(R.layout.popview, null);
		mMapView.addView(mPopView, new MapView.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, null,
				MapView.LayoutParams.TOP_LEFT));
		mPopView.setVisibility(View.GONE);

	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	protected void onDestroy() {
		if (mBMapMan != null) {
			mBMapMan.destroy();
			mBMapMan = null;
		}
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		if (mBMapMan != null) {
			mBMapMan.stop();
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		if (mBMapMan != null) {
			mBMapMan.start();
		}
		super.onResume();
	}

}
