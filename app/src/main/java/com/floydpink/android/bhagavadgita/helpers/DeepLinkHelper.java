package com.floydpink.android.bhagavadgita.helpers;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

/**
 * Created by hari on 12/24/14.
 */
public class DeepLinkHelper {
    public static void checkForDeepLinkIntentAction(final DeepLinkHelperCallback context, Activity activity) {
        final Intent intent = activity.getIntent();
        final String action = intent.getAction();

        // if the app is launched from a deep link, navigate to the child/grandchild activity
        if (Intent.ACTION_VIEW.equals(action)) {
            //  *** UNCOMMENT BELOW TO ATTACH DEBUGGER ***
            Log.i("Starting delay:", "Attach the debugger");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    final String url = intent.getDataString();
                    int baseUrlLength = ShareHelper.BASE_URL.length();
                    if (url.indexOf(ShareHelper.BASE_URL) == 0 && url.length() > baseUrlLength) {
                        String queryString = url.substring(baseUrlLength);
                        String[] parts = queryString.split("[/]");
                        int chapterIndex = -1;
                        int sectionIndex = -1;
                        if (parts.length == 2) {    // deep link to a section
                            chapterIndex = Integer.parseInt(parts[0]);
                            sectionIndex = Integer.parseInt(parts[1]);
                        } else if (parts.length == 1) { //deep link to a chapter
                            chapterIndex = Integer.parseInt(parts[0]);
                        }
                        String chapterName = ChapterHelper.getChapterFromChapterIndex(chapterIndex).getName();
                        context.ProcessDeepLink(chapterName, sectionIndex != -1 ? queryString : "");
                    }
                }
            }, 100);
        }

    }
}
