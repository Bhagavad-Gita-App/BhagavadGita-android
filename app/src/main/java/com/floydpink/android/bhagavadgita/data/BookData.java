package com.floydpink.android.bhagavadgita.data;

import android.content.res.Resources;

import com.floydpink.android.bhagavadgita.App;
import com.floydpink.android.bhagavadgita.R;
import com.floydpink.android.bhagavadgita.helpers.JSONResourceReader;
import com.floydpink.android.bhagavadgita.models.Book;
import com.floydpink.android.bhagavadgita.models.Chapter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hari on 8/21/14.
 */
public class BookData {

    public static Book Book = initializeBookFromResource();

    public static Map<String, Chapter> Chapters;

    static {
        Book = initializeBookFromResource();
    }

    private static Book initializeBookFromResource() {
        JSONResourceReader reader = new JSONResourceReader(App.getContext().getResources(), R.raw.gita);

        Book book = reader.constructUsingGson(Book.class);

        Chapters = new HashMap<String, Chapter>();

        for (Chapter chapter : book.getChapters()) {
            Chapters.put(chapter.getName(), chapter);
        }

        return book;
    }


}
