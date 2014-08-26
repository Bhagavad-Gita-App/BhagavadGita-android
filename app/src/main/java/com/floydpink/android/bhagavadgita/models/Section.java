package com.floydpink.android.bhagavadgita.models;

/**
 * Created by hari on 8/20/14.
 */
public class Section {
    private int SectionCount;
    private int SlokaCount;
    private String Content;
    private String Meaning;
    private String Speaker;

    public int getSectionCount() {
        return SectionCount;
    }

    public void setSectionCount(int sectionCount) {
        SectionCount = sectionCount;
    }

    public int getSlokaCount() {
        return SlokaCount;
    }

    public void setSlokaCount(int slokaCount) {
        SlokaCount = slokaCount;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMeaning() {
        return Meaning;
    }

    public void setMeaning(String meaning) {
        Meaning = meaning;
    }

    public String getSpeaker() {
        return Speaker;
    }

    public void setSpeaker(String speaker) {
        Speaker = speaker;
    }

    public Section(int sectionCount, int slokaCount, String content, String meaning, String speaker) {
        SectionCount = sectionCount;
        SlokaCount = slokaCount;
        Content = content;
        Meaning = meaning;
        Speaker = speaker;
    }

    @Override public String toString() {
        return Content;
    }
}
