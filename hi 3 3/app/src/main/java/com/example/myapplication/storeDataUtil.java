package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class storeDataUtil {
    public static List<StoreData> initializeMapDataList() {
        List<StoreData> storeDataList = new ArrayList<>();
        //편의점 주차장 atm 프린터

        storeDataList.add(new StoreData("입구","영남대학교 정문","영남대학교 정문",35.8360578,128.7529371));
        storeDataList.add(new StoreData("입구","영남대학교 서문","영남대학교 서문",35.831250,128.750168));
        storeDataList.add(new StoreData("입구","영남대학교 동문","영남대학교 동문",35.836777,128.764684));

        /*
        storeDataList.add(new StoreData("편의점","GS25 영남대 국제교류관점","GS25 영남대 국제교류관점",35.8369393,128.7546148));
        storeDataList.add(new StoreData("편의점","세븐일레븐 영남대 자연대점","세븐일레븐 영남대 자연대점",35.828296,128.7566318));
        storeDataList.add(new StoreData("편의점","세븐일레븐 영남대 상경관점","세븐일레븐 영남대 상경관점",35.832599,128.7557202));
        storeDataList.add(new StoreData("편의점","세븐일레븐 영남대 생활관점","세븐일레븐 영남대 생활관점",35.828296,128.7566318));
        storeDataList.add(new StoreData("편의점","GS25 영남대 학생회관점","GS25 영남대 학생회관점",35.829558,128.7488722));
*/
        storeDataList.add(new StoreData("주차장","동문 무료 주차장","동문 무료 주차장",35.8357336,128.7623657));
        storeDataList.add(new StoreData("주차장","서문 무료 주차장","서문 무료 주차장",35.826014,128.753022));

        return storeDataList;
    }

}
