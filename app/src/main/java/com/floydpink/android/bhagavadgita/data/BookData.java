package com.floydpink.android.bhagavadgita.data;

import android.text.TextUtils;

import com.floydpink.android.bhagavadgita.App;
import com.floydpink.android.bhagavadgita.R;
import com.floydpink.android.bhagavadgita.helpers.JSONResourceReader;
import com.floydpink.android.bhagavadgita.models.Book;
import com.floydpink.android.bhagavadgita.models.Chapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hari on 8/21/14.
 */
public class BookData {

    public static Book Book = initializeBookFromResource();

    public static List<Map<String, String>> ChapterList;

    public static Map<String, ArrayList<ChapterSection>> Chapters;

    static {
        Book = initializeBookFromResource();
    }

    private static Book initializeBookFromResource() {
        JSONResourceReader reader = new JSONResourceReader(App.getContext().getResources(), R.raw.gita);

        Book book = reader.constructUsingGson(Book.class);

        ChapterList = new ArrayList<Map<String, String>>();
        Chapters = new HashMap<String, ArrayList<ChapterSection>>();
        for (Chapter chapter : book.getChapters()) {

            Chapters.put(chapter.getName(), hydrateChapterSections(chapter));

            Map<String, String> chapterListItem = new HashMap<String, String>(2);
            chapterListItem.put("title", chapter.getTitle());
            chapterListItem.put("subtitle", chapter.getSubtitle());
            ChapterList.add(chapterListItem);
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

        for (com.floydpink.android.bhagavadgita.models.Section section : chapter.getSections()) {
            String sectionSpeaker = section.getSpeaker();
            if (!TextUtils.isEmpty(sectionSpeaker)) {
                ChapterSection speaker = new ChapterSection();
                speaker.Type = SectionType.Speaker;
                speaker.Content = sectionSpeaker;
                chapterSections.add(speaker);
            }

            String sectionVerse = section.getContent();
            if (!TextUtils.isEmpty(sectionVerse)) {
                ChapterSection verse = new ChapterSection();
                verse.Type = SectionType.Verse;
                verse.Content = sectionVerse;
                chapterSections.add(verse);
            }

            String sectionMeaning = section.getMeaning();
            if (!TextUtils.isEmpty(sectionMeaning)) {
                ChapterSection meaning = new ChapterSection();
                meaning.Type = SectionType.Meaning;
                meaning.Content = sectionMeaning;
                chapterSections.add(meaning);
            }
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
