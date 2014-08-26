package com.floydpink.android.bhagavadgita;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.floydpink.android.bhagavadgita.data.BookData;
import com.floydpink.android.bhagavadgita.helpers.TypefaceSpan;
import com.floydpink.android.bhagavadgita.models.Chapter;


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
            assert actionBar != null;
            actionBar.setTitle(s);

            // Populate the list with the sections in the chapter
            ListView sectionsList = (ListView) rootView.findViewById(android.R.id.list);
            sectionsList.setAdapter(new SectionArrayAdapter(getActivity(), mChapter));

        }

        return rootView;
    }
}
