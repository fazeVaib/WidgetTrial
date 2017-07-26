package com.fazevaib.widgetstrial;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Random;

/**
 * Implementation of App Widget functionality.
 */
public class MyWidget extends AppWidgetProvider {

    /*static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget);
        views.setTextViewText(R.id.appwidget_text, "Hello");

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }*/

    int i=0;
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them

        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget);
            int i = new Random().nextInt(100);
            views.setTextViewText(R.id.appwidget_text, String.valueOf(i));
            Log.d("TAG", "OnUpdate was called " + i + "times");

            Intent intent = new Intent(context, MyWidget.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            PendingIntent pi = PendingIntent.getBroadcast(context,appWidgetId,intent, PendingIntent.FLAG_UPDATE_CURRENT);

            views.setOnClickPendingIntent(R.id.appwidget_text, pi);

            //Instruct thr widget manager to update the widget
//            appWidgetManager.updateAppWidget(appWidgetId, views);

            Intent intent2 = new Intent(context, MainActivity.class);
            intent2.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent2.putExtra("VALUE", i);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, appWidgetId, intent2, PendingIntent.FLAG_UPDATE_CURRENT);

//            RemoteViews views2 = new RemoteViews(context.getPackageName(), R.layout.my_widget);
            views.setOnClickPendingIntent(R.id.relativeLayout1, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        i=0;
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

