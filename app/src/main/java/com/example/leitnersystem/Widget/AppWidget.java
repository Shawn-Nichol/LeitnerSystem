package com.example.leitnersystem.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.leitnersystem.Activities.MainActivity;
import com.example.leitnersystem.R;

/**
 * Implementation of App Widget functionality.
 */
public class AppWidget extends AppWidgetProvider {
    private static String LOGTAG = "Widget appWidget";

    private static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                        int appWidgetId) {
        Log.d(LOGTAG, "updateAppWidget");

        // Widget title launches the main activity
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        // Intent service runs on
        Intent serviceIntent = new Intent(context, WidgetService.class);
        serviceIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);

        Intent intentUpdate = new Intent(context, AppWidget.class);
        intentUpdate.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        // Update the current widget instance only, by creating an array that contains the widgets unique ID
        int[] idArray = new int[]{appWidgetId};
        intentUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, idArray);

        // Click List Item
        Intent clickIntent = new Intent(context, AppWidget.class);
        clickIntent.setAction(ACTION_TOAST);
        PendingIntent clickPendingIntent = PendingIntent.getBroadcast(context, 0,
                clickIntent, 0);

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_app_new);
        views.setOnClickPendingIntent(R.id.widget_title_section, pendingIntent);
        views.setRemoteAdapter(R.id.widget_stack_view, serviceIntent);
        views.setEmptyView(R.id.widget_stack_view, R.id.widget_empty_view);
        views.setPendingIntentTemplate(R.id.widget_stack_view, clickPendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private static final String ACTION_TOAST = "actionToast";
    public static final String EXTRA_ITEM_POSITION = "extraItemPosition";
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
            Log.d(LOGTAG, "onUpdate");
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(LOGTAG, "onReceive");
        if(ACTION_TOAST.equals(intent.getAction())){
            Log.d(LOGTAG, "onReceive if");
            int clickedPosition = intent.getIntExtra(EXTRA_ITEM_POSITION, 0);
            Toast.makeText(context, R.string.widget_clicked_position + clickedPosition, Toast.LENGTH_SHORT).show();
        }
        super.onReceive(context, intent);
    }
}

