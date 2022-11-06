package ru.nsu.krasnikov;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SubStringSearch {
    private FileInputStream file;
    private final String pattern;
    private final Integer patternLength;
    private final Integer[] patternZFunction;
    private final char[] patternInChars;

    public SubStringSearch(String fileName, String pattern) throws FileNotFoundException {
        file = null;
        try {
            file = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
        this.pattern = pattern;
        this.patternLength = pattern.length();
        this.patternZFunction = new Integer[patternLength];
        this.patternInChars = pattern.toCharArray();
        calcPatternZFunction();
    }

    private void calcPatternZFunction() {
        int i = 0;
        int l = 0;
        int r = 0;
        char[] chars = pattern.toCharArray();
        for (char ignored : chars) {
            if (i == 0) {
                patternZFunction[0] = 0;
                i++;
                continue;
            }
            patternZFunction[i] = (r > i) ?
                    Integer.min(patternZFunction[i - l], r - i) : 0;

            while (i + patternZFunction[i] < patternLength
                    && chars[patternZFunction[i]] == chars[i + patternZFunction[i]]) {
                patternZFunction[i]++;
            }
            if (l + patternZFunction[i] > r) {
                l = i;
                r = i + patternZFunction[i];
            }
            i++;
        }
    }

    public List<Integer> findIndexes() throws IOException {
        List<Integer> allEntries = new ArrayList<>();
        List<Integer> charBuffer = new ArrayList<>();

        int leftBorder = 0,
                rightBorder = 0,
                symbol,
                zCurrent,
                currentIndex = 0;

        for (int i = 0; i < patternLength; i++) {
            charBuffer.add(file.read());
        }

        while (true) {
            zCurrent = (currentIndex < rightBorder) ?
                    Integer.min(patternZFunction[currentIndex - leftBorder], rightBorder - currentIndex) : 0;

            while (zCurrent < patternLength
                    && charBuffer.get(zCurrent) == patternInChars[zCurrent]) {
                zCurrent++;
            }

            if (currentIndex + zCurrent > rightBorder) {
                leftBorder = currentIndex;
                rightBorder = currentIndex + zCurrent;
            }

            if (zCurrent == patternLength) {
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
