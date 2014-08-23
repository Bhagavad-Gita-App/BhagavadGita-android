package com.floydpink.android.bhagavadgita.models;

/**
 * Created by hari on 8/20/14.
 */
public class Section {
    private int SectionCount;
    private int SlokaCount;
    private String Content;
    private String Meaning;

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

    public Section(int sectionCount, int slokaCount, String content, String meaning) {
        SectionCount = sectionCount;
        SlokaCount = slokaCount;
        Content = content;
        Meaning = meaning;
    }

    @Override public String toString() {
        return Content;
    }
}
