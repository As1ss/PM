package al.if05.practica6;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class  ActivityManager {

    private static ArrayList<AppCompatActivity> activities = new ArrayList<>();



    public static void addActivity(AppCompatActivity activity){

        activities.add(activity);

    }
    public static void finishActivities(){

        for (AppCompatActivity activity: activities){
            activity.finish();
        }

    }
}
