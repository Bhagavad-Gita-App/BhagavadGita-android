package com.floydpink.android.bhagavadgita;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;

import com.floydpink.android.bhagavadgita.helpers.ShareHelper;


/**
 * An activity representing a single Chapter detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ChapterListActivity}.
 * <p/>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link ChapterDetailFragment}.
 */
public class ChapterDetailActivity extends Activity
        implements ChapterDetailFragment.Callbacks {

    /**
     * Flag to indicate if deep links have been processed yet
     */
    private static boolean processedDeepLinks = false;

    /**
     * Name of the chapter this activity is presenting
     */
    private String mChapterName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("ChapterDetailActivity method: ", "onCreate");
        Log.i("processedDeepLinks: ", Boolean.toString(processedDeepLinks));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_detail);

        // Show the Up button in the action bar.
        getActionBar().setDisplayHomeAsUpEnabled(true);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            mChapterName = getIntent().getStringExtra(ChapterDetailFragment.ARG_CHAPTER_NAME);
            String chapterSectionQueryString = getIntent().getStringExtra(ChapterDetailFragment.ARG_CHAPTER_SECTION_QUERY_STRING);
            arguments.putString(ChapterDetailFragment.ARG_CHAPTER_NAME, mChapterName);
            if (!TextUtils.isEmpty(chapterSectionQueryString) && !processedDeepLinks) {
                arguments.putString(ChapterDetailFragment.ARG_CHAPTER_SECTION_QUERY_STRING,
                        chapterSectionQueryString);
                processedDeepLinks = true;
            }
            ChapterDetailFragment fragment = new ChapterDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .add(R.id.chapter_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.i("In method:", "ChapterDetailActivity::onOptionsItemSelected");

        switch (id) {
            case android.R.id.home:
                setResult(RESULT_CANCELED);
                finish();
                return true;
            case R.id.action_share:
                ShareHelper.ShareChapter(this, mChapterName);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSectionSelected(String chapterAndVerse) {
        Intent detailIntent = new Intent(this, SectionDetailActivity.class);
        detailIntent.putExtra(SectionDetailActivity.ARG_CHAPTER_VERSE, chapterAndVerse);
        startActivity(detailIntent);
    }
}
