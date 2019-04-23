package com.example.leitnersystem.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.RemoteViews;

import com.example.leitnersystem.Activities.MainActivity;
import com.example.leitnersystem.R;

/**
 * Implementation of App Widget functionality.
 */
public class AppWidget extends AppWidgetProvider {


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        final String LOGTAG = "AppWidget";


        // Widget title launches the main activity
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        // Intent service runs on
        Intent serviceIntent = new Intent(context, WidgetService.class);
        serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_app_new);
        views.setOnClickPendingIntent(R.id.widget_title_section, pendingIntent);
        views.setRemoteAdapter(R.id.widget_stack_view, serviceIntent);
        views.setEmptyView(R.id.widget_stack_view, R.id.widget_empty_view);

//        PendingIntent pendingUpdate = PendingIntent.getBroadcast(
//                context, appWidgetId, serviceIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//
//
//        views.setOnClickPendingIntent(R.id.widget_btn_reload, serviceIntent);






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

