package com.floydpink.android.bhagavadgita.data;

import android.text.TextUtils;

import com.floydpink.android.bhagavadgita.App;
import com.floydpink.android.bhagavadgita.R;
import com.floydpink.android.bhagavadgita.helpers.JSONResourceReader;
import com.floydpink.android.bhagavadgita.models.Book;
import com.floydpink.android.bhagavadgita.models.Chapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hari on 8/21/14.
 */
public class BookData {

    public static Book Book;

    public static List<Map<String, String>> ChapterList;

    public static LinkedHashMap<String, ArrayList<ChapterSection>> Chapters;

    public static Map<String, Integer> ChapterIndexes;

    static {
        Book = initializeBookFromResource();
    }

    private static Book initializeBookFromResource() {
        JSONResourceReader reader = new JSONResourceReader(App.getContext().getResources(), R.raw.gita);

        Book book = reader.constructUsingGson(Book.class);

        ChapterList = new ArrayList<>();
        Chapters = new LinkedHashMap<>();
        ChapterIndexes = new HashMap<>();

        int chapterIndex = 0;

        for (Chapter chapter : book.getChapters()) {

            Chapters.put(chapter.getName(), hydrateChapterSections(chapter));

            ChapterIndexes.put(chapter.getName(), chapterIndex);

            Map<String, String> chapterListItem = new HashMap<String, String>(2);
            chapterListItem.put("title", chapter.getTitle());
            chapterListItem.put("subtitle", chapter.getSubtitle());
            ChapterList.add(chapterListItem);

            chapterIndex++;
        }

        return book;
    }

    private static ArrayList<ChapterSection> hydrateChapterSections(Chapter chapter) {
        ArrayList<ChapterSection> chapterSections = new ArrayList<ChapterSection>();
        String chapterIntro = chapter.getIntro();
        if (!TextUtils.isEmpty(chapterIntro)) {
            ChapterSection intro = new ChapterSection();
            intro.Type = SectionType.Intro;
            intro.Content = chapterIntro;
            chapterSections.add(intro);
        }

        String chapterTitle = chapter.getTitle();
        if (!TextUtils.isEmpty(chapterTitle)) {
            ChapterSection title = new ChapterSection();
            title.Type = SectionType.Title;
            title.Content = chapterTitle;
            chapterSections.add(title);
        }

        int sectionCount = 0;
        for (com.floydpink.android.bhagavadgita.models.Section section : chapter.getSections()) {
            String sectionSpeaker = section.getSpeaker();
            if (!TextUtils.isEmpty(sectionSpeaker)) {
                ChapterSection speaker = new ChapterSection();
                speaker.Type = SectionType.Speaker;
                speaker.Content = sectionSpeaker;
                speaker.OriginalSection = Integer.toString(sectionCount);
                chapterSections.add(speaker);
            }

            String sectionVerse = section.getContent();
            if (!TextUtils.isEmpty(sectionVerse)) {
                ChapterSection verse = new ChapterSection();
                verse.Type = SectionType.Verse;
                verse.Content = sectionVerse;
                verse.OriginalSection = Integer.toString(sectionCount);
                chapterSections.add(verse);
            }

            String sectionMeaning = section.getMeaning();
            if (!TextUtils.isEmpty(sectionMeaning)) {
                ChapterSection meaning = new ChapterSection();
                meaning.Type = SectionType.Meaning;
                meaning.Content = sectionMeaning;
                meaning.OriginalSection = Integer.toString(sectionCount);
                chapterSections.add(meaning);
            }
            sectionCount++;
        }

        String chapterOutro = chapter.getOutro();
        if (!TextUtils.isEmpty(chapterOutro)) {
            ChapterSection outro = new ChapterSection();
            outro.Type = SectionType.Outro;
            outro.Content = chapterOutro;
            chapterSections.add(outro);
        }

        return chapterSections;
    }

}
