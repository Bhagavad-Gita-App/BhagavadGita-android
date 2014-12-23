package com.floydpink.android.bhagavadgita;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Typeface;
import android.opengl.Visibility;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.floydpink.android.bhagavadgita.data.BookData;
import com.floydpink.android.bhagavadgita.helpers.TypefaceSpan;
import com.floydpink.android.bhagavadgita.models.Chapter;
import com.floydpink.android.bhagavadgita.models.Section;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

    /**
     * The title of section to be used while sharing
     */
    private String mSectionShareTitle;

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
        setActivityTitle(sectionIndex, chapter);

        // Show the Up button in the action bar.
        getActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_section_detail);

        //set the content
        Typeface malayalamFont = Typeface.createFromAsset(getAssets(), "fonts/AnjaliOldLipi.ttf");
        TextView speaker = (TextView) findViewById(R.id.speaker);
        String speakerValue = mSection.getSpeaker();
        if (speakerValue != null) {
            speaker.setText(speakerValue);
            speaker.setTypeface(malayalamFont, Typeface.BOLD);
        } else {
            speaker.setVisibility(View.GONE);
        }
        TextView verse = (TextView) findViewById(R.id.verse);
        verse.setText(mSection.getContent());
        verse.setTypeface(malayalamFont, Typeface.BOLD_ITALIC);
        TextView meaning = (TextView) findViewById(R.id.meaning);
        meaning.setText(mSection.getMeaning());
        meaning.setTypeface(malayalamFont, Typeface.NORMAL);
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
            case R.id.action_share:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setActivityTitle(int sectionIndex, Chapter chapter) {
        SpannableString s = new SpannableString(getSectionDetailActivityTitle(chapter, sectionIndex, mSection));
        s.setSpan(new TypefaceSpan(this, "AnjaliOldLipi.ttf"), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Update the action bar title with the TypefaceSpan instance
        ActionBar actionBar = getActionBar();
        assert actionBar != null;
        actionBar.setTitle(s);
    }

    private String getSectionDetailActivityTitle(Chapter chapter, int sectionIndex, Section section) {
        String sectionTitle = getSectionTitle(sectionIndex, section.getContent());
        mSectionShareTitle = String.format("%s%s", chapter.getTitle() + " - ", sectionTitle);
        return String.format("%s%s", getDisplayWidth() > 480 ? chapter.getTitle() + " - " : "",
                sectionTitle);
    }


    private String getSectionTitle(int s, String sloka) {
        String slokaNumber = Integer.toString(s);
        Pattern regex = Pattern.compile("\\(([0-9]+)\\)", Pattern.DOTALL);
        Matcher regexMatcher = regex.matcher(sloka);
        List<String> slokaNumbers = new ArrayList<>();
        while (regexMatcher.find()) {
            slokaNumbers.add(regexMatcher.group(1));
        }
        if (slokaNumbers.size() > 0) {
            slokaNumber = TextUtils.join(", ", slokaNumbers);
        }
        return ((slokaNumber.indexOf(',') > -1) ? "ശ്ലോകങ്ങൾ " : "ശ്ലോകം ") + slokaNumber;
    }

    private float getDisplayWidth() {
        // http://stackoverflow.com/a/11755265
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        // float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        Log.d("Display Width (in dp):", Float.toString(dpWidth));
        return dpWidth;
    }

}
