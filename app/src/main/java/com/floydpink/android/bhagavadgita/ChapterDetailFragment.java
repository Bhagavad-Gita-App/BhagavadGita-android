package com.floydpink.android.bhagavadgita;

import android.app.ActionBar;
import android.app.ListFragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.floydpink.android.bhagavadgita.data.BookData;
import com.floydpink.android.bhagavadgita.helpers.TypefaceSpan;
import com.floydpink.android.bhagavadgita.models.Chapter;
import com.floydpink.android.bhagavadgita.models.Section;


/**
 * A fragment representing a single Chapter detail screen.
 * This fragment is either contained in a {@link ChapterListActivity}
 * in two-pane mode (on tablets) or a {@link ChapterDetailActivity}
 * on handsets.
 */
public class ChapterDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The chapter this fragment is presenting.
     */
    private Chapter mChapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ChapterDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the chapter specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mChapter = BookData.Chapters.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chapter_detail, container, false);

        // Show the chapter as text in a TextView.
        if (mChapter != null) {
            // set the malayalam title on the parent activity
            SpannableString s = new SpannableString(mChapter.getTitle());
            s.setSpan(new TypefaceSpan(getActivity(), "AnjaliOldLipi.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            // Update the action bar title with the TypefaceSpan instance
            ActionBar actionBar = getActivity().getActionBar();
            actionBar.setTitle(s);

            Typeface anjaliOldLipi = Typeface.createFromAsset(getActivity().getAssets(), "fonts/AnjaliOldLipi.ttf");

            // Chapter Intro
            TextView textView = (TextView) rootView.findViewById(R.id.chapter_intro);
            String intro = mChapter.getIntro();
            if (intro != null) {
                textView.setText(intro);
                textView.setTypeface(anjaliOldLipi);
            } else {
                textView.setVisibility(View.INVISIBLE);
                textView.getLayoutParams().height = 1;
            }

            // Chapter Title
            textView = (TextView) rootView.findViewById(R.id.chapter_title);
            textView.setText(mChapter.getTitle());
            textView.setTypeface(anjaliOldLipi);

            // Sections
            ListView sectionsList = (ListView) rootView.findViewById(android.R.id.list);
            sectionsList.setAdapter(new SectionArrayAdapter<Section>(
                    getActivity(),
                    mChapter.getSections()));

            // Add header and footer to the sections


            // Chapter Outtro
            textView = (TextView) rootView.findViewById(R.id.chapter_outtro);
            String outro = mChapter.getOutro();
            if (outro != null) {
                textView.setText(outro);
                textView.setTypeface(anjaliOldLipi);
            } else {
                textView.setVisibility(View.INVISIBLE);
                textView.getLayoutParams().height = 1;
            }
        }

        return rootView;
    }
}
