package com.floydpink.android.bhagavadgita.models;

import java.util.List;

/**
 * Created by hari on 8/20/14.
 */
public class Chapter {
    private String Name;
    private String Title;
    private int ChapterCount;
    private String Intro;
    private String Outro;
    private List<Paragraph> Contents;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getChapterCount() {
        return ChapterCount;
    }

    public void setChapterCount(int chapterCount) {
        ChapterCount = chapterCount;
    }

    public String getIntro() {
        return Intro;
    }

    public void setIntro(String intro) {
        Intro = intro;
    }

    public String getOutro() {
        return Outro;
    }

    public void setOutro(String outro) {
        Outro = outro;
    }

    public List<Paragraph> getContents() {
        return Contents;
    }

    public void setContents(List<Paragraph> contents) {
        Contents = contents;
    }

    public Chapter(String name, String title, int chapterCount, String intro, String outro, List<Paragraph> contents) {
        Name = name;
        Title = title;
        ChapterCount = chapterCount;
        Intro = intro;
        Outro = outro;
        Contents = contents;
    }

    @Override public String toString() {
        return Title;
    }
}
