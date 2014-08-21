package com.floydpink.android.bhagavadgita.models;

/**
 * Created by hari on 8/20/14.
 */
public class Paragraph {
    private ParagraphType Type;
    private int ContentCount;
    private int SlokaCount;
    private String Content;
    private String Meaning;

    public ParagraphType getType() {
        return Type;
    }

    public void setType(ParagraphType type) {
        Type = type;
    }

    public int getContentCount() {
        return ContentCount;
    }

    public void setContentCount(int contentCount) {
        ContentCount = contentCount;
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

    public Paragraph(ParagraphType type, int contentCount, int slokaCount, String content, String meaning) {
        Type = type;
        ContentCount = contentCount;
        SlokaCount = slokaCount;
        Content = content;
        Meaning = meaning;
    }
}
