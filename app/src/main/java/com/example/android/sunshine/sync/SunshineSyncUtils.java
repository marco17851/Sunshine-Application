/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine.sync;

import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.android.sunshine.data.WeatherContract;


public class SunshineSyncUtils {

    private static boolean sInitialized;

    public static void initialize(final Context context){
        if (!sInitialized){
            sInitialized = true;

            new AsyncTask<Void, Void, Void>(){

                @Override
                protected Void doInBackground(Void... params) {

                    Cursor cursor = context.getContentResolver().query(
                            WeatherContract.WeatherEntry.CONTENT_URI,
                            new String[]{WeatherContract.WeatherEntry._ID},
                            null,
                            null,
                            null);

                    if (cursor == null || cursor.getCount() == 0){
                        startImmediateSync(context);
                    }

                    cursor.close();
                    return null;
                }
            }.execute();
        }
    }

    /**
     * Helper method to perform a sync immediately using an IntentService for asynchronous
     * execution.
     *
     * @param context The Context used to start the IntentService for the sync.
     */
    public static void startImmediateSync(@NonNull final Context context) {
        Intent intentToSyncImmediately = new Intent(context, SunshineSyncIntentService.class);
        context.startService(intentToSyncImmediately);
    }
}