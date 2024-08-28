package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    private MaterialCalendarView calendarView;
    private List<scheduleData> scheduleList;
    private TextView scheduleTextView, scheduleTextView2;
    private Button button, button2;
    private TextView tv_date;
    private String userEmail;

    private SQLiteDatabase db;
    private MemoDB dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        setTitle("YU 캘린더");

        dbHelper = new MemoDB(this);

        db = dbHelper.getReadableDatabase();

        scheduleList = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        userEmail = sharedPreferences.getString("email", "");


        calendarView = findViewById(R.id.calendar);
        scheduleTextView = findViewById(R.id.scheduleTextView); // 추가
        scheduleTextView.setVisibility(View.GONE);
        scheduleTextView2 = findViewById(R.id.scheduleTextView2) ;// 추가
        scheduleTextView2.setVisibility(View.GONE);

        tv_date = findViewById(R.id.tv_date);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        calendarView.addDecorators(new SundayDecorator(), new SaturdayDecorator(), new OneDayDecorator());
        calendarView.addDecorators(
                new MySelectorDecorator(this)
        );

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent project1 = new Intent(CalendarActivity.this, InsertActivity.class);
                project1.putExtra("selectedDate", tv_date.getText().toString()); // Attach the selected date
                startActivity(project1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalendarActivity.this, MainActivity_memo.class);
                startActivity(intent);
            }
        });
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int year = date.getYear();
                int month = date.getMonth() + 1; // 월은 0부터 시작하므로 1을 더해줌
                int dayOfMonth = date.getDay();

                String selectedDate = year + "년 " + month + "월 " + dayOfMonth + "일 선택";
                tv_date.setText(selectedDate);
                scheduleTextView.setVisibility(View.VISIBLE);
                scheduleTextView2.setVisibility(View.VISIBLE);
                showSchedule2(date);
                showSchedule(date);
            }
        });
        Calendar calendar = Calendar.getInstance();


        calendar.set(2023, Calendar.MAY, 23); // 년, 월(0부터 시작), 일
        Date date3 = calendar.getTime();
        addSchedule(date3, "축제 1일차, 수업일수(3/4)");

        calendar.set(2023, Calendar.MAY, 24); // 년, 월(0부터 시작), 일
        Date date4 = calendar.getTime();
        addSchedule(date4, "축제 2일차");

        calendar.set(2023, Calendar.MAY, 25); // 년, 월(0부터 시작), 일
        Date date5 = calendar.getTime();
        addSchedule(date5, "축제 3일차");

        calendar.set(2023, Calendar.FEBRUARY, 6); // 년, 월(0부터 시작), 일
        Date date6 = calendar.getTime();
        addSchedule(date6, "추가재입학원서접수");

        calendar.set(2023, Calendar.FEBRUARY, 7); // 년, 월(0부터 시작), 일
        Date date7 = calendar.getTime();
        addSchedule(date7, "추가재입학원서접수");


        calendar.set(2023, Calendar.FEBRUARY, 8); // 년, 월(0부터 시작), 일
        Date date8 = calendar.getTime();
        addSchedule(date8, "추가재입학원서접수");

        calendar.set(2023, Calendar.FEBRUARY, 14); // 년, 월(0부터 시작), 일
        Date date9 = calendar.getTime();
        addSchedule(date9, "수강신청 (2/14 ~ 2/16)");

        calendar.set(2023, Calendar.FEBRUARY, 15); // 년, 월(0부터 시작), 일
        Date date10 = calendar.getTime();
        addSchedule(date10, "수강신청 (2/14 ~ 2/16)");

        calendar.set(2023, Calendar.FEBRUARY, 16); // 년, 월(0부터 시작), 일
        Date date11 = calendar.getTime();
        addSchedule(date11, "수강신청 (2/14 ~ 2/16)");

        calendar.set(2023, Calendar.FEBRUARY, 20); // 년, 월(0부터 시작), 일
        Date date12 = calendar.getTime();
        addSchedule(date12, "등록기간 (2/20 ~ 2/21)");

        calendar.set(2023, Calendar.FEBRUARY, 21); // 년, 월(0부터 시작), 일
        Date date13 = calendar.getTime();
        addSchedule(date13, "등록기간 (2/20 ~ 2/21)");

        calendar.set(2023, Calendar.FEBRUARY, 22); // 년, 월(0부터 시작), 일
        Date date14 = calendar.getTime();
        addSchedule(date14, "등록기간 (2/20 ~ 2/21)");

        calendar.set(2023, Calendar.FEBRUARY, 24); // 년, 월(0부터 시작), 일
        Date date15 = calendar.getTime();
        addSchedule(date15, "추가복학원서접수 (2/24 ~ 2/28)");

        calendar.set(2023, Calendar.FEBRUARY, 25); // 년, 월(0부터 시작), 일
        Date date16 = calendar.getTime();
        addSchedule(date16, "추가복학원서접수 (2/24 ~ 2/28)");

        calendar.set(2023, Calendar.FEBRUARY, 26); // 년, 월(0부터 시작), 일
        Date date17 = calendar.getTime();
        addSchedule(date17, "추가복학원서접수 (2/24 ~ 2/28)");

        calendar.set(2023, Calendar.FEBRUARY, 27); // 년, 월(0부터 시작), 일
        Date date18 = calendar.getTime();
        addSchedule(date18, "추가복학원서접수 (2/24 ~ 2/28)");

        calendar.set(2023, Calendar.FEBRUARY, 28); // 년, 월(0부터 시작), 일
        Date date19 = calendar.getTime();
        addSchedule(date19, "추가복학원서접수 (2/24 ~ 2/28), 입학식");

        calendar.set(2023, Calendar.MARCH, 1); // 년, 월(0부터 시작), 일
        Date date20 = calendar.getTime();
        addSchedule(date20, "학기개시일");

        calendar.set(2023, Calendar.MARCH, 2); // 년, 월(0부터 시작), 일
        Date date21 = calendar.getTime();
        addSchedule(date21, "개강");

        calendar.set(2023, Calendar.MARCH, 24); // 년, 월(0부터 시작), 일
        Date date22= calendar.getTime();
        addSchedule(date22, "추가휴학원서접수 (3/24 ~ 3/27)");

        calendar.set(2023, Calendar.MARCH, 25); // 년, 월(0부터 시작), 일
        Date date23= calendar.getTime();
        addSchedule(date23, "추가휴학원서접수 (3/24 ~ 3/27)");

        calendar.set(2023, Calendar.MARCH, 26); // 년, 월(0부터 시작), 일
        Date date24= calendar.getTime();
        addSchedule(date24, "추가휴학원서접수 (3/24 ~ 3/27)");

        calendar.set(2023, Calendar.MARCH, 27); // 년, 월(0부터 시작), 일
        Date date25= calendar.getTime();
        addSchedule(date25, "추가휴학원서접수 (3/24 ~ 3/27)");

        calendar.set(2023, Calendar.MARCH, 28); // 년, 월(0부터 시작), 일
        Date date26= calendar.getTime();
        addSchedule(date26, "수업일수(1/4)");

        calendar.set(2023, Calendar.MARCH, 30); // 년, 월(0부터 시작), 일
        Date date27= calendar.getTime();
        addSchedule(date27, "학기개시일(30일)");

        calendar.set(2023, Calendar.APRIL, 20); // 년, 월(0부터 시작), 일
        Date date28= calendar.getTime();
        addSchedule(date28, "중간시험 (4/20 ~ 4/26)");


        calendar.set(2023, Calendar.APRIL, 21); // 년, 월(0부터 시작), 일
        Date date29= calendar.getTime();
        addSchedule(date29, "중간시험 (4/20 ~ 4/26)");

        calendar.set(2023, Calendar.APRIL, 22); // 년, 월(0부터 시작), 일
        Date date30= calendar.getTime();
        addSchedule(date30, "중간시험 (4/20 ~ 4/26)");


        calendar.set(2023, Calendar.APRIL, 23); // 년, 월(0부터 시작), 일
        Date date31= calendar.getTime();
        addSchedule(date31, "중간시험 (4/20 ~ 4/26)");


        calendar.set(2023, Calendar.APRIL, 24); // 년, 월(0부터 시작), 일
        Date date32= calendar.getTime();
        addSchedule(date32, "중간시험 (4/20 ~ 4/26), 수업일수(1/2)");

        calendar.set(2023, Calendar.APRIL, 25); // 년, 월(0부터 시작), 일
        Date date33= calendar.getTime();
        addSchedule(date33, "중간시험 (4/20 ~ 4/26)");

        calendar.set(2023, Calendar.APRIL, 26); // 년, 월(0부터 시작), 일
        Date date34= calendar.getTime();
        addSchedule(date34, "중간시험 (4/20 ~ 4/26)");

        calendar.set(2023, Calendar.APRIL, 29); // 년, 월(0부터 시작), 일
        Date date35= calendar.getTime();
        addSchedule(date35, "학기개시일(60일)");

        calendar.set(2023, Calendar.MAY, 29); // 년, 월(0부터 시작), 일
        Date date36= calendar.getTime();
        addSchedule(date36, "학기개시일(90일)");

        calendar.set(2023, Calendar.MAY, 1); // 년, 월(0부터 시작), 일
        Date date37= calendar.getTime();
        addSchedule(date37, "교육실습 시작 (5/1~5/26)");

        calendar.set(2023, Calendar.MAY, 26); // 년, 월(0부터 시작), 일
        Date date38= calendar.getTime();
        addSchedule(date38, "교육실습 종료 (5/1~5/26)");

        calendar.set(2023, Calendar.JUNE, 8); // 년, 월(0부터 시작), 일
        Date date39= calendar.getTime();
        addSchedule(date39, "공휴일수업대체지정일 (5/1(월) 근로자의날)");

        calendar.set(2023, Calendar.JUNE, 9); // 년, 월(0부터 시작), 일
        Date date40= calendar.getTime();
        addSchedule(date40, "공휴일수업대체지정일 (5/5(금) 어린이날)");

        calendar.set(2023, Calendar.JUNE, 12); // 년, 월(0부터 시작), 일
        Date date41= calendar.getTime();
        addSchedule(date41, "공휴일수업대체지정일 (6/6(화) 현충일)");

        calendar.set(2023, Calendar.JUNE, 13); // 년, 월(0부터 시작), 일
        Date date42= calendar.getTime();
        addSchedule(date42, "공휴일수업대체지정일 (5/29(월) 부처님오신날 대체공휴일)");

        calendar.set(2023, Calendar.JUNE, 14); // 년, 월(0부터 시작), 일
        Date date44= calendar.getTime();
        addSchedule(date44, "기말시험 (6/14 ~ 6/20)");

        calendar.set(2023, Calendar.JUNE, 15); // 년, 월(0부터 시작), 일
        Date date45= calendar.getTime();
        addSchedule(date45, "기말시험 (6/14 ~ 6/20)");

        calendar.set(2023, Calendar.JUNE, 16); // 년, 월(0부터 시작), 일
        Date date46= calendar.getTime();
        addSchedule(date46, "기말시험 (6/14 ~ 6/20)");

        calendar.set(2023, Calendar.JUNE, 18); // 년, 월(0부터 시작), 일
        Date date47= calendar.getTime();
        addSchedule(date47, "기말시험 (6/14 ~ 6/20)");

        calendar.set(2023, Calendar.JUNE, 19); // 년, 월(0부터 시작), 일
        Date date48= calendar.getTime();
        addSchedule(date48, "기말시험 (6/14 ~ 6/20)");

        calendar.set(2023, Calendar.JUNE, 20); // 년, 월(0부터 시작), 일
        Date date49= calendar.getTime();
        addSchedule(date49, "기말시험 (6/14 ~ 6/20)");

        calendar.set(2023, Calendar.JUNE, 21); // 년, 월(0부터 시작), 일
        Date date50= calendar.getTime();
        addSchedule(date50, "방학");

        calendar.set(2023, Calendar.JULY, 5); // 년, 월(0부터 시작), 일
        Date date51= calendar.getTime();
        addSchedule(date51, "복학 재입학원서접수");

        calendar.set(2023, Calendar.JULY, 6); // 년, 월(0부터 시작), 일
        Date date52= calendar.getTime();
        addSchedule(date52, "휴학원서접수");

        calendar.set(2023, Calendar.JULY, 7); // 년, 월(0부터 시작), 일
        Date date53= calendar.getTime();
        addSchedule(date53, "휴학원서접수");

        calendar.set(2023, Calendar.AUGUST, 16); // 년, 월(0부터 시작), 일
        Date date54= calendar.getTime();
        addSchedule(date54, "수강신청 (8/16 ~ 8/18)");

        calendar.set(2023, Calendar.AUGUST, 17); // 년, 월(0부터 시작), 일
        Date date55= calendar.getTime();
        addSchedule(date55, "수강신청 (8/16 ~ 8/18)");

        calendar.set(2023, Calendar.AUGUST, 18); // 년, 월(0부터 시작), 일
        Date date56= calendar.getTime();
        addSchedule(date56, "수강신청 (8/16 ~ 8/18)");

        calendar.set(2023, Calendar.AUGUST, 22); // 년, 월(0부터 시작), 일
        Date date57= calendar.getTime();
        addSchedule(date57, "학위수여식");

        calendar.set(2023, Calendar.AUGUST, 23); // 년, 월(0부터 시작), 일
        Date date58= calendar.getTime();
        addSchedule(date58, "학위수여식");

        calendar.set(2023, Calendar.AUGUST, 23); // 년, 월(0부터 시작), 일
        Date date59= calendar.getTime();
        addSchedule(date59, "등록기간 (8/22 ~ 8/24)");

        calendar.set(2023, Calendar.AUGUST, 24); // 년, 월(0부터 시작), 일
        Date date60= calendar.getTime();
        addSchedule(date60, "등록기간 (8/22 ~ 8/24)");

        calendar.set(2023, Calendar.SEPTEMBER, 1); // 년, 월(0부터 시작), 일
        Date date61= calendar.getTime();
        addSchedule(date61, "개강");

        calendar.set(2023, Calendar.SEPTEMBER, 27); // 년, 월(0부터 시작), 일
        Date date62= calendar.getTime();
        addSchedule(date62, "수업일수 (1/4)");

        calendar.set(2023, Calendar.SEPTEMBER, 30); // 년, 월(0부터 시작), 일
        Date date63= calendar.getTime();
        addSchedule(date63, "학기개시일(30일)");

        calendar.set(2023, Calendar.OCTOBER, 20); // 년, 월(0부터 시작), 일
        Date date64= calendar.getTime();
        addSchedule(date64, "중간시험 (10/20 ~ 10/26)");

        calendar.set(2023, Calendar.OCTOBER, 21); // 년, 월(0부터 시작), 일
        Date date65= calendar.getTime();
        addSchedule(date65, "중간시험 (10/20 ~ 10/26)");

        calendar.set(2023, Calendar.OCTOBER, 22); // 년, 월(0부터 시작), 일
        Date date66= calendar.getTime();
        addSchedule(date66, "중간시험 (10/20 ~ 10/26)");

        calendar.set(2023, Calendar.OCTOBER, 23); // 년, 월(0부터 시작), 일
        Date date67= calendar.getTime();
        addSchedule(date67, "중간시험 (10/20 ~ 10/26)");

        calendar.set(2023, Calendar.OCTOBER, 24); // 년, 월(0부터 시작), 일
        Date date68= calendar.getTime();
        addSchedule(date68, "중간시험 (10/20 ~ 10/26)");

        calendar.set(2023, Calendar.OCTOBER, 25); // 년, 월(0부터 시작), 일
        Date date69= calendar.getTime();
        addSchedule(date69, "중간시험 (10/20 ~ 10/26)");

        calendar.set(2023, Calendar.OCTOBER, 26); // 년, 월(0부터 시작), 일
        Date date70= calendar.getTime();
        addSchedule(date70, "중간시험 (10/20 ~ 10/26)");

        calendar.set(2023, Calendar.OCTOBER, 30); // 년, 월(0부터 시작), 일
        Date date71= calendar.getTime();
        addSchedule(date71, "수업(1/2), 일수학기개시일 (60일)");

        calendar.set(2023, Calendar.NOVEMBER, 24); // 년, 월(0부터 시작), 일
        Date date72= calendar.getTime();
        addSchedule(date72, "수업일수 (3/4)");

        calendar.set(2023, Calendar.NOVEMBER, 29); // 년, 월(0부터 시작), 일
        Date date73= calendar.getTime();
        addSchedule(date73, "학기개시일 (90일)");

        calendar.set(2023, Calendar.DECEMBER, 8); // 년, 월(0부터 시작), 일
        Date date74= calendar.getTime();
        addSchedule(date74, "공휴일수업대체지정일 (9/28(목) 추석연휴일)");

        calendar.set(2023, Calendar.DECEMBER, 11); // 년, 월(0부터 시작), 일
        Date date75= calendar.getTime();
        addSchedule(date75, "공휴일수업대체지정일 (9/29(금) 추석연휴일)");

        calendar.set(2023, Calendar.DECEMBER, 12); // 년, 월(0부터 시작), 일
        Date date76= calendar.getTime();
        addSchedule(date76, "공휴일수업대체지정일 (10/3(화) 개천절)");

        calendar.set(2023, Calendar.DECEMBER, 13); // 년, 월(0부터 시작), 일
        Date date77= calendar.getTime();
        addSchedule(date77, "공휴일수업대체지정일 (10/9(월) 한글날)");

        calendar.set(2023, Calendar.DECEMBER, 14); // 년, 월(0부터 시작), 일
        Date date78= calendar.getTime();
        addSchedule(date78, "기말 시험 (12/14 ~ 12/20)");

        calendar.set(2023, Calendar.DECEMBER, 15); // 년, 월(0부터 시작), 일
        Date date79= calendar.getTime();
        addSchedule(date79, "기말 시험 (12/14 ~ 12/20)");

        calendar.set(2023, Calendar.DECEMBER, 16); // 년, 월(0부터 시작), 일
        Date date80= calendar.getTime();
        addSchedule(date80, "기말 시험 (12/14 ~ 12/20)");

        calendar.set(2023, Calendar.DECEMBER, 17); // 년, 월(0부터 시작), 일
        Date date81= calendar.getTime();
        addSchedule(date81, "기말 시험 (12/14 ~ 12/20)");

        calendar.set(2023, Calendar.DECEMBER, 18); // 년, 월(0부터 시작), 일
        Date date82= calendar.getTime();
        addSchedule(date82, "기말 시험 (12/14 ~ 12/20)");

        calendar.set(2023, Calendar.DECEMBER, 19); // 년, 월(0부터 시작), 일
        Date date83= calendar.getTime();
        addSchedule(date83, "기말 시험 (12/14 ~ 12/20)");

        calendar.set(2023, Calendar.DECEMBER, 20); // 년, 월(0부터 시작), 일
        Date date84= calendar.getTime();
        addSchedule(date84, "기말 시험 (12/14 ~ 12/20)");

        calendar.set(2023, Calendar.DECEMBER, 21); // 년, 월(0부터 시작), 일
        Date date85= calendar.getTime();
        addSchedule(date85, "방학");

        calendar.set(2023, Calendar.DECEMBER, 22); // 년, 월(0부터 시작), 일
        Date date86= calendar.getTime();
        addSchedule(date86, "개교기념일");

        calendar.set(2024, Calendar.JANUARY, 8); // 년, 월(0부터 시작), 일
        Date date87= calendar.getTime();
        addSchedule(date87, "복학 재입학원서 접수 (1/8 ~ 1/10)");

        calendar.set(2024, Calendar.JANUARY, 9); // 년, 월(0부터 시작), 일
        Date date88= calendar.getTime();
        addSchedule(date88, "복학 재입학원서 접수 (1/8 ~ 1/10)");

        calendar.set(2024, Calendar.JANUARY, 10); // 년, 월(0부터 시작), 일
        Date date89= calendar.getTime();
        addSchedule(date89, "복학 재입학원서 접수 (1/8 ~ 1/10)");


        decorateCalendar();
        decorateCalendar2();

    }

    private void addSchedule(Date date, String detail) {
        scheduleData schedule = new scheduleData(date, detail);
        scheduleList.add(schedule);
    }

    private void showSchedule2(CalendarDay date) {
        List<scheduleData> selectedSchedules = new ArrayList<>();
        List<scheduleData> adminSchedules = new ArrayList<>();

        // 1. 사용자가 추가한 일정 확인
        for (scheduleData schedule : scheduleList) {
            if (isSameDate(schedule.getDate(), date.getDate())) {
                selectedSchedules.add(schedule);
            }
        }

        // 2. 관리자가 추가한 일정 확인
        String dateKey = date.getYear() + "/" + (date.getMonth() + 1) + "/" + date.getDay();
        Cursor cursor = db.rawQuery("SELECT content FROM memo WHERE user_email = ? AND wdate = ?", new String[]{"admin@admin.com", dateKey});
        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("content");
            if (columnIndex != -1) {
                String eventDetails = cursor.getString(columnIndex);
                adminSchedules.add(new scheduleData(date.getDate(), eventDetails));
            }
        }

        // 3. 일정 표시
        if (!selectedSchedules.isEmpty() || !adminSchedules.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();

            // 사용자가 추가한 일정 표시
            for (scheduleData schedule : selectedSchedules) {
                stringBuilder.append(schedule.getDetail()).append("\n");
            }

            // 관리자가 추가한 일정 표시
            for (scheduleData schedule : adminSchedules) {
                stringBuilder.append(schedule.getDetail()).append("\n");
            }

            scheduleTextView.setText(stringBuilder.toString());
            scheduleTextView.setVisibility(View.VISIBLE);
        } else {
            scheduleTextView.setVisibility(View.GONE);
        }
    }

    private void showSchedule(CalendarDay date) {

        String dateKey = date.getYear() + "/" + (date.getMonth() + 1) + "/" + date.getDay();
        Cursor cursor = db.rawQuery("SELECT content FROM memo WHERE user_email = ? AND wdate = ?", new String[]{userEmail, dateKey});
        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("content");
            if (columnIndex != -1) {
                String eventDetails = cursor.getString(columnIndex);
                scheduleTextView2.setText(eventDetails);
                scheduleTextView2.setVisibility(View.VISIBLE);
            }
        } else {
            scheduleTextView2.setVisibility(View.GONE);
        }
    }


    private boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }


    private void decorateCalendar() {
        calendarView.addDecorator(new DayViewDecorator() {
            @Override
            public boolean shouldDecorate(CalendarDay day) {
                String dateKey = day.getYear() + "/" + (day.getMonth() + 1) + "/" + day.getDay();
                Cursor cursor = db.rawQuery("SELECT content FROM memo WHERE user_email = ? AND wdate = ?", new String[]{userEmail, dateKey});
                return cursor.moveToFirst();
            }

            @Override
            public void decorate(DayViewFacade view) {
                view.setSelectionDrawable(getResources().getDrawable(R.drawable.custom_date_selector));

            }
        });
    }

    private void decorateCalendar2() {
        calendarView.addDecorator(new DayViewDecorator() {
            @Override
            public boolean shouldDecorate(CalendarDay day) {
                String dateKey = day.getYear() + "/" + (day.getMonth() + 1) + "/" + day.getDay();

                // 사용자가 추가한 일정 확인
                boolean userSchedule = false;
                for (scheduleData schedule : scheduleList) {
                    if (isSameDate(schedule.getDate(), day.getDate())) {
                        userSchedule = true;
                        break;
                    }
                }

                // 관리자가 추가한 일정 확인
                Cursor cursor = db.rawQuery("SELECT content FROM memo WHERE user_email = ? AND wdate = ?", new String[]{"admin@admin.com", dateKey});
                boolean adminSchedule = cursor.moveToFirst();

                // 사용자 또는 관리자가 추가한 일정이 있는 경우에만 데코레이션 적용
                return userSchedule || adminSchedule;
            }

            @Override
            public void decorate(DayViewFacade view) {
                // 기존 데코레이션 적용 (사용자가 추가한 일정)
                view.addSpan(new DotSpan(5, Color.RED));
            }
        });
        Log.d("Decorated Dates", calendarView.getCurrentDate().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        decorateCalendar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.map_menu_2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        int id = item.getItemId();

        if (id == R.id.back_btn) { //map
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        return true;
    }
}