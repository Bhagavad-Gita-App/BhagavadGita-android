package com.floydpink.android.bhagavadgita.helpers;

import com.floydpink.android.bhagavadgita.data.ChapterSection;
import com.floydpink.android.bhagavadgita.data.SectionType;

import java.util.ArrayList;

/**
 * Created by hari on 12/22/14.
 */
public class ChapterHelper {
    public static String getChapterTitle(ArrayList<ChapterSection> mChapterSections) {
        for (ChapterSection section : mChapterSections) {
            if (section.Type == SectionType.Title) {
                return section.Content;
            }
        }
        return null;
    }
}
