package com.floydpink.android.bhagavadgita;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.floydpink.android.bhagavadgita.data.BookData;
import com.floydpink.android.bhagavadgita.helpers.TypefaceSpan;
import com.floydpink.android.bhagavadgita.models.Chapter;
import com.floydpink.android.bhagavadgita.models.Section;


public class SectionDetailActivity extends Activity {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_CHAPTER_VERSE = "chapter_and_verse";

    /**
     * The chapter and section indices for the section to be presented here as querystring
     */
    private String mChapterSectionIndicesQueryString;

    /**
     * The section to be presented here as querystring
     */
    private Section mSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get the chapter and section indices for the section to be presented
        String chapterAndVerse = getIntent().getStringExtra(ARG_CHAPTER_VERSE);

        mChapterSectionIndicesQueryString = chapterAndVerse;
        Log.d("Chapter And Section Index Query String is ", mChapterSectionIndicesQueryString);

        String[] parts = chapterAndVerse.split("[=&]");
        int chapterIndex = Integer.parseInt(parts[1]);
        int sectionIndex = Integer.parseInt(parts[3]);

        //get the section
        Chapter chapter = BookData.Book.getChapters().get(chapterIndex);
        mSection = chapter.getSections().get(sectionIndex);

        // set the malayalam title on this activity
        SpannableString s = new SpannableString(getSectionDetailActivityTitle(chapter, sectionIndex, mSection));
        s.setSpan(new TypefaceSpan(this, "AnjaliOldLipi.ttf"), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Update the action bar title with the TypefaceSpan instance
        ActionBar actionBar = getActionBar();
        assert actionBar != null;
        actionBar.setTitle(s);

        // Show the Up button in the action bar.
        getActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_section_detail);
    }

    private String getSectionDetailActivityTitle(Chapter chapter, int sectionIndex, Section section) {
//        getSlokaNumber = function (s, sloka) {
//            var slokaNumber = parseInt(s, 10) + 1;
//            var re = /\(([0-9]+)\)/gm;
//            if (sloka.Content.match(re)) {
//                var regexExecResult, slokaNumbers = [];
//                while ((regexExecResult = re.exec(sloka.Content)) !== null) {
//                    slokaNumbers.push(regexExecResult[1]);
//                }
//                slokaNumber = slokaNumbers.join(', ');
//            }
//            return ((slokaNumber.toString().indexOf(',') > -1) ? 'ശ്ലോകങ്ങൾ ' : 'ശ്ലോകം ') + slokaNumber;
//        },
        String slokaNumber = Integer.toString(sectionIndex + 1);
        String sectionTitle;
        return String.format("%s - %s", chapter.getTitle(), sectionTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_section_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                setResult(RESULT_CANCELED);
                finish();
                return true;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
