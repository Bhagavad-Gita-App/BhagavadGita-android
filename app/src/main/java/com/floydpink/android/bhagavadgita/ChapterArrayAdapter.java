package com.floydpink.android.bhagavadgita;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hari on 8/21/14.
 */
public class ChapterArrayAdapter<T> extends ArrayAdapter<T> {

    public ChapterArrayAdapter(Context context, List<T> objects) {
        super(context, android.R.layout.simple_list_item_activated_1, android.R.id.text1, objects);
    }

    public ChapterArrayAdapter(Context context, T[] objects) {
        super(context, android.R.layout.simple_list_item_activated_1, android.R.id.text1, objects);
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/AnjaliOldLipi.ttf"));
        return view;
    }
}
