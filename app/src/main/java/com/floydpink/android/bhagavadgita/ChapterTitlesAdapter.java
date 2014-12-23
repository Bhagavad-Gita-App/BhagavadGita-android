package com.floydpink.android.bhagavadgita;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.floydpink.android.bhagavadgita.data.BookData;

/**
 * Created by hari on 8/21/14.
 */
public class ChapterTitlesAdapter extends SimpleAdapter {

    private Context Context;

    public ChapterTitlesAdapter(Context context) {
        super(context, BookData.ChapterList,
                R.layout.chapter_list_item,
                new String[]{"subtitle", "title"},
                new int[]{android.R.id.text1, android.R.id.text2});
        setContext(context);
    }

    public Context getContext() {
        return Context;
    }

    public void setContext(Context context) {
        Context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        Typeface malayalamFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/AnjaliOldLipi.ttf");
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setTypeface(malayalamFont);
        textView = (TextView) view.findViewById(android.R.id.text2);
        textView.setTypeface(malayalamFont);
        return view;
    }
}
