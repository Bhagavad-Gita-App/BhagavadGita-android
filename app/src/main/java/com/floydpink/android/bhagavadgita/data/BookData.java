package com.floydpink.android.bhagavadgita.data;

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

    public static Map<String, Chapter> Chapters;

    public static List<Map<String, String>> ChapterList;

    static {
        Book = initializeBookFromResource();
    }

    private static Book initializeBookFromResource() {
        JSONResourceReader reader = new JSONResourceReader(App.getContext().getResources(), R.raw.gita);

        Book book = reader.constructUsingGson(Book.class);

        Chapters = new HashMap<String, Chapter>();
        ChapterList = new ArrayList<Map<String, String>>();
        for (Chapter chapter : book.getChapters()) {

            Chapters.put(chapter.getName(), chapter);

            Map<String, String> chapterListItem = new HashMap<String, String>(2);
            chapterListItem.put("title", chapter.getTitle());
            chapterListItem.put("subtitle", chapter.getSubtitle());
            ChapterList.add(chapterListItem);
        }

        return book;
    }


}
