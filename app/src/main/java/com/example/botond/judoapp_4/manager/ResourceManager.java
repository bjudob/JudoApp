package com.example.botond.judoapp_4.manager;

import com.example.botond.judoapp_4.ctrl.BeltController;
import com.example.botond.judoapp_4.ctrl.LectureController;
import com.example.botond.judoapp_4.ctrl.VocabularyController;
import com.example.botond.judoapp_4.domain.Vocabulary;

import java.util.List;

public class ResourceManager {
    public static BeltController beltController;
    public static LectureController lectureController;
    public static VocabularyController vocabularyController;

    public static void init(){
        if(beltController==null)
            beltController=new BeltController();
        if(lectureController==null)
            lectureController=new LectureController();
        if(vocabularyController==null)
            vocabularyController=new VocabularyController();

    }

    public static BeltController getBeltController() {
        return beltController;
    }

    public static LectureController getLectureController() {
        return lectureController;
    }

    public static VocabularyController getVocabularyController() { return vocabularyController; }
}
