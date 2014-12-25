package com.floydpink.android.bhagavadgita.helpers;

import com.floydpink.android.bhagavadgita.data.BookData;
import com.floydpink.android.bhagavadgita.data.ChapterSection;
import com.floydpink.android.bhagavadgita.data.SectionType;
import com.floydpink.android.bhagavadgita.models.Chapter;

import java.util.ArrayList;

/**
 * Created by hari on 12/22/14.
 */
public class ChapterHelper {
    public static String getChapterTitleFromChapterSections(ArrayList<ChapterSection> mChapterSections) {
        for (ChapterSection section : mChapterSections) {
            if (section.Type == SectionType.Title) {
                return section.Content;
            }
        }
        return null;
    }

    public static int getChapterIndexFromChapterName(String chapterName) {
        return BookData.ChapterIndexes.get(chapterName);
    }

    public static Chapter getChapterFromChapterIndex(int chapterIndex) {
        return BookData.Book.getChapters().get(chapterIndex);
    }
}
