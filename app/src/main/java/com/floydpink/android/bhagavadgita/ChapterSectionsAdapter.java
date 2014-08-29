package com.floydpink.android.bhagavadgita;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.floydpink.android.bhagavadgita.data.ChapterSection;
import com.floydpink.android.bhagavadgita.data.SectionType;

import java.util.ArrayList;

/**
 * Created by hari on 8/23/14.
 */
public class ChapterSectionsAdapter extends BaseAdapter {

    private ArrayList<ChapterSection> mChapterSections = new ArrayList<ChapterSection>();
    private LayoutInflater mInflater;
    private Context Context;

    public ChapterSectionsAdapter(Context context, ArrayList<ChapterSection> chapter) {
        Context = context;
        mChapterSections = chapter;
        mInflater = (LayoutInflater) context.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mChapterSections.size();
    }

    @Override
    public Object getItem(int position) {
        return mChapterSections.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        Typeface malayalamFont = Typeface.createFromAsset(Context.getAssets(), "fonts/AnjaliOldLipi.ttf");
        SectionType sectionType = getSectionTypeForPosition(position);
        int sectionStyle;
        switch (sectionType) {
            case Verse:
                sectionStyle = Typeface.BOLD_ITALIC;
                break;
            case Speaker:
            case Intro:
            case Outro:
                sectionStyle = Typeface.BOLD;
                break;
            default:
                sectionStyle = Typeface.NORMAL;
        }
        convertView = getViewForSection(getItemViewType(position));
        holder.textView = (TextView) convertView.findViewById(R.id.text);
        convertView.setTag(holder);
        holder.textView.setText(mChapterSections.get(position).Content);
        holder.textView.setTypeface(malayalamFont, sectionStyle);
        return convertView;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutId(getSectionTypeForPosition(position));
    }

    private SectionType getSectionTypeForPosition(int position) {
        return mChapterSections.get(position).Type;
    }

    private View getViewForSection(int resource) {
        return mInflater.inflate(resource, null);
    }

    private int getLayoutId(SectionType sectionType) {
        int resource;
        switch (sectionType) {
            case Intro:
                resource = R.layout.chapter_intro;
                break;
            case Title:
                resource = R.layout.chapter_title;
                break;
            case Speaker:
                resource = R.layout.chapter_speaker;
                break;
            case Verse:
                resource = R.layout.chapter_verse;
                break;
            case Meaning:
                resource = R.layout.chapter_meaning;
                break;
            case Outro:
                resource = R.layout.chapter_outro;
                break;
            default:
                resource = R.layout.chapter_verse;
        }
        return resource;
    }

    private class ViewHolder {
        public TextView textView;
    }

}
