package com.example.myapplication;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.maps.android.SphericalUtil;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class SchoolMapActivity extends AppCompatActivity
        implements OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback {

    //추가함
    private boolean isTracking = false; // 추적 여부를 나타내는 변수
    private Polyline currentPolyline; // Polyline을 저장할 변수
    private Marker currentMarker = null;
    private static final int GPS_ENABLE_REQUEST_CODE = 3001;
    private static final int UPDATE_INTERVAL_MS = 3000;  // 1초 //처음에 현재 위치 뜨는 거
    private static final int FASTEST_UPDATE_INTERVAL_MS = 1000000; // //검색한 화면 뜨는 시간 10분동안 뜸
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    // 위치 권한 요청 예시
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 123;
    boolean needRequest = false;

    String[] REQUIRED_PERMISSIONS = {android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    Location mCurrentLocation;
    LatLng currentPosition;
    LatLng previousPosition = null;
    Marker addedMarker = null;
    int tracking = 0;

    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest locationRequest;
    private Location location;
    private View mLayout;
    private static final String TAG = "SchoolMap";
    private boolean walkState = false; //걸음 상태
    private Marker selectedMarker = null; // 선택된 마커를 추적하기 위한 변수
    private boolean trackingStarted = false;
    private Polyline userPolyline;
    private Marker startMarker;
    private Marker destinationMarker;

//추가함

    private GoogleMap mMap;
    private Button btnKor2Loc, start_btn, arrival_button; //검색 버튼/(길찾기) 시작 버튼/도착지설정 버튼
    private LinearLayout layout_search_line_1, layout_search_line_2, layout_search_line_3;
    private HorizontalScrollView scrollView;
    private AutoCompleteTextView autotext_building, autotext_building_from, autotext_building_to;
    private Button btn_find_route_daijkstra; //경로찾기 버튼
    private TextView text_path, text_meter, btn_before;
    private LatLng startLatLng = new LatLng(0, 0);
    private LatLng endLatLng = new LatLng(0, 0);
    private List<BuildingData> mapDataList;
    private List<StoreData> storeDataList;
    private List<Marker> markersList;
    private List<Marker> markersList2;
    private ArrayList<Polyline> polylines, polylines1;
    private ArrayList<Marker> markers_disabled, markers_building;
    private static final int NODE = 100; // 노드(학교 장소) 갯수
    private static ArrayList<Vertex> vertex = new ArrayList<>(NODE); // vertex 객체배열
    private static final double[] BUILDING_POINTS = {
            35.829943, 128.754348, //0 공대 전기관
            35.830621, 128.754446, //1 공대 it관
            35.835248, 128.757236, //2 미대 디자인관
            35.832045, 128.753259, //3 카페 스타벅스 영남대아트센터점
            35.833042, 128.758056, //4 카페 스타벅스 영남대 중앙도서관
            35.831693, 128.760208, //5 카페 그라찌에 외국어교육원점
            35.825869, 128.756282, //6 카페 아이엔지
            35.829025, 128.756918, //7 이종우 과학 도서관
            35.833056, 128.757961, //8 중앙도서관
            35.828366, 128.756240, //9 자연계식당
            35.834135, 128.756936, //10 학생회관 식당
            35.834135, 128.756936, //11 인문계 식당
            35.828296, 128.756631, //12 세븐일레븐 자연대점
            35.834004, 128.757223, //13 GS25 학생회관점
            35.835733, 128.762365, //14 동문 무료 주차장
            35.831875, 128.758512, //15 인문관
            35.833611, 128.756451, //16 사회과학관
            35.829423, 128.754422, //17 섬유관
            35.828932, 128.754134, //18 화공관
            35.827121, 128.754094,// 19 소재관
            35.826610, 128.754042,// 20 기계관
            35.829819, 128.755421,// 21 건축관
            35.828846, 128.755671,// 22 건설관
            35.830179, 128.757570,// 23 제1과학관
            35.829435, 128.757609,// 24 제2과학관
            35.825446, 128.756199,// 25 로봇관
            35.825000, 128.754955,// 26 자동차관
            35.826434, 128.754122,// 27 기계관복사실
            35.828119, 128.759967,// 28 법학전문도서관복사실
            35.832804, 128.755958,// 29 상경관복사실
            35.834227, 128.759139,// 30 사범대학복사실
            35.828908, 128.757102,// 31 이종우과학도서관복사실
            35.831915, 128.758514,// 32 인문관복사실
            35.829025, 128.756918,// 33 전기관복사실
            35.838013, 128.756122,// 35 a16
            35.838013, 128.756122,// 36 a02
            35.838013, 128.756122,//37 a17
            35.836478, 128.756331,//38 a04
            35.835256, 128.756100,//39 a05
            35.834719, 128.758334,//40 a07
            35.834253, 128.759054,//41 a08
            35.834701, 128.760138,//42 a09
            35.834876, 128.761409,//43 a10
            35.835311, 128.761269,//44 a11
            35.836044, 128.763177,//45 a12
            35.835088, 128.760832,//46 a13
            35.836252, 128.760670,//47 a14
            35.835247, 128.759513,//48 a24
            35.836483, 128.759358,//49 a27
            35.836311, 128.762846,//50 a29
            35.829988, 128.761372,//51 c01
            35.831820, 128.759985,//52 c02
            35.832382, 128.760025,//53 c03
            35.834053, 128.761805,//54 c07
            35.833995, 128.761898,//55 c06
            35.833624, 128.763528,//56 c31
            35.832987, 128.762435,//57 c28
            35.831860, 128.762476,//58 c24
            35.832123, 128.762072,//59 c25
            35.832225, 128.761721,//60 c26
            35.832446, 128.761211,//61 c27
            35.829955, 128.761776,//62 c21
            35.832009, 128.753003,//63 e02
            35.830851, 128.752915,//64 e04
            35.829332, 128.755731,//65 f04
            35.829501, 128.755287,//66 f05
            35.829271, 128.755276,//67 f06
            35.829667, 128.756851,//68 f23
            35.828055, 128.756195,//69 f26
            35.827690, 128.756774,//70 f27
            35.827286, 128.756975,//71 f28
            35.827710, 128.755397,//72 f29
            35.829314, 128.758779,//73 g01
            35.829103, 128.759649,//74 g02
            35.827713, 128.757980,//75 g07
            35.826298, 128.756760,//76 g11
            35.825994, 128.755989,//77 g12
            35.825000, 128.754955,//78 g29
            35.825539, 128.755393,//79 g14
            35.825553, 128.753800,//80 g15
            35.824986, 128.754379,//81 g17
            35.824700, 128.753864,//82 g19
            35.828335, 128.761206,//83 민속촌
            35.828382, 128.759373,//84 g04
            35.832389, 128.753129,//85 은하정
            35.836057, 128.752937,//86 정문
            35.831250, 128.750168,//87 서문
            35.836777, 128.764684,//88 동문
            35.836939, 128.754614,//89 gs25국제교류관점
            35.832599, 128.755720,//90 세븐일레븐상경관점
            35.829424, 128.748904,//91 세븐일레븐생활관점
            35.826014, 128.753022,//92 공대무료주차장
            35.830283, 128.752903,// 93 e05
            35.834131, 128.755095,// 94 b01
            35.832779, 128.756012,// 95 b02
            35.834206, 128.756751,// 96 b06
            35.834357, 128.755958,// 97 b07
            35.828100, 128.759848,// 98 법학도서관
            35.830468, 128.748918// 99 생활관

    };
    private static final int BUILDING_POINTS_SIZE = 99 * 2; // 교차로 제외 건물 수 * (위도 + 경도 = 2)
    private static ArrayList<String> BUILDING_NAMES = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_map);
        setTitle("YU 지도");
        /////////
        //추가
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mLayout = findViewById(R.id.map);
        locationRequest = new LocationRequest()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setFastestInterval(FASTEST_UPDATE_INTERVAL_MS);
        LocationSettingsRequest.Builder builder =
                new LocationSettingsRequest.Builder();

        builder.addLocationRequest(locationRequest);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        /////////

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // 리스트 초기화
        markers_disabled = new ArrayList<>();
        markers_building = new ArrayList<>();
        vertex = new ArrayList<>();
        polylines = new ArrayList<>();
        polylines1 = new ArrayList<>();
        // Vertex 초기화
        try {
            setVertex();
        } catch (IOException e) {
            e.printStackTrace();
        }
        layout_search_line_1 = findViewById(R.id.search_line_1);
        layout_search_line_2 = findViewById(R.id.search_line_2);
        layout_search_line_2.setVisibility(View.GONE);
        layout_search_line_3 = findViewById(R.id.search_line_3);
        layout_search_line_3.setVisibility(View.GONE);
        scrollView = findViewById(R.id.layout_scroll);
        autotext_building_from = findViewById(R.id.autotext_building_from);
        autotext_building_to = findViewById(R.id.autotext_building_to);
        btn_find_route_daijkstra = findViewById(R.id.btn_find_route_daijkstra);
        btn_before = findViewById(R.id.before_btn);
        text_meter = findViewById(R.id.text_meter);
        text_path = findViewById(R.id.text_path);
        //추가(길찾기)
        start_btn = findViewById(R.id.start_button);
        start_btn.setVisibility(View.GONE);

        // start_btn 클릭 리스너
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tracking = 1 - tracking;

                if (tracking == 1) {
                    start_btn.setText("길찾기 종료");
                    start_btn.setBackgroundColor(Color.parseColor("#1D4273"));
                    start_btn.setTextColor(Color.parseColor("#FFFFFF"));
                    ClickRouteStartBtn();

                    if (selectedMarker == null) {
                        // 선택된 마커가 없는 경우 메시지 표시
                        Toast.makeText(SchoolMapActivity.this, "도착지 마커를 선택하세요.", Toast.LENGTH_SHORT).show();
                        // 길찾기를 시작하지 않도록 tracking 값을 원래대로 변경
                        tracking = 1 - tracking;
                        start_btn.setText("길찾기 시작");
                        // 버튼 색상 초기화 (기본값)
                        start_btn.setBackgroundColor(Color.parseColor("#CCCCCC"));
                        // 위치 업데이트 중지
                        mFusedLocationClient.removeLocationUpdates(locationCallback);


                    } else {
                        startLocation();

                    }//calculateDistanceAndDrawPolyline();
                } else{
                    start_btn.setText("길찾기 시작");
                    // 위치 업데이트 중지
                    mFusedLocationClient.removeLocationUpdates(locationCallback);
                    Toast.makeText(SchoolMapActivity.this, "길찾기가 종료되었습니다.", Toast.LENGTH_SHORT).show();

                    // 추적을 종료할 때 Polyline과 이전 위치 초기화
                    if (currentPolyline != null) {
                        currentPolyline.remove();
                        currentPolyline = null;
                    }
                    previousPosition = null;

                    initPolylines();
                    HideMarkers();
                    // 버튼 색상 초기화 (기본값)
                    start_btn.setBackgroundColor(Color.parseColor("#CCCCCC"));


                }

            }
        });
        //검색 기능
        autotext_building = findViewById(R.id.autotext_building);
        btnKor2Loc = (Button) findViewById(R.id.button2);
        btnKor2Loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (autotext_building.getText().toString().length() > 0) {
                    String input = autotext_building.getText().toString();
                    showMarkerWithTagOrIdOrName(input);
                }
            }
        });
        Button find_route = findViewById(R.id.btn_find_route);
        find_route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onMapReady(mMap);
                initPolylines();
                ClickFindRouteBtn();
            }
        });
        btn_before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPolylines();
                onMapReady(mMap);
                layout_search_line_2.setVisibility(View.GONE);
                layout_search_line_3.setVisibility(View.GONE);
                start_btn.setVisibility(View.GONE);
                layout_search_line_1.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.VISIBLE);
            }
        });
        btn_find_route_daijkstra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 출발지랑 도착지 넣는 텍스트 뷰에서 값 가져오기
                int start = convert(autotext_building_from.getText().toString()); // 선택한 출발지를 객체배열의 고유번호로 바꿔줍니다.
                int end = convert(autotext_building_to.getText().toString()); // 선택한 도착지를 객체배열의 고유번호로 바꿔줍니다.
                initPolylines();
                if (start == -1 || end == -1) return;

                HideKeyboard();

                String input = autotext_building_from.getText().toString();
                String input2 = autotext_building_to.getText().toString();
                showMarkerWithTagOrIdOrName2(input, input2);

                Daijkstra path = Daijkstra.getInstance(start, end); // 다익스트라 singleton 객체 Path를 생성합니다.
                try {
                    setVertex(); // vertex 초기화
                    path.calDaijkstra(getApplicationContext(), vertex); // 경로 계산
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                String[] pathNode = path.getPathNode().split(" ", 0); // 계산된 경로(String)을 연산을 위해 배열로 만듭니다.

                // 캠퍼스지도상에 그릴 polyLine객체를 pathNode를 토대로 생성합니다.
                for (int i = 1; i < pathNode.length; i++) {
                    int preVertexNum = Integer.parseInt(pathNode[i - 1]);
                    int postVertexNum = Integer.parseInt(pathNode[i]);
                    // polyline 그리는 코드
                    polylines.add(mMap.addPolyline((new PolylineOptions()).add(new LatLng(vertex.get(preVertexNum).latitude, vertex.get(preVertexNum).longitude),
                            new LatLng(vertex.get(postVertexNum).latitude, vertex.get(postVertexNum).longitude)).width(7).color(Color.RED)));
                }

                //도보시간 계산(추가!)
                int distanceInMeters = path.getMeter(); // 실제 거리(m)로 대체
                int walkingTimeInMinutes = 1; // 100m 미만의 거리에 대한 기본 값

                if (distanceInMeters > 100) {
                    walkingTimeInMinutes = (distanceInMeters - 100) / 50 + 1;
                }

                // 건물 고유숫자로 된 경로를 건물명으로 바꿉니다.
                String pathInfo = "";
                for (int i = 0; i < pathNode.length; i++) {
                    pathInfo += vertex.get(Integer.parseInt(pathNode[i])).tag;
                    if (i == pathNode.length - 1)
                        break;
                    pathInfo += " -> ";
                }
                String walkingTimeText = walkingTimeInMinutes + "분 "; // 도보시간 텍스트 생성
                String meterText = distanceInMeters + "m"; // 거리(m) 텍스트 생성
                text_path.setText(pathInfo); // 경로 표시

                // 거리와 도보시간을 합친 문자열을 생성
                //String finalText = meterText + " " + walkingTimeText;
                //text_meter.setText(finalText); // 거리와 도보시간을 함께 표시
                text_meter.setText("도보 " + walkingTimeText + "(" + path.getMeter() + "m)"); // 거리 표시

                layout_search_line_3.setVisibility(View.VISIBLE);
                start_btn.setVisibility(View.VISIBLE);
            }
        });

        mapDataList = MapDataUtil.initializeMapDataList();
        storeDataList = storeDataUtil.initializeMapDataList();
        markersList = new ArrayList<>();
        markersList2 = new ArrayList<>();

        Button A_Button = findViewById(R.id.A_button);
        Button B_Button = findViewById(R.id.B_button);
        Button C_Button = findViewById(R.id.C_button);
        Button D_Button = findViewById(R.id.D_button);
        Button E_Button = findViewById(R.id.E_button);
        Button F_Button = findViewById(R.id.F_button);
        Button G_Button = findViewById(R.id.G_button);

        Button cafeButton = findViewById(R.id.cafe_button);
        Button libraryButton = findViewById(R.id.library_button);
        Button restaurantButton = findViewById(R.id.restaurant_button);
        Button storeButton = findViewById(R.id.store_button);
        Button parkButton = findViewById(R.id.park_button);
        Button gymButton = findViewById(R.id.gym_button);
        Button printerButton = findViewById(R.id.printer_button);
        Button EntranceButton = findViewById(R.id.entrance_button);
        Button hanokButton = findViewById(R.id.hanok_button);
        Button schoolButton = findViewById(R.id.school_button);
        Button homeButton = findViewById(R.id.home_button);

        A_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMarkerWithId("A");
            }
        });
        B_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMarkerWithId("B");
            }
        });
        C_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMarkerWithId("C");
            }
        });
        D_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMarkerWithId("D");
            }
        });
        E_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMarkerWithId("E");
            }
        });
        F_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMarkerWithId("F");
            }
        });
        G_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMarkerWithId("G");
            }
        });

        cafeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMarkerWithTag("카페");
            }
        });

        libraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMarkerWithTag("도서관");
            }
        });
        restaurantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMarkerWithTag("식당");
            }
        });
        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMarkerWithTag("편의점");
            }
        });
        parkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMarkerWithTag("주차장");
            }
        });
        printerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMarkerWithTag("프린터");
            }
        });
        gymButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMarkerWithTag("체육관");
            }
        });
        EntranceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMarkerWithTag("입구");
            }
        });
        hanokButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMarkerWithTag("민속촌");
            }
        });
        schoolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMarkerWithTag("대학원");
            }
        });
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMarkerWithTag("기숙사");
            }
        });
    }

    private void showMarkerWithTag(String tag) {
        Marker firstVisibleMarker = null;
        for (Marker marker : markersList) {
            BuildingData buildingData = (BuildingData) marker.getTag();
            if (buildingData != null && buildingData.getTag().startsWith(tag)) {
                marker.setVisible(true);
                if (firstVisibleMarker == null) {
                    firstVisibleMarker = marker;
                }
            } else {
                marker.setVisible(false);
            }
        }

        for (Marker marker : markersList2) {
            StoreData storeData = (StoreData) marker.getTag();
            if (storeData != null && storeData.getTag().startsWith(tag)) {
                marker.setVisible(true);
                if (firstVisibleMarker == null) {
                    firstVisibleMarker = marker;
                }
            } else {
                marker.setVisible(false);
            }
        }

        if (firstVisibleMarker != null) {
            // Move the camera to the first visible marker
            LatLng markerPosition = firstVisibleMarker.getPosition();
            mMap.moveCamera(CameraUpdateFactory.newLatLng(markerPosition));
        }
    }

    private void showMarkerWithId(String idPrefix) {
        Marker firstVisibleMarker = null;
        for (Marker marker : markersList) {
            BuildingData buildingData = (BuildingData) marker.getTag();
            if (buildingData != null && buildingData.getId().startsWith(idPrefix)) {
                marker.setVisible(true);
                if (firstVisibleMarker == null) {
                    firstVisibleMarker = marker;
                }
            } else {
                marker.setVisible(false);
            }
        }

        for (Marker marker : markersList2) {
            StoreData storeData = (StoreData) marker.getTag();
            if (storeData != null && storeData.getId().startsWith(idPrefix)) {
                marker.setVisible(true);
                if (firstVisibleMarker == null) {
                    firstVisibleMarker = marker;
                }
            } else {
                marker.setVisible(false);
            }
        }
        if (firstVisibleMarker != null) {
            // Move the camera to the first visible marker
            LatLng markerPosition = firstVisibleMarker.getPosition();
            mMap.moveCamera(CameraUpdateFactory.newLatLng(markerPosition));
        }
    }


    private void showMarkerWithTagOrIdOrName(String input) {
        Marker firstVisibleMarker = null;
        for (Marker marker : markersList) {
            BuildingData buildingData = (BuildingData) marker.getTag();
            if (buildingData != null && (buildingData.getName().contains(input) || buildingData.getTag().contains(input) || buildingData.getId().contains(input))) {
                marker.setVisible(true);
                if (firstVisibleMarker == null) {
                    firstVisibleMarker = marker;
                }
            } else {
                marker.setVisible(false);
            }
        }

        for (Marker marker : markersList2) {
            StoreData storeData = (StoreData) marker.getTag();
            if (storeData != null && (storeData.getName().contains(input) || storeData.getTag().contains(input) || storeData.getId().contains(input))) {
                marker.setVisible(true);
                if (firstVisibleMarker == null) {
                    firstVisibleMarker = marker;
                }
            } else {
                marker.setVisible(false);
            }
        }

        if (firstVisibleMarker != null) {
            // Move the camera to the first visible marker
            LatLng markerPosition = firstVisibleMarker.getPosition();
            mMap.moveCamera(CameraUpdateFactory.newLatLng(markerPosition));
        }
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMinZoomPreference(15); //지도 최소 줌
        mMap.setMaxZoomPreference(18); //지도 최대 줌


        for (BuildingData buildingData : mapDataList) {
            LatLng buildingLatLng = new LatLng(buildingData.getLatitude(), buildingData.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            //팝업 윈도우 화면에 뜨게 설정
            markerOptions.position(buildingLatLng);
            markerOptions.title(buildingData.getId());
            markerOptions.snippet(buildingData.getName());
            if (buildingData.getTag().contains("대학원")) {
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon_school));
            }
            if (buildingData.getTag().contains("민속촌")) {
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon_hanok));
            }
            if (buildingData.getTag().contains("식당")) {
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon_rest));
            }
            if (buildingData.getTag().contains("체육관")) {
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.gym_icon));
            }
            if (buildingData.getTag().contains("도서관")) {
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon_library));
            }
            if (buildingData.getTag().contains("카페")) {
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon_cafe));
            }
            if (buildingData.getId().contains("A") && !buildingData.getTag().contains("체육관")) {
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
            }
            if (buildingData.getId().contains("B") && !buildingData.getTag().contains("식당") && !buildingData.getTag().contains("도서관")) {
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
            }
            if (buildingData.getId().contains("C") & !buildingData.getTag().contains("식당")) {
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
            }
            if (buildingData.getId().contains("D")) {
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            }
            if (buildingData.getId().contains("E") && !buildingData.getTag().contains("체육관") && !buildingData.getTag().contains("식당")) {
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
            }
            if (buildingData.getId().contains("F") && !buildingData.getTag().contains("식당") && !buildingData.getTag().contains("도서관")) {
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            }
            if (buildingData.getId().contains("G")
                    && !buildingData.getTag().contains("도서관") && !buildingData.getTag().contains("대학원")) {
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
            }
            if (buildingData.getTag().contains("프린터")) {
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.printer));
            }

            if (buildingData.getTag().contains("편의점")) {
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon_store));
            }
            if (buildingData.getTag().contains("기숙사") && !buildingData.getTag().contains("편의점")) {
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon_hanok));
            }


            Marker marker = mMap.addMarker(markerOptions);
            marker.setTag(buildingData);
            markersList.add(marker);
        }
        for (StoreData storeData : storeDataList) {
            LatLng buildingLatLng = new LatLng(storeData.getLatitude(), storeData.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(buildingLatLng);
            markerOptions.title(storeData.getId());

            if (storeData.getTag().equals("입구")) {
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.gate_icon));
            }
            if (storeData.getTag().equals("주차장")) {
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon_park));
            }

            Marker marker2 = mMap.addMarker(markerOptions);
            marker2.setTag(storeData);
            markersList2.add(marker2);
        }
        LatLng SCHOOL = new LatLng(35.830621, 128.7592085);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SCHOOL);
        markerOptions.title("영남대학교");
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SCHOOL, 15));

        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(SchoolMapActivity.this));

        //마커 클릭 시 상세정보가 나옴
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // Show custom popup window only for BuildingData
                Object tag = marker.getTag();
                if (tag instanceof BuildingData) {
                    showCustomPopupWindow(marker);
                } else {
                    showCustomPopupWindow_store(marker);
                }
                // 클릭한 마커를 선택된 마커로 설정
                selectedMarker = marker;
                return true;
            }

        });


        // 건물 마커 설정
        BitmapDescriptor bitmap_building = GetBitmapDescriptor(R.drawable.ic_mark_building, 1);
        for (int i = 0; i < BUILDING_POINTS_SIZE; i += 2) {
            Marker m = mMap.addMarker(new MarkerOptions().position(new LatLng(BUILDING_POINTS[i], BUILDING_POINTS[i + 1])));
            m.setIcon(bitmap_building);
            m.setTag(vertex.get(i / 2).name);
        }

    }


    private BitmapDescriptor GetBitmapDescriptor(@DrawableRes int id, int size) {
        Drawable vectorDrawable = ResourcesCompat.getDrawable(getResources(), id, null);

        int width = vectorDrawable.getIntrinsicWidth() * size / 100;
        int height = vectorDrawable.getIntrinsicHeight() * size / 100;

        // Ensure width and height are at least 1
        width = Math.max(1, width);
        height = Math.max(1, height);

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    private Location getLocationFromAddress(Context context, String address) {
        Geocoder geocoder = new Geocoder(context);
        List<Address> addresses;
        Location resLocation = new Location("");
        try {
            addresses = geocoder.getFromLocationName(address, 5);
            if ((addresses == null) || (addresses.size() == 0)) {
                return null;
            }
            Address addressLoc = addresses.get(0);

            resLocation.setLatitude(addressLoc.getLatitude());
            resLocation.setLongitude(addressLoc.getLongitude());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resLocation;
    }

    public void showCurrentLocation(Location location2) {
        LatLng curPoint = new LatLng(location2.getLatitude(), location2.getLongitude());
        String msg = "Latitutde : " + curPoint.latitude
                + "\nLongitude : " + curPoint.longitude;
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        //화면 확대, 숫자가 클수록 확대
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));

        //마커 찍기
        Location targetLocation = new Location("");
        targetLocation.setLatitude(curPoint.latitude);
        targetLocation.setLongitude(curPoint.longitude);
        showMyMarker(targetLocation);

    }

    private void showMyMarker(Location targetLocation) {
        MarkerOptions myMarker = null;
        if (myMarker == null) {

            myMarker = new MarkerOptions();
            myMarker.position(new LatLng(targetLocation.getLatitude(), targetLocation.getLongitude()));
            myMarker.title("검색위치");
            myMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
            mMap.addMarker(myMarker);
        }
    }

    //수정('길찾기'기능에서 현재위치 나타나는 함수)
    private void ClickFindRouteBtn() {
        HideKeyboard();
        HideMarkers();
        // layout_slide.setPanelHeight(0);
        layout_search_line_1.setVisibility(View.GONE);
        scrollView.setVisibility(View.GONE);

        layout_search_line_2.setVisibility(View.VISIBLE);
        start_btn.setVisibility(View.VISIBLE);


    }


    public void setVertex() throws IOException {
        try {
            InputStream is = this.getApplicationContext().getResources().openRawResource(R.raw.vertex);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] st = line.split(" ");
                BUILDING_NAMES.add(st[3]);
                BUILDING_NAMES.add(st[4]);
                BUILDING_NAMES.add(st[5]);
                BUILDING_NAMES.add(st[6]);

                Vertex v = new Vertex(Double.parseDouble(st[0]),
                        Double.parseDouble(st[1]),
                        Integer.parseInt(st[2]),
                        st[3], st[4], st[5], st[6]
                );
                vertex.add(v);

            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }


    private void HideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public int convert(String spot) {
        int result = -1; // 못 찾았을 경우, -1로 반환합니다.

        // 건물이름으로 찾습니다.
        for (int i = 0; i < NODE; i++) {
            if (spot.equals(vertex.get(i).name) | spot.equals(vertex.get(i).tag) | spot.equals(vertex.get(i).tag2) | spot.equals(vertex.get(i).tag3)) {
                return i;
            }
        }
        return result;
    }

    //길찾기 경로 표시 삭제
    public void initPolylines() {
        for (Polyline p : polylines) {
            p.remove();
        }
    }

    private void showCustomPopupWindow(final Marker marker) {
        // Inflate the custom layout
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View customPopupView = inflater.inflate(R.layout.custom_info_window, null);

        // Create the popup window
        final PopupWindow popupWindow = new PopupWindow(customPopupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        // Set the custom InfoWindow contents

        //TextView numberView = customPopupView.findViewById(R.id.number);
        // numberView.setText(marker.getNumber());
        TextView titleView = customPopupView.findViewById(R.id.title);
        titleView.setText(marker.getTitle());
        TextView snippetView = customPopupView.findViewById(R.id.snippet);
        snippetView.setText(marker.getSnippet());
        ImageView imageView = customPopupView.findViewById(R.id.image);
        Button infoButton = customPopupView.findViewById(R.id.info_button);

        //도착지설정 버튼 추가
        Button arrivalButton = customPopupView.findViewById(R.id.arrival_button);
        arrivalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(SchoolMapActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View view1 = inflater.inflate(R.layout.dialog_place_info, null);
                builder.setView(view1);
                final Button button_submit = (Button) view1.findViewById(R.id.button_dialog_placeInfo);
                final Button button_cancle = (Button) view1.findViewById(R.id.button_dialog_cancle);
                final AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                dialog.setCancelable(false);

                button_submit.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        String string_placeTitle = ("도착지 설정 완료").toString();
                        Toast.makeText(SchoolMapActivity.this, string_placeTitle, Toast.LENGTH_SHORT).show();

                        if (selectedMarker != null) {
                            // 선택된 마커의 위치를 가져와서 도착지 마커로 설정
                            LatLng destinationLatLng = selectedMarker.getPosition();
                            MarkerOptions markerOptions = new MarkerOptions();
                            markerOptions.position(destinationLatLng);
                            markerOptions.title("도착지 설정 완료");
                            markerOptions.draggable(true);
                            //도착지 마커 생성됨!

                            // 건물 마커 설정
                            markerOptions.icon(GetBitmapDescriptor(R.drawable.ic_mark_building, 1));
                            addedMarker = mMap.addMarker(markerOptions);

                            dialog.dismiss();
                        } else {
                            // 위치 정보를 가져올 수 없을 때 사용자에게 메시지 표시
                            Toast.makeText(SchoolMapActivity.this, "마커를 먼저 선택하세요.", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }

                        // changeWalkState(); //걸음 상태 변경
                    }
                });
                button_cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        BuildingData selectedBuildingData = new BuildingData();
        boolean isInList = false;
        for (BuildingData buildingData : mapDataList) {
            if (buildingData.getId().equals(marker.getTitle()) && buildingData.getName().equals(marker.getSnippet())) {
                selectedBuildingData = buildingData;
                isInList = true;
                break;
            }
        }

        if (isInList) {
            String imageUrl = selectedBuildingData.getPicture();
            Glide.with(SchoolMapActivity.this)
                    .load(imageUrl)
                    .into(imageView);
        }
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle info button click event
                String markerTitle = marker.getTitle();
                BuildingData selectedBuildingData = new BuildingData();
                boolean isInList = false;
                for (BuildingData buildingData : mapDataList) {
                    if (buildingData.getId().equals(marker.getTitle()) && buildingData.getName().equals(marker.getSnippet())) {
                        selectedBuildingData = buildingData;
                        isInList = true;
                        break;
                    }
                }

                if (isInList) {
                    Intent detailActivity = new Intent(SchoolMapActivity.this, DetailActivity.class);
                    detailActivity.putExtra("id", selectedBuildingData.getId());
                    detailActivity.putExtra("name", selectedBuildingData.getName());
                    detailActivity.putExtra("major", selectedBuildingData.getMajor());
                    detailActivity.putExtra("call", selectedBuildingData.getCall());
                    detailActivity.putExtra("info", selectedBuildingData.getInfo());
                    detailActivity.putExtra("url", selectedBuildingData.getUrl());
                    detailActivity.putExtra("image", selectedBuildingData.getPicture());
                    startActivity(detailActivity);
                }
                popupWindow.dismiss();
            }
        });


        // Show the popup window
        popupWindow.showAtLocation(findViewById(R.id.map), Gravity.CENTER, 0, 0);
    }

    private void showCustomPopupWindow_store(final Marker marker) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View customPopupView = inflater.inflate(R.layout.custom_info_window_store, null);

        // Create the popup window
        final PopupWindow popupWindow = new PopupWindow(customPopupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        // Set the custom InfoWindow contents
        TextView titleView = customPopupView.findViewById(R.id.title);
        titleView.setText(marker.getTitle());
        TextView snippetView = customPopupView.findViewById(R.id.snippet);
        snippetView.setText(marker.getSnippet());

        // Show the popup window
        popupWindow.showAtLocation(findViewById(R.id.map), Gravity.CENTER, 0, 0);

    }


    private void HideMarkers() {
        for (Marker marker : markersList) {
            marker.setVisible(false);
        }

        for (Marker marker : markersList2) {
            marker.setVisible(false);
        }
    }


    private void showMarkerWithTagOrIdOrName2(String input, String input2) {
        Marker firstVisibleMarker = null;
        for (Marker marker : markersList) {
            BuildingData buildingData = (BuildingData) marker.getTag();
            if (buildingData != null && (buildingData.getName().contains(input) || buildingData.getName().contains(input2) || buildingData.getTag().contains(input) || buildingData.getTag().contains(input2) || buildingData.getId().contains(input) || buildingData.getId().contains(input2))) {
                marker.setVisible(true);
                if (firstVisibleMarker == null) {
                    firstVisibleMarker = marker;
                }
            } else {
                marker.setVisible(false);
            }
        }

        for (Marker marker : markersList2) {
            StoreData storeData = (StoreData) marker.getTag();
            if (storeData != null && (storeData.getName().contains(input) || storeData.getName().contains(input2) || storeData.getTag().contains(input) || storeData.getTag().contains(input2) || storeData.getId().contains(input) || storeData.getId().contains(input2))) {
                marker.setVisible(true);
                if (firstVisibleMarker == null) {
                    firstVisibleMarker = marker;
                }
            } else {
                marker.setVisible(false);
            }
        }

        if (firstVisibleMarker != null) {
            // Move the camera to the first visible marker
            LatLng markerPosition = firstVisibleMarker.getPosition();
            mMap.moveCamera(CameraUpdateFactory.newLatLng(markerPosition));
        }
    }

    /////////////////////////////////////////////////
// startLocation 함수
    boolean isArrived = false;

    private void startLocation() {
        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                List<Location> locationList = locationResult.getLocations();

                if (!locationList.isEmpty()) {
                    Location location = locationList.get(locationList.size() - 1);
                    LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());

                    // 이전 위치가 null일 때 현재 위치로 초기화
                    if (previousPosition == null) {
                        previousPosition = currentLatLng;
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 18));
                    }

                    // 현재 위치에서 이전 위치까지 polyline 그리기
                    if (!currentLatLng.equals(previousPosition)) {
                        PolylineOptions options = new PolylineOptions()
                                .add(previousPosition)
                                .add(currentLatLng)
                                .width(15)
                                .color(Color.GREEN)
                                .geodesic(true);
                        polylines.add(mMap.addPolyline(options));
                    }

                    // 이전 위치 업데이트
                    previousPosition = currentLatLng;

                    // 출발지에서 도착지 사이 거리 계산
                    double distance = SphericalUtil.computeDistanceBetween(currentLatLng, addedMarker.getPosition());

                    // 처음 시작 시 50m 이내에 도착했다면 도착 메시지를 표시하고 위치 업데이트 중지
                    if (!isArrived && distance <= 80) {
                        isArrived = true;
                        Toast.makeText(SchoolMapActivity.this, "도착했습니다.", Toast.LENGTH_SHORT).show();
                        //mFusedLocationClient.removeLocationUpdates(this);
                    }

                    // 50m 이내에 도착하지 않았을 때, 50m 이내로 들어오면 메시지 표시
                    if (isArrived && distance > 80) {
                        isArrived = false;
                    }

                    // 거리 표시 (Toast 등을 사용하여 표시)
                    String distanceInMeter = String.format("%.2f", distance) + "m 남음";
                    Toast.makeText(SchoolMapActivity.this, distanceInMeter, Toast.LENGTH_SHORT).show();
                }
            }
        };

        // 위치 권한 확인 및 위치 업데이트 요청
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // 권한이 없는 경우 권한 요청
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSIONS_REQUEST_CODE);
        } else {
            // 권한이 있는 경우 위치 업데이트 요청
            mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
        }
    }

    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);

            List<Location> locationList = locationResult.getLocations();

            if (locationList.size() > 0) {
                location = locationList.get(locationList.size() - 1);

                LatLng previousPosition = currentPosition;
                currentPosition
                        = new LatLng(location.getLatitude(), location.getLongitude());

                if (previousPosition == null) previousPosition = currentPosition;

                String markerTitle = getCurrentAddress(currentPosition);

                //현재 위치에 마커 생성하고 이동
                setCurrentLocation(location, markerTitle);
                mCurrentLocation = location;

            }

        }
    };


    //추가6
    public String getCurrentAddress(LatLng latlng) {

        //지오코더... GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    latlng.latitude,
                    latlng.longitude,
                    1);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(this, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";

        }


        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";

        } else {
            Address address = addresses.get(0);
            return address.getAddressLine(0).toString();
        }

    }

    //추가7
    public void setCurrentLocation(Location location, String markerTitle) {


        if (currentMarker != null) currentMarker.remove();


        LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(currentLatLng);
        markerOptions.title(markerTitle);
        markerOptions.draggable(true);


        currentMarker = mMap.addMarker(markerOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(currentLatLng);
        mMap.moveCamera(cameraUpdate);

    }

    //수정('길찾기'기능에서 현재위치 나타나는 함수)
    private void ClickRouteStartBtn() {
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);

        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {

        } else {


            if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])) {

                Snackbar.make(mLayout, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.",
                        Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {


                        ActivityCompat.requestPermissions(SchoolMapActivity.this, REQUIRED_PERMISSIONS,
                                PERMISSIONS_REQUEST_CODE);
                    }
                }).show();

            } else {
                ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);


            }

        }
        //추가
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);


    }
    /////////////////////////////////////////////////

    public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
        protected final View mWindow;
        private Context mContext;

        public CustomInfoWindowAdapter(Context context) {
            mContext = context;
            mWindow = LayoutInflater.from(context).inflate(R.layout.custom_info_window, null);
        }

        protected void renderWindowText(Marker marker, View view) {

            String title = marker.getTitle();
            TextView titleTextView = (TextView) view.findViewById(R.id.title);

            if (title != null) {
                titleTextView.setText(title);
            }

            String snippet = marker.getSnippet();
            TextView snippetTextView = (TextView) view.findViewById(R.id.snippet);

            if (snippet != null) {
                snippetTextView.setText(snippet);
            }
        }

        @Override
        public View getInfoWindow(Marker marker) {
            renderWindowText(marker, mWindow);
            return mWindow;
        }

        @Override
        public View getInfoContents(Marker marker) {
            renderWindowText(marker, mWindow);
            return mWindow;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.map_menu_1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        int id = item.getItemId();

        if(id==R.id.home_btn){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if (id == R.id.school_btn) { //map
            Intent intent = new Intent(this, SchoolMapActivity.class);
            startActivity(intent);
        }
        if (id == R.id.list_btn) { //map
            Intent intent = new Intent(this, MapListActivity.class);
            startActivity(intent);
        }
        return true;
    }



}