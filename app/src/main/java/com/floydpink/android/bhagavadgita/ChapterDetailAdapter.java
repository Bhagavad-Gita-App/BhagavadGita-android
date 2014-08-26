package com.floydpink.android.bhagavadgita;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.floydpink.android.bhagavadgita.models.Chapter;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by hari on 8/23/14.
 */
public class ChapterDetailAdapter extends BaseAdapter {

    private ArrayList<Section> mChapterSections = new ArrayList<Section>();
    private LayoutInflater mInflater;
    private Context Context;

    public Context getContext() {
        return Context;
    }

    public void setContext(Context context) {
        Context = context;
    }

    public ChapterDetailAdapter(Context context, Chapter chapter) {
        setContext(context);
        mInflater = (LayoutInflater) context.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);
        hydrateAdapter(chapter);
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
        Typeface anjaliOldLipi = Typeface.createFromAsset(getContext().getAssets(), "fonts/AnjaliOldLipi.ttf");
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
        holder.textView.setTypeface(anjaliOldLipi, sectionStyle);
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
        return mChapterSections.get(position).type;
    }

    private void hydrateAdapter(Chapter chapter) {
        String chapterIntro = chapter.getIntro();
        if (!TextUtils.isEmpty(chapterIntro)) {
            Section intro = new Section();
            intro.type = SectionType.Intro;
            intro.Content = chapterIntro;
            mChapterSections.add(intro);
        }

        String chapterTitle = chapter.getTitle();
        if (!TextUtils.isEmpty(chapterTitle)) {
            Section title = new Section();
            title.type = SectionType.Title;
            title.Content = chapterTitle;
            mChapterSections.add(title);
        }

        for (com.floydpink.android.bhagavadgita.models.Section section : chapter.getSections()) {
            String sectionSpeaker = section.getSpeaker();
            if (!TextUtils.isEmpty(sectionSpeaker)) {
                Section speaker = new Section();
                speaker.type = SectionType.Speaker;
                speaker.Content = sectionSpeaker;
                mChapterSections.add(speaker);
            }

            String sectionVerse = section.getContent();
            if (!TextUtils.isEmpty(sectionVerse)) {
                Section verse = new Section();
                verse.type = SectionType.Verse;
                verse.Content = sectionVerse;
                mChapterSections.add(verse);
            }

            String sectionMeaning = section.getMeaning();
            if (!TextUtils.isEmpty(sectionMeaning)) {
                Section meaning = new Section();
                meaning.type = SectionType.Meaning;
                meaning.Content = sectionMeaning;
                mChapterSections.add(meaning);
            }
        }

        String chapterOutro = chapter.getOutro();
        if (!TextUtils.isEmpty(chapterOutro)) {
            Section outro = new Section();
            outro.type = SectionType.Outro;
            outro.Content = chapterOutro;
            mChapterSections.add(outro);
        }
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

    private enum SectionType {
        Intro, Title, Speaker, Verse, Meaning, Outro
    }

    private class Section {
        public SectionType type;
        public String Content;
    }

    private class ViewHolder {
        public TextView textView;
    }

}
