package io.github.why168;

import java.util.Collections;
import java.util.List;

/**
 * @author Edwin.Wu edwin.wu05@gmail.com
 * @version v0.9
 * @see
 * @since JDK1.8
 */
public class SearchUtil {
    /**
     * Returns null if no match
     * @param searchList The list to search in
     * @param itemToFind The item to look for
     * @return The matching item of type T
     */
    public static <T extends Comparable<T>> T findMatch(final List<T> searchList, final T itemToFind) {
        final int searchResult = Collections.binarySearch(searchList, itemToFind);
        if (Math.abs(searchResult) >= searchList.size() || searchResult < 0) return null;
        return searchList.get(searchResult);
    }

    /**
     * Returns null if no suggestion is found
     * @param searchList The list to search in
     * @param itemToFind The item to look for
     * @return The suggestion of type String
     */
    public static String findStringSuggestion(final List<String> searchList, final String itemToFind) {
        final int searchResult = Collections.binarySearch(searchList, itemToFind);
        final int absoluteIndex = Math.abs(searchResult);

        //If the insertion point is >= searchList.size(), compare it to the last item
        if (absoluteIndex >= searchList.size()) {
            final String suggestion = searchList.get(searchList.size() - 1);
            return suggestion.startsWith(itemToFind) ? suggestion : null;
        }

        final int index = searchResult < 0 ? absoluteIndex - 1 : searchResult;
        final String suggestion = searchList.get(index);
        return suggestion != null && suggestion.startsWith(itemToFind) ? suggestion : null;
    }
}
