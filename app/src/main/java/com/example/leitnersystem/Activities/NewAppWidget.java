package com.example.leitnersystem.Activities;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.RemoteViews;

import com.example.leitnersystem.R;
import com.example.leitnersystem.RoomCategory.Category;
import com.example.leitnersystem.RoomCategory.CategoryRoomDatabase;

import java.util.List;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_app_new);



        // Widget title launches the main activity
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        views.setOnClickPendingIntent(R.id.widget_title_section, pendingIntent);




 //       AppDatabase database = AppDatabase.getInstance(context);
//        tasks = database.taskDao().loadAllTasks();

    //    CategoryRoomDatabase database = CategoryRoomDatabase().getInstance(context);

        CategoryRoomDatabase database = CategoryRoomDatabase.getInstance(context);



        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);



    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }


}

