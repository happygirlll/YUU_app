package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class MapDataUtil {
    public static List<BuildingData> initializeMapDataList() {
        List<BuildingData> mapDataList = new ArrayList<>();

        //A
        mapDataList.add(new BuildingData("벤처창업관a16","A16","벤처창업관","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/img-a16-01.jpeg",35.8380137959199,128.756122310121));
        mapDataList.add(new BuildingData("국제교류센터a02","A02","국제교류센터","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/117.jpeg",35.8380137959199,128.756122310121));
        mapDataList.add(new BuildingData("Y-STAR 경산 청년창의창작소a17","A17","Y-STAR 경산 청년창의창작소","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/Y-STAR.jpg",35.8380137959199,128.756122310121));
        mapDataList.add(new BuildingData("박물관a04","A04","박물관","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/017.jpeg",35.83647855390543,128.75633170708156));
        mapDataList.add(new BuildingData("학생지원센터a05","A05","학생지원센터","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/112.jpeg",35.83525641145394,128.75610003620042));
        mapDataList.add(new BuildingData("미술대학예술대학미대예대a06","A06","디자인관","","시각디자인학과,산업디자인학과,생활제품디자인학과","https://www.3dart.it/en/free-sci-fi-space-hdri-360/,https://www.yu.ac.kr/main/academics/id.do,https://www.yu.ac.kr/main/academics/ld.do","053-810-3303,053-810-3298,053-810-3340","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/106.jpeg",35.8352487,128.7572366));
        mapDataList.add(new BuildingData("미술대학예술대학미대예대a07","A07","미술관","","회화과,트랜스아트과","https://painting.yu.ac.kr/painting/index.do,https://transart.yu.ac.kr/transart/index.do","053-810-3320,053-810-3320","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/112.jpeg",35.834719099407316,128.7583346388585));
        mapDataList.add(new BuildingData("사범대학a08","A08","사범대학","","교육학과,국어교육과,영어교육과,한문교육과,수학교육과,유아교육과,특수체육교육과","https://eduwm.yu.ac.kr/eduwm/index.do,https://koredu21.yu.ac.kr/koredu21/index.do,https://dele.yu.ac.kr/dele/index.do,https://eduhankyo.yu.ac.kr/eduhankyo/index.do,https://mathedu.yu.ac.kr/mathedu/index.do,https://child.yu.ac.kr/child/index.do,https://spe.yu.ac.kr/spe/index.do","053-810-3110,053-810-3190,053-810-3150,053-810-3170,053-810-3185,053-810-2890,053-810-3555","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/007.jpeg",35.83425349118948,128.7590547299206));

        mapDataList.add(new BuildingData("체육관운동장테니스a09","A09","중앙테니스장","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/045.jpeg",35.83470170432628,128.76013805097483));
        mapDataList.add(new BuildingData("미술대학예술대학미대예대a10","A10","음악관","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/042.jpeg",35.83487681975927,128.7614090060983));
        mapDataList.add(new BuildingData("미술대학예술대학미대예대a11","A11","심포니홀","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/122.jpeg",35.83531140831219,128.76126921957854));
        mapDataList.add(new BuildingData("목공기계집진실a12","A12","시설관리지원센터","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/219.jpeg",35.83604486357597,128.76317779219394));
        mapDataList.add(new BuildingData("체육관운동장유도레슬링a13","A13","필승관","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/043.jpeg",35.83508802020847,128.760832695813));
        mapDataList.add(new BuildingData("체욱관운동장유도레슬링a14","A14","씨름장","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/100.jpeg",35.83625284930233,128.76067031274948));
        mapDataList.add(new BuildingData("미술대학예술대학미대예대a24","A24","세라믹 실기동","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/049.jpeg",35.835247016798675,128.7595137863297));
        mapDataList.add(new BuildingData("체육관운동장탁구a27","A27","탁구장","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/093.jpeg",35.83648383021495,128.75935850672604));
        mapDataList.add(new BuildingData("온실a29","A29","독도자연생태온실","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/093.jpeg",35.83631105572632,128.76284615710034));

        //B
        mapDataList.add(new BuildingData("강당,동아리b01","B01","노천강당","노천강당","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/056.jpeg",35.83413116192374,128.75509587741593));
        mapDataList.add(new BuildingData("경영대학b02","B02","상경관","상경관","경제금융학부,경영학과,무역학부,회계세무학과,항공운송학과,산업경영학과"
                ,"https://econ.yu.ac.kr/econ/index.do,https://biz.yu.ac.kr/biz/index.do,https://trade.yu.ac.kr/trade/index.do,https://acc.yu.ac.kr/acc/index.do,https://daspo.yu.ac.kr/daspo/index.do,https://im.yu.ac.kr/im/index.do"
                ,"053-810-3212,053-810-7815,053-810-7815,053-810-7815,053-810-2705053-810-7814","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/011.jpeg",35.832779602333574,128.75601224453618));
        mapDataList.add(new BuildingData("인문대학b03","B03","인문관","인문관","국어국문학과,유럽언어문화학부,철학과,일어일문학과,역사학과,영어영문학과, 문화인류학과",
                "https://korean.yu.ac.kr/korean/index.do,https://europe.yu.ac.kr/europe/index.do,https://phil.yu.ac.kr/phil/index.do,https://japan.yu.ac.kr/japan/index.do,https://yuhistory.yu.ac.kr/yuhistory/index.do,https://engl.yu.ac.kr/engl/intro/location.do,https://saram.yu.ac.kr/saram/intro/location.do",
                "053-810-2110,053-810-2180,053-810-2190,053-810-3160,053-810-2210,053-810-2130,053-810-2240",
                "https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/004.jpeg",35.831875,128.758512));
        mapDataList.add(new BuildingData("사회과학대학글로벌인재대학천마학부대학b05","B05","사회과학관","사회과학관","군사학과,경찰행정학과,새마을국제개발학과,글로벌비즈니스학과,천마인재학부",
                "https://ms.yu.ac.kr/ms/index.do,https://police.yu.ac.kr/police/index.do,https://intdev.yu.ac.kr/intdev/index.do,https://gbusiness.yu.ac.kr/gbusiness/index.do,https://honors.yu.ac.kr/honors/index.do",
                "053-810-2290,053-810-2910,053–810–2680,053-810-7807,053-810-1412","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/131.jpeg",35.833611,128.756451));
        mapDataList.add(new BuildingData("학생회동아리방b06","B06","학생회관","학생회관","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/009.jpeg",35.83420608297126,128.75675192043371));
        mapDataList.add(new BuildingData("체육관경기장b07","B07","이희건기념관","이희건기념관","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/010.jpeg",35.83435735589169,128.7559584903663));

        //C
        mapDataList.add(new BuildingData("대학본관대학본부c01","C01","본부본관","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/001.jpeg",35.829988,128.761372));
        mapDataList.add(new BuildingData("외국어교육원c02","C02","외국어교육원","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/003.jpeg",35.8318203,128.759985));
        mapDataList.add(new BuildingData("사회과학대학,천마학부대학c03","C03","천마관","","사회학과,심리학과,전공자유선택학부"
                ,"https://socio.yu.ac.kr/socio/index.do,https://psy.yu.ac.kr/psy/index.do,https://ccgs.yu.ac.kr/ccgs/index.do",
                "053-810-2250,053-810-2230,053-810-2205","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/005.jpeg",
                35.8323829,128.7600259));
        mapDataList.add(new BuildingData("승리관c07","C07","승리관","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/223.jpeg",35.8340532,128.761805));
        mapDataList.add(new BuildingData("야구연습장c06","C06","야구부실내연습장","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/039.jpeg",35.8339955,128.761898));
        mapDataList.add(new BuildingData("테니스장c31","C31","교직원테니스장","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/096.jpeg",35.83362471,128.7635281));
        mapDataList.add(new BuildingData("학군단c28","C28","학군단","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/034.jpeg",35.83298743,128.762435));
        mapDataList.add(new BuildingData("명상실c24","C24","명상실","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/035.jpeg",35.8318604,128.762476));
        mapDataList.add(new BuildingData("연대본부c25","C25","연대본부 및 노동조합","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/032.jpeg",35.832123162,128.762072));
        mapDataList.add(new BuildingData("출판부c26","C26","출판부","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/033.jpeg",35.832225193,128.761721));
        mapDataList.add(new BuildingData("인문관세미나실c27","C27","제2인문관","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/198.jpeg",35.8324466822,128.761211));
        mapDataList.add(new BuildingData("민원실c21","C21","학사민원실","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/002.jpeg",35.829955807,128.761776));

        //E
        mapDataList.add(new BuildingData("공연장대강당e02","E02","천마아트센터","","천마아트센터","https://cmac.yu.ac.kr/cmac/index.do","053-810-1528","https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20200418_33%2F1587194801709wjBqs_JPEG%2FAS4mXuyzDIbMuAUWP8uUliYl.jpeg.jpg",35.8320097,128.753003));
        mapDataList.add(new BuildingData("체육관운동장체조볼링배드민턴수영태권도e04","E04","체조장","","체육학전공,무용학전공","https://pe.yu.ac.kr/pe/index.do,https://dance.yu.ac.kr/dance/index.do","053-810-3763,053-810-3149","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/059.jpeg",35.830851356185285,128.7529156124662));
        mapDataList.add(new BuildingData("체육관운동장구기무용e05","E05","천마체육관","","","","053-810-2550,053-810-3295,053-810-3297","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/136.jpeg",35.83028382932409,128.7529031271948));
        mapDataList.add(new BuildingData("기계it대학기아대e21","E21","IT관","","컴퓨터공학과,정보통신공학과,소프트웨어융합학부","https://cse.yu.ac.kr/cse/index.do,http://ice.yu.ac.kr/ice/index.do,https://sw.yu.ac.kr/sw/index.do","053-810-2550,053-810-3295,053-810-3297","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/012.jpeg",35.830621,128.7544468));
        mapDataList.add(new BuildingData("기계it대학기아대e22","E22","전기관","","전기공학과,전자공학과","https://ee.yu.ac.kr/ee/index.do,https://electronics.yu.ac.kr/electronics/index.do","053-810-2480,053-810-2490","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/013.jpeg",35.8299436,128.754348));
        mapDataList.add(new BuildingData("공과대학공대e23","E23","섬유관","","파이버시스템공학과","https://textiles.yu.ac.kr/textiles/index.do","053-810-2770","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/014.jpeg",35.829423,128.754422));
        mapDataList.add(new BuildingData("공과대학공대e24","E24","화공관","","화학공학부","https://che.yu.ac.kr/che/index.do","053-810-2510","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/118.jpeg",35.828932,128.754134));
        mapDataList.add(new BuildingData("공과대학공대e28","E28","소재관","","신소재공학부,환경공학과","https://mse.yu.ac.kr/mse/index.do,https://enveng.yu.ac.kr/enveng/index.do","053-810-2470,053-810-2540","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/201.jpeg",35.827121,128.754094));
        mapDataList.add(new BuildingData("기계it대학기아대e29","E29","기계관","","기계공학부","https://me.yu.ac.kr/me/index.do","053-810-2450","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/137.jpeg",35.826610,128.754042));
        ///F
        mapDataList.add(new BuildingData("공과대학공대f03","F03","건축관","","건축학부","https://arch.yu.ac.kr/arch/index.do","053-810-2420","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/018.jpeg",35.829819,128.755421));
        mapDataList.add(new BuildingData("전산원f04","F04","정보전산원","","정보전산원","https://www.yu.ac.kr/iics/index.do","webadmin@yu.ac.kr","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/094.jpeg",35.82933239582726,128.75573156225732));
        mapDataList.add(new BuildingData("강당f05","F05","공과대학강당","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/019.jpeg",35.82950109179401,128.75528712584338));
        mapDataList.add(new BuildingData("연구소f06","F06","정보통신연구소","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/020.jpeg",35.82927145958731,128.75527653469197));
        mapDataList.add(new BuildingData("공과대학공대f07","F07","건설관","","건설시스템공학과,도시공학과,","https://civil.yu.ac.kr/civil/index.do,https://urban.yu.ac.kr/urban/index.do","053-810-2410,053-810-2430","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/190.jpeg",35.82966747128012, 128.7568510320053));
        mapDataList.add(new BuildingData("자연과학대학자연대f21","F21","제1과학관","","물리학과,화학과,수학과","https://physics.yu.ac.kr/physics/index.do,https://chem.yu.ac.kr/chem/index.do,https://math.yu.ac.kr/math/index.do", "053-810-2330,053-810-2350,053-810-2310","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/023.jpeg",35.830179,128.757570));
        mapDataList.add(new BuildingData("자연과학대학자연대f22","F22","제2과학관","","통계학과,생명과학과","https://sta.yu.ac.kr/sta/index.do,https://lifesciences.yu.ac.kr/lifesciences/index.do","053-810-2320,053-810-2370","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/024.jpeg",35.829435,128.757609));
        mapDataList.add(new BuildingData("자연과학대학자연대f23","F23","제3과학관","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/190.jpeg",35.82966747128012,128.7568510320053));
        mapDataList.add(new BuildingData("생명응용과학대학생대f26","F26","생명응용과학대 제1실험동","","의생명공학과,식품공학과,삭품경제외식학과(외식산업트랙)","https://mbt.yu.ac.kr/mbt/index.do,https://foodscience.yu.ac.kr/foodscience/index.do,https://fes.yu.ac.kr/foodservice/index.do","053-810-3020,053-810-2950,053-810-2980","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/027.jpeg",35.82805502449726,128.75619582425037));
        mapDataList.add(new BuildingData("생명응용과학대학생대f27","F27","생명응용과학대본관","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/026.jpeg",35.827690624358354,128.75677425241906));
        mapDataList.add(new BuildingData("생명응용과학대학생대f28","F28","생명응용과학대제2실험동","","산림자원학과,조경학과,삭품경제외식학과(식품자원경제트랙)","https://forestry.yu.ac.kr/forestry/index.do,https://land.yu.ac.kr/land/index.do,https://fes.yu.ac.kr/frecon/index.do","053-810-2920,053-810-2970,053-810-2960","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/029.jpeg",35.827286679822166,128.75697558622934));
        mapDataList.add(new BuildingData("생명응용과학대학생대f29","F29","생명응용과학대제3실험동","","생명공학과,원예생명과학과,응용미생물학과","https://bt1.yu.ac.kr/bt1/index.do,https://sta.yu.ac.kr/sta/index.do,https://lifesciences.yu.ac.kr/lifesciences/index.do","053-810-3020,053-810-2907,053-810-2320","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/028.jpeg",35.827710751884105,128.75539707034088));

        //G
        mapDataList.add(new BuildingData("생활과학대학본관생과대본관g01","G01","생활과학대학본관","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/030.jpeg",35.829314,128.758779));
        mapDataList.add(new BuildingData("생활과학대학별관생과대별관g02","G02","생활과학대학별관","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/114.jpeg",35.8291038719,128.759649));
        //약대
        mapDataList.add(new BuildingData("약학대학약대g07","G07","약학관","","약학부","https://pharm.yu.ac.kr/pharm/index.do","053-810-4654","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/285.jpeg",35.8277135,128.7579808));
        mapDataList.add(new BuildingData("CRCcrcg11","G11","CRC","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/250.jpeg",35.8262985917,128.756760));
        mapDataList.add(new BuildingData("창업센터g12","G12","창업보육센터","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/275.jpeg",35.825994455,128.7559899));
        mapDataList.add(new BuildingData("기계it대학기아대g29","G29", "자동차관","","미래자동차공학과","https://automotive.yu.ac.kr/automotive/index.do","053-810-4789","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/172.jpeg",35.825000,128.754955));
        mapDataList.add(new BuildingData("중앙기기센터g14","G14","중앙기기센터","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/171.jpeg",35.8255390915,128.755393));
        mapDataList.add(new BuildingData("생산기술연구원g15","G15","생산기술연구원","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/%EC%83%9D%EC%82%B0%EA%B8%B0%EC%88%A0%EC%97%B0%EA%B5%AC%EC%9B%90%20(3).jpg",35.82555334,128.7538004));
        mapDataList.add(new BuildingData("풍동실험실g17","G17","풍동실험실","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/134.jpeg",35.8249861847,128.7543799));
        mapDataList.add(new BuildingData("안전교육체험장g19","G19","안전교육체험장","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/%EC%95%88%EC%A0%84%EA%B5%90%EC%9C%A1%EC%B2%B4%ED%97%98%EC%9E%A5.jpg",35.8247008407,128.753864));

        mapDataList.add(new BuildingData("기계it대학기아대g13","G13","로봇관","","로봇공학과","https://robotics.yu.ac.kr/robotics/index.do","053-810-3035","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/173.jpeg",35.825446,128.756199));
        mapDataList.add(new BuildingData("자동차관g16","G16","자동차관","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/172.jpeg",35.825000306,128.7549556));


        mapDataList.add(new BuildingData("기숙사향토관우정관고시원생활관","D01~10, 21","생활관","","생활관행정실(관장),행정실장/총괄","https://www.yu.ac.kr/dormi/index.do,https://www.yu.ac.kr/dormi/index.do","053-810-1810,053-810-1811","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/218.jpeg",35.83046805,128.74891800));

        //민속촌
        mapDataList.add(new BuildingData("민속촌","민속촌","민속촌","","","","","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/248.jpeg",35.82833537,128.7612063));

        //대학원
        mapDataList.add(new BuildingData("대학원법학대학원g04","G04","대학원/법학전문대학원","","대학원","https://graduate.yu.ac.kr/graduate/main.do","053-810-3754","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/228.jpeg",35.828382,128.759373));


        //편의점
        mapDataList.add(new BuildingData("편의점c02","C02","GS25 영남대 국제교류관점","08:00 ~ 23:00\n[23:00~08:00(무인운영)]\n","","","","https://yucf.yu.ac.kr/_res/yu/yucf/img/conv/img-intl01.jpg",35.8317376,128.7602830));

        mapDataList.add(new BuildingData("편의점b02","B02","세븐일레븐 영남대 상경관점","학기중\n평일 : 08:00 ~ 22:00\n" +
                "방학중\n평일\n09:00 ~ 19:00\n" +
                "토,일\n09:00 ~ 19:00","","","053-815-2631","https://yucf.yu.ac.kr/_res/yu/yucf/img/conv/img-cce01.jpg",35.8326520,128.7557136));

        mapDataList.add(new BuildingData("편의점f25","F25","세븐일레븐 영남대 자연대점","평일,주말 : 08:00 ~ 22:00\n(※ 시험기간 : 07:00 ~ 24:00)\n","","","053-801-3202","https://yucf.yu.ac.kr/_res/yu/yucf/img/conv/img-na01.jpg",35.828296,128.7566318));

        mapDataList.add(new BuildingData("편의점d04","D04","세븐일레븐 영남대 생활관점","학기중\n평일 : 08:00 ~ 22:00\n" +
                "방학중\n평일\n : 09:00 ~ 19:00\n" +
                "토,일\n09:00 ~ 19:00","","","053-811-7850","https://yucf.yu.ac.kr/_res/yu/yucf/img/conv/img-domi01.jpg",35.8294243,128.7489046));

        mapDataList.add(new BuildingData("편의점b06","B06","GS25 영남대 학생회관점","학기중\n 00:00 ~ 24:00\n" +
                "방학중\n08:00 ~ 22:00","","","053-811-8146","https://yucf.yu.ac.kr/_res/yu/yucf/img/conv/img-stu01.jpg",35.8341269,128.7570265));



        //카페
        mapDataList.add(new BuildingData("카페음료","스타벅스","영남대 아트센터점","학기중\n평일 : 08:00 ~ 21:00\n토,일 : 10:00 ~ 18:00\n" +
                "방학중\n 평알 : 10:30 ~ 17:00\n토,일 : 휴무","","","053-814-8806","https://lh5.googleusercontent.com/p/AF1QipNLX-CmbtiXHN_1DGMAwpreEEL_V9RdcB5_-cVm=w408-h306-k-no",35.8320451,128.7532596));
        mapDataList.add(new BuildingData("카페음료","스타벅스","영남대 중앙도서관","학기중\n평일 : 07:00 ~ 22:00\n토,일 : 09:00 ~ 17:00\n" +
                "방학중\n 09:00 ~ 17:00\n토,일 포함음료","","","053-811-8780","https://lh5.googleusercontent.com/p/AF1QipPWp4hGHAOySXtAnnPwE_3PtjzLjaPFOrat0LN4=w408-h306-k-no",35.8330426,128.7580567));
        mapDataList.add(new BuildingData("카페음료","그라찌에","영남대 외국어교육원점","평일 : 09:00 ~ 18:00\n토,일,공휴일 : 휴무\n","","","0507-1348-9699","https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20220507_246%2F1651849342855ISXbp_JPEG%2F20220416_121950.jpg",35.8316935,128.7602085));
        mapDataList.add(new BuildingData("카페음료","카페아이엔지","영남대학교점","평일 : 09:00 ~ 18:00","아이엔지","","053-802-8904","https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20220328_163%2F1648465626920zhVEr_JPEG%2F%25B8%25C5%25C0%25E5%25BB%25E7%25C1%25F81.jpg",35.8258693,128.7562828));

        //도서관
        mapDataList.add(new BuildingData("도서관이도과도f24","F24","이종우 과학 도서관","","","","053-810-1694","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/025.jpeg",35.8290257,128.7569188));
        mapDataList.add(new BuildingData("도서관중도b04","BO4","중앙도서관","","","","053-810-1675","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/006.jpeg",35.8330565,128.7579612));
        mapDataList.add(new BuildingData("도서관법도g03","GO3","법학전문도서관","","법학전문도서관","https://lawlib.yu.ac.kr/lawlib/","053-810-1667","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/135.jpeg",35.828100706895235,128.7598483859868));
        //프린터
        mapDataList.add(new BuildingData("프린터","복사실","법학전문도서관 복사실","학기중\n09:00 ~ 18:00\n" +
                "방학중\n10:00 ~ 17:00\n" +
                "토요일\n 휴무","","","053-812-0044","https://www.yu.ac.kr/_res/yu/yucf/img/copy/img-law02.jpg",35.8281191,128.7599676));


        //식당
        mapDataList.add(new BuildingData("식당학식밥점심f25","F25","자연계 식당","학기중\n평일 : 10:30 ~ 18:00\n토요일 : 10:30 ~ 14:00\n일,공휴일 : 휴무\n" +
                "방학중\n평일 : 11:00 ~ 18:00\n평일 외 : 휴무","","","053-801-3667","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/123.jpeg",35.8283664,128.7562405));
        mapDataList.add(new BuildingData("식당학식밥점심b06","B06","학생회관 식당","학기중\n평일 : 09:30 ~ 19:00\n토요일,공휴일 : 10:30 ~ 14:00\n일요일 휴무\n" +
                "방학중\n평일 : 10:30 ~ 18:00\n주말,공휴일 휴무","","","053-811-7554","https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzA0MDRfMjgz%2FMDAxNjgwNTcxOTg3MDUy.r4vveNmrtwMUxnggDmCIrPBYqLDrB0fPEuo1SoU_mwEg.4zBcsR0GzDtu0BExJMyHPZC28hB4NOVqt88xFvLpM3Yg.JPEG.doggie738%2F1680571985553.jpg",35.8341353,128.7569366));
        mapDataList.add(new BuildingData("식당학식밥점심c04","C04","인문계 교직원식당(선향재)","학기중\n중식 : 11:30 ~ 13:30\n석식 : 17:30 ~ 18:30\n" +
                "방학중\n중식 : 11:30 ~ 13:30\n주말, 공휴일 휴무","","","053-811-7554","https://www.yu.ac.kr/_res/yu/main/img/campusmap/building-img/170.jpeg",35.833257860637396,128.76027767590867));
        mapDataList.add(new BuildingData("식당학식밥점심e02","E02","천마아트센터 식당(은하정)","학기중\n평일 : 11:00 ~ 20:00\n" +
                "토·일요일 : 예약제에 한함\n","","","053-815-7001","https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxOTAyMDFfMzIg%2FMDAxNTQ5MDE4MjI5NTgx.kLlDfu9lWhRM_TMCNvpmWpED82kuw3dwYmgbTABkB2Ag.26RSAsAqZgGWt_5A-GCocciBIJM-PMG0fKo-RgJSBv4g.JPEG.djs87%2Foutput_1546324484.jpg",35.8323897 ,128.75312955201326));


        mapDataList.add(new BuildingData("프린터","복사실","상경관 복사실 ","학기중\n08:30 ~ 19:30\n" +
                "방학중\n09:00 ~ 17:00\n" +
                "토요일\n09:30 ~ 12:30","","","053-812-1364","https://www.yu.ac.kr/_res/yu/yucf/img/copy/img-cce01.jpg",35.8328044,128.7559589));

        mapDataList.add(new BuildingData("프린터","복사실","사범대학 복사실 ","학기중\n08:30 ~ 19:00\n" +
                "방학중\n09:00 ~ 19:00\n" +
                "토요일\n09:30 ~ 13:00","","","053-941-9807","https://www.yu.ac.kr/_res/yu/yucf/img/copy/img-edu01.jpg",35.8342274,128.7591396));

        mapDataList.add(new BuildingData("프린터","복사실","이종우 과학도서관 복사실 ","학기중\n09:00 ~ 20:00\n" +
                "방학중\n09:00 ~ 19:00\n" +
                "토요일\n09:30 ~ 13:00","","","053-813-2301","https://www.yu.ac.kr/_res/yu/yucf/img/copy/img-ljw01.jpg",35.828908,128.757102));

        mapDataList.add(new BuildingData("프린터","복사실","인문관 복사실 ","학기중\n09:00 ~ 19:00\n" +
                "방학중\n10:00 ~ 17:00\n" +
                "토요일\n09:00 ~ 12:00","","","053-811-6809","https://www.yu.ac.kr/_res/yu/yucf/img/copy/img-human01.jpg",35.8319157,128.7585143));

        mapDataList.add(new BuildingData("프린터","복사실","전기관 복사실 ","학기중\n08:30 ~ 18:30\n" +
                "방학중\n10:00 ~ 16:00\n" +
                "토요일\n휴무","","","053-811-5265","https://www.yu.ac.kr/_res/yu/yucf/img/copy/img-elec01.jpg",
                35.8290257,128.7569188));

        mapDataList.add(new BuildingData("프린터","복사실","기계관 복사실","학기중\n09:00~19:00\n" +
                "방학중\n10:00 ~ 17:00\n" +
                "토요일\n09:00 ~ 12:00","","","053-811-0048","https://www.yu.ac.kr/_res/yu/yucf/img/copy/img-machine01.jpg",35.826434,128.754122));

        return mapDataList;

    }


}