package ru.romanbrazhnikov.sourceprovider;

import io.reactivex.Single;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class SourceProvider {

    private Queue<String> mSourceData = new LinkedList<>();

    private String page1 =
            "<table>\n" +
                    "  <tr>\n" +
                    "    <td>Left top</td>\n" +
                    "    <td>Right top</td>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>Left bottom</td>\n" +
                    "    <td>Right bottom</td>\n" +
                    "  </tr>\n" +
                    "</table>\n";

    private String page2 =
            "<table>\n" +
                    "  <tr>\n" +
                    "    <td class=\"left\">Car: </td>\n" +
                    "    <td>Mazda</td>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td class=\"left\">Glasses: </td>\n" +
                    "    <td>Optic 7</td>\n" +
                    "  </tr>\n" +
                    "</table>\n";

    private String page3 =
            "<table>\n" +
                    "  <tr>\n" +
                    "    <td class=\"left\">Drink: </td>\n" +
                    "    <td>Rum</td>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td class=\"left\">Suit: </td>\n" +
                    "    <td>Versace</td>\n" +
                    "  </tr>\n" +
                    "  <tr class = \"special ad\">\n" +
                    "    <td class=\"left\">Shoes: </td>\n" +
                    "    <td>Jimmy Choo</td>\n" +
                    "  </tr>\n" +
                    "</table>\n";

    public boolean hasMore(){
        return mSourceData.size() > 0;
    }

    public SourceProvider() {
        mSourceData.offer(page1);
        mSourceData.offer(page2);
        mSourceData.offer(page3);
    }

    public Single<String> requestNext() {
        return Single.just(mSourceData.poll()).timeout(1, TimeUnit.SECONDS);
    }
}
