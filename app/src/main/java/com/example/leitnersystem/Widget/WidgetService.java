package com.example.leitnersystem.Widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.leitnersystem.R;
import com.example.leitnersystem.RoomCategory.Category;
import com.example.leitnersystem.RoomCategory.CategoryRoomDatabase;

import java.util.List;

import static com.example.leitnersystem.Widget.AppWidget.EXTRA_ITEM_POSITION;

public class WidgetService extends RemoteViewsService {
    private final String LOGTAG = "WidgetService";


    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ExampleWidgetItemFactory(getApplicationContext(), intent);
    }

    class ExampleWidgetItemFactory implements RemoteViewsFactory {
        private final Context context;
        private int appWidgetId;
        private List<Category> categoryList;

        ExampleWidgetItemFactory(Context context, Intent intent){
            this.context = context;
            this.appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        @Override
        public void onCreate() {
            // Connect to data source
            Log.d(LOGTAG, "onCreate");
            new dbAsyncTask().execute();
        }

        @Override
        public void onDataSetChanged() {
            // Updates widget
            Log.d(LOGTAG, "onDataSetChanged");
            new dbAsyncTask().execute();
        }

        @Override
        public void onDestroy() {
            // Close Data Source
            Log.d(LOGTAG, "onDestroy");
        }

        @Override
        public int getCount() {
            return categoryList.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            Log.d(LOGTAG, "getViewAt: " + position);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_item);
            Category current = categoryList.get(position);
            views.setTextViewText(R.id.widget_item_text, current.getTitle());

            Intent fillIntent = new Intent();
            fillIntent.putExtra(EXTRA_ITEM_POSITION, position);
            views.setOnClickFillInIntent(R.id.widget_item_text, fillIntent);

            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        private class dbAsyncTask extends AsyncTask<Void, Void, Void> {

            protected  Void doInBackground(Void... voids) {
                Log.d(LOGTAG, "dbAsyncTask doInBackground");
                CategoryRoomDatabase database = CategoryRoomDatabase.getDatabase(context);
                categoryList = database.CategoryDao().getAlphabetizedTitleWidget();

                for(Category category: categoryList) {
                    category.getTitle();

                    Log.d(LOGTAG, category.getTitle());
                }

                return null;
            }
        }
    }
}
