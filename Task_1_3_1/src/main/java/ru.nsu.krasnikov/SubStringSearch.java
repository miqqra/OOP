package ru.nsu.krasnikov;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for searching substring in file.
 */
public class SubStringSearch {
    private FileInputStream file;
    private final String pattern;
    private final Integer patternLength;
    private final Integer[] patternZfunction;
    private final char[] patternInChars;

    /**
     * class constructor for searching substring.
     *
     * @param fileName name of the file, where we must search a substring.
     * @param pattern  substring, we need to find.
     * @throws FileNotFoundException if file is not found in directory.
     */
    public SubStringSearch(String fileName, String pattern) throws FileNotFoundException {
        file = null;
        try {
            file = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
        this.pattern = pattern;
        this.patternLength = pattern.length();
        this.patternZfunction = new Integer[patternLength];
        this.patternInChars = pattern.toCharArray();
        calcPatternZfunction();
    }

    private void calcPatternZfunction() {
        int i = 0;
        int l = 0;
        int r = 0;
        char[] chars = pattern.toCharArray();
        for (char ignored : chars) {
            if (i == 0) {
                patternZfunction[0] = 0;
                i++;
                continue;
            }
            patternZfunction[i] = (r > i)
                    ? Integer.min(patternZfunction[i - l], r - i)
                    : 0;

            while (i + patternZfunction[i] < patternLength
                    && chars[patternZfunction[i]] == chars[i + patternZfunction[i]]) {
                patternZfunction[i]++;
            }
            if (l + patternZfunction[i] > r) {
                l = i;
                r = i + patternZfunction[i];
            }
            i++;
        }
    }

    /**
     * Find indexes of pettern entries into file.
     *
     * @return list if indexes.
     * @throws IOException if file is not found or some errors with reading from file.
     */
    public List<Long> findIndexes() throws IOException {
        List<Long> allEntries = new ArrayList<>();
        List<Integer> charBuffer = new ArrayList<>();

        long leftBorder = 0L;
        long rightBorder = 0L;
        int symbol;
        int currentZfunction;
        long currentIndex = 0L;

        for (int i = 0; i < patternLength; i++) {
            charBuffer.add(file.read());
        }

        while (true) {
            currentZfunction = (int) ((currentIndex < rightBorder)
                    ? Long.min(
                    patternZfunction[(int) (currentIndex - leftBorder)],
                    rightBorder - currentIndex)
                    : 0L);

            while (currentZfunction < patternLength
                    && charBuffer.get(currentZfunction) == patternInChars[currentZfunction]) {
                currentZfunction++;
            }

            if (currentIndex + currentZfunction > rightBorder) {
                leftBorder = currentIndex;
                rightBorder = currentIndex + currentZfunction;
            }

            if (currentZfunction == patternLength) {
                allEntries.add(currentIndex);
            }

            charBuffer.remove(0);
            if ((symbol = file.read()) == -1) {
                return allEntries;
            }
            charBuffer.add(symbol);
            currentIndex++;
        }
    }
}
