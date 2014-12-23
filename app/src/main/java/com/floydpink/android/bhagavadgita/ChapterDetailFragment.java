package com.floydpink.android.bhagavadgita;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.floydpink.android.bhagavadgita.data.BookData;
import com.floydpink.android.bhagavadgita.data.ChapterSection;
import com.floydpink.android.bhagavadgita.data.SectionType;
import com.floydpink.android.bhagavadgita.helpers.TypefaceSpan;

import java.util.ArrayList;


/**
 * A fragment representing a single Chapter detail screen.
 * This fragment is either contained in a {@link ChapterListActivity}
 * in two-pane mode (on tablets) or a {@link ChapterDetailActivity}
 * on handsets.
 */
public class ChapterDetailFragment extends ListFragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_CHAPTER_NAME = "chapter_name";

    /**
     * The title of the book or the title of the parent activity in two-pane mode.
     */
    private String mBookTitle;

    /**
     * The title of the chapter this fragment is presenting.
     */
    private String mChapterTitle;

    /**
     * The sections of the chapter this fragment is presenting.
     */
    private ArrayList<ChapterSection> mChapterSections;

    /**
     * The index in the original JSON's Chapters collection for the chapter this fragment is presenting.
     */
    private int mChapterIndex;

    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks mCallbacks = sDummyCallbacks;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         */
        public void onItemSelected(String chapterAndVerse);
    }

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String id) {
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ChapterDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_CHAPTER_NAME)) {
            // Load the chapter specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            String chapterName = getArguments().getString(ARG_CHAPTER_NAME);
            mChapterSections = BookData.Chapters.get(chapterName);
            mChapterTitle = getChapterTitle(mChapterSections);
            mChapterIndex = BookData.ChapterIndexes.get(chapterName);
        }

        setHasOptionsMenu(true);
    }

    private String getChapterTitle(ArrayList<ChapterSection> mChapterSections) {
        for (ChapterSection section : mChapterSections) {
            if (section.Type == SectionType.Title) {
                return section.Content;
            }
        }
        return null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_section_detail, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chapter_detail, container, false);

        // Show the chapter as text in a TextView.
        if (mChapterSections != null) {
            Activity parentActivity = getActivity();

            // set the chapter title in malayalam on the activity if we are in single pane mode,
            // else append the chapter title to the book title
            String newTitle = mChapterTitle;
            if (parentActivity instanceof ChapterListActivity) {
                if (mBookTitle == null) {
                    mBookTitle = parentActivity.getTitle().toString();
                }
                newTitle = mBookTitle + " - " + newTitle;
            }

            SpannableString s = new SpannableString(newTitle);
            s.setSpan(new TypefaceSpan(parentActivity, "AnjaliOldLipi.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            // Update the action bar title with the TypefaceSpan instance
            ActionBar actionBar = parentActivity.getActionBar();
            assert actionBar != null;
            actionBar.setTitle(s);

            // Populate the list with the sections in the chapter
            ListView sectionsList = (ListView) rootView.findViewById(android.R.id.list);
            sectionsList.setAdapter(new ChapterSectionsAdapter(parentActivity, mChapterSections));

        }

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        String selectedChapterAndSection = String.format("c=%s&s=%s", mChapterIndex, getSectionIndex(position));

        mCallbacks.onItemSelected(selectedChapterAndSection);
    }

    private String getSectionIndex(int position) {
        return mChapterSections.get(position).OriginalSection;
    }
}
