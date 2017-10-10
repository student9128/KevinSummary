package com.kevin.tech.kevinsummary.activity.thirdparty;

import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.kevin.tech.kevinsummary.R;
import com.kevin.tech.kevinsummary.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2017/10/9.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public class BaiduMapActivity extends BaseActivity implements SensorEventListener, View.OnClickListener, BaiduMap.OnMapClickListener {
    @BindView(R.id.bd_map_view)
    MapView bdMapView;

    @BindView(R.id.fl_content)
    RelativeLayout flContent;
    @BindView(R.id.iv_plus)
    ImageView ivPlus;
    @BindView(R.id.iv_minus)
    ImageView ivMinus;
    @BindView(R.id.iv_location)
    ImageView ivLocation;
    @BindView(R.id.iv_traffic)
    ImageView ivTraffic;
    @BindView(R.id.ll_traffic)
    LinearLayout llTraffic;
    @BindView(R.id.ll_scale_control)
    LinearLayout llScaleControl;
    @BindView(R.id.iv_view)
    ImageView ivView;

    private BaiduMap mBaiduMap;

    // 定位相关
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    private MyLocationConfiguration.LocationMode mCurrentMode;
    BitmapDescriptor mCurrentMarker;
    private static final int accuracyCircleFillColor = 0xAAFFFF88;
    private static final int accuracyCircleStrokeColor = 0xAA00FF00;
    private SensorManager mSensorManager;
    private Double lastX = 0.0;
    private int mCurrentDirection = 0;
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;
    private float mCurrentAccracy;

    boolean isFirstLoc = true; // 是否首次定位
    private MyLocationData locData;
    private float direction;
    private float maxZoomLevel;
    private float minZoomLevel;
    private float mCurrentZoomLevel;
    private static boolean isTrafficOpen = false;
    private static boolean isViewcSwitch = false;//普通视图和卫星视图切换

    @Override
    public int setLayoutResId() {
        return R.layout.activity_baidu_map;
    }

    @Override
    public void initView() {
        actionBar.setTitle("百度地图");
        bdMapView.setLogoPosition(LogoPosition.logoPostionleftTop);//设置logo位置
        bdMapView.getChildAt(1).setPadding(30, 110, 0, 0);//修改logo位置
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);//获取传感器管理服务
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        mBaiduMap = bdMapView.getMap();
        //开启交通图
//        mBaiduMap.setTrafficEnabled(true);
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();
//
//        //定义Maker坐标点
//        LatLng point = new LatLng(39.963175, 116.400244);
////构建Marker图标
//        BitmapDescriptor bitmap = BitmapDescriptorFactory
//                .fromResource(R.drawable.ic_map_marka);
////构建MarkerOption，用于在地图上添加Marker
//        OverlayOptions option = new MarkerOptions()
//                .position(point)
//                .icon(bitmap);
////在地图上添加Marker，并显示
//        mBaiduMap.addOverlay(option);
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
//        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(
//                mCurrentMode, true, mCurrentMarker));
        MapStatus.Builder builder1 = new MapStatus.Builder();
        builder1.overlook(0);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder1.build()));
        bdMapView.showZoomControls(false);
//        bdMapView.getChildAt(2).setPadding(50,50,0,0);//修改缩放控件位置
        //监听状态变化，获取缩放级别
        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {
                mCurrentZoomLevel = mapStatus.zoom;
            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {

//                LatLng latLng = mapStatus.target;
//
//                //实例化一个地理编码查询对象
//                GeoCoder geoCoder = GeoCoder.newInstance();
//                //设置反地理编码位置坐标
//                ReverseGeoCodeOption op = new ReverseGeoCodeOption();
//                op.location(latLng);
//                //发起反地理编码请求(经纬度->地址信息)
//                geoCoder.reverseGeoCode(op);
//                geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
//
//                    @Override
//                    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult arg0) {
//                        //获取点击的坐标地址
//                        String address = arg0.getAddress();
//                    }
//
//                    @Override
//                    public void onGetGeoCodeResult(GeoCodeResult arg0) {
//                    }
//                });
////            myLatLng = new LatLng(latitude, longitude);
//
//                //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
//                MapStatusUpdate mMapStatus = MapStatusUpdateFactory.newLatLng(latLng);
//                //改变地图状态将这个坐标设为中心点
//                mBaiduMap.animateMapStatus(mMapStatus);
            }
        });

        //调整比例尺位置，必须写到Callback里面
        mBaiduMap.setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                bdMapView.setScaleControlPosition(new Point(50, 50));
            }
        });

        // 修改为自定义marker
        mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.drawable.ic_map_location);
        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(
                mCurrentMode, true, mCurrentMarker,
                getMyColor(R.color.mapColor), getMyColor(R.color.mapColor)));

        maxZoomLevel = mBaiduMap.getMaxZoomLevel();
        minZoomLevel = mBaiduMap.getMinZoomLevel();

    }

    @Override
    public void initData() {


    }

    @Override
    public void initListener() {
        ivLocation.setOnClickListener(this);
        ivPlus.setOnClickListener(this);
        ivMinus.setOnClickListener(this);
        llTraffic.setOnClickListener(this);
        mBaiduMap.setOnMapClickListener(this);
        ivView.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
//        在activity执行onDestroy时执行bdMapView.onDestroy()，实现地图生命周期管理
//        bdMapView.onDestroy();

        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        bdMapView.onDestroy();
        bdMapView = null;
        super.onDestroy();
        isTrafficOpen = false;
        isViewcSwitch = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行bdMapView. onResume ()，实现地图生命周期管理
        bdMapView.onResume();
        //为系统的方向传感器注册监听器
        mSensorManager.registerListener((SensorEventListener) this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行bdMapView. onPause ()，实现地图生命周期管理
        bdMapView.onPause();

    }

    @Override
    protected void onStop() {
        //取消注册传感器监听
        mSensorManager.unregisterListener((SensorEventListener) this);
        super.onStop();
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        double x = sensorEvent.values[SensorManager.DATA_X];
        if (Math.abs(x - lastX) > 1.0) {
            mCurrentDirection = (int) x;
            locData = new MyLocationData.Builder()
                    .accuracy(mCurrentAccracy)
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(mCurrentLat)
                    .longitude(mCurrentLon).build();
            mBaiduMap.setMyLocationData(locData);
        }
        lastX = x;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onClick(View v) {
        printLogd("cre=====" + mCurrentZoomLevel);
        switch (v.getId()) {
            case R.id.iv_location:
                isFirstLoc = true;
                // 开启定位图层
//                mBaiduMap.setMyLocationEnabled(true);
//                // 定位初始化
                mLocClient = new LocationClient(this);
                mLocClient.registerLocationListener(myListener);
                LocationClientOption option = new LocationClientOption();
                option.setOpenGps(true); // 打开gps
                option.setCoorType("bd09ll"); // 设置坐标类型
                option.setScanSpan(1000);
                mLocClient.setLocOption(option);
                mLocClient.start();
                break;
            case R.id.iv_plus:
                printLogd("max=======" + maxZoomLevel);
                if (mCurrentZoomLevel >= maxZoomLevel) {
                    ivPlus.setImageResource(R.drawable.ic_map_plus_gray);
                    ivPlus.setEnabled(false);
                } else {
                    mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomIn());
                    ivPlus.setEnabled(true);
                    ivPlus.setImageResource(R.drawable.ic_map_plus);
                    ivMinus.setEnabled(true);
                    ivMinus.setImageResource(R.drawable.ic_map_minus);
                }
                break;
            case R.id.iv_minus:
                printLogd("min======" + minZoomLevel);
                if (mCurrentZoomLevel < 7) {
                    ivMinus.setEnabled(false);
                    ivMinus.setImageResource(R.drawable.ic_map_minus_gray);
                } else {
                    mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomOut());
                    ivPlus.setEnabled(true);
                    ivPlus.setImageResource(R.drawable.ic_map_plus);
                    ivMinus.setEnabled(true);
                    ivMinus.setImageResource(R.drawable.ic_map_minus);
                }
                break;
            case R.id.ll_traffic:
                if (!isTrafficOpen) {
                    showToast("实时路况打开");
                    mBaiduMap.setTrafficEnabled(true);
                    ivTraffic.setImageResource(R.drawable.ic_map_traffic_open);
                    isTrafficOpen = !isTrafficOpen;
                } else {
                    showToast("实时路况关闭");
                    mBaiduMap.setTrafficEnabled(false);
                    ivTraffic.setImageResource(R.drawable.ic_map_traffic_close);
                    isTrafficOpen = !isTrafficOpen;
                }
                break;
            case R.id.iv_view:
                if (!isViewcSwitch) {
                    showToast("切换卫星视图");
                    mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                    isViewcSwitch = !isViewcSwitch;
                } else {
                    showToast("卫星视图关闭");
                    mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                    isViewcSwitch = !isViewcSwitch;

                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        //定义Maker坐标点
//        LatLng point = new LatLng(39.963175, 116.400244);
////构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.ic_map_click_location);
//构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(latLng)
                .icon(bitmap);
        mBaiduMap.clear();//清除之前的marker
//在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);


        //实例化一个地理编码查询对象
        GeoCoder geoCoder = GeoCoder.newInstance();
        //设置反地理编码位置坐标
        ReverseGeoCodeOption op = new ReverseGeoCodeOption();
        op.location(latLng);
        //发起反地理编码请求(经纬度->地址信息)
        geoCoder.reverseGeoCode(op);
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult arg0) {
                //获取点击的坐标地址
                String address = arg0.getAddress();
                Log.e("[address]====", address);
            }

            @Override
            public void onGetGeoCodeResult(GeoCodeResult arg0) {
            }
        });
//        double m = calculate(myLatLng, point);
//        Log.e("距离距离：", m + "");

    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        return false;
    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || bdMapView == null) {
                return;
            }
            mCurrentLat = location.getLatitude();
            mCurrentLon = location.getLongitude();
            mCurrentAccracy = location.getRadius();
            locData = new MyLocationData.Builder()
                    .accuracy(300)
//                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(15.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }
}
