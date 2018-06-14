package com.example.botond.judoapp_4.manager;

import com.example.botond.judoapp_4.ctrl.BeltController;
import com.example.botond.judoapp_4.ctrl.LectureController;
import com.example.botond.judoapp_4.domain.Vocabulary;

import java.util.List;

public class ResourceManager {
    public static BeltController beltController;
    public static LectureController lectureController;

    public static void init(){
        if(beltController==null)
            beltController=new BeltController();
        if(lectureController==null)
            lectureController=new LectureController();

    }

    public static BeltController getBeltController() {
        return beltController;
    }

    public static LectureController getLectureController() {
        return lectureController;
    }
}
