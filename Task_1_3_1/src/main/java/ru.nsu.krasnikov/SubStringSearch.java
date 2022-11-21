package ru.nsu.krasnikov;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for searching substring in file.
 */
public class SubStringSearch {
    private InputStream stream;
    private String pattern;
    private Integer patternLength;
    private Long[] patternZfunction;
    private char[] patternInChars;

    /**
     * class constructor for searching substring.
     *
     * @param file    file, where we must search a substring.
     * @param pattern substring, we need to find.
     * @throws FileNotFoundException if file is not found in directory.
     */
    public SubStringSearch(File file, String pattern) throws FileNotFoundException {
        this.stream = null;
        try {
            stream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
        initFields(pattern);
    }

    /**
     * class constructor for searching substring.
     *
     * @param string  string, where we must search a substring.
     * @param pattern substring, we need to find.
     */
    public SubStringSearch(String string, String pattern) {
        this.stream = new ByteArrayInputStream(string.getBytes());
        initFields(pattern);
    }

    /**
     * class constructor for searching substring.
     *
     * @param stream stream, where we must search a substring.
     * @param pattern substring, we need to find.
     */
    public SubStringSearch(InputStream stream, String pattern) {
        this.stream = stream;
        initFields(pattern);
    }

    private void initFields(String pattern) {
        this.pattern = pattern;
        this.patternLength = pattern.length();
        this.patternZfunction = new Long[patternLength];
        this.patternInChars = pattern.toCharArray();
    }

    /**
     * Find indexes of pattern entries into file.
     *
     * @return list of indexes where pattern was found.
     * @throws IOException if file is not found or some errors with reading from file.
     */
    public List<Long> findIndexes() throws IOException {

        int i = 0;
        long leftBorder = 0L;
        long rightBorder = 0L;
        char[] chars = pattern.toCharArray();
        for (int j = 0; j < chars.length; j++) {
            if (i == 0) {
                patternZfunction[0] = 0L;
                i++;
                continue;
            }
            patternZfunction[i] = (rightBorder > i)
                    ? Long.min(patternZfunction[(int) (i - leftBorder)], rightBorder - i)
                    : 0;

            while (i + patternZfunction[i] < patternLength
                    && chars[patternZfunction[i].intValue()] ==
                    chars[(int) (i + patternZfunction[i])]) {
                patternZfunction[i]++;
            }
            if (leftBorder + patternZfunction[i] > rightBorder) {
                leftBorder = i;
                rightBorder = i + patternZfunction[i];
            }
            i++;
        }

        List<Long> allEntries = new ArrayList<>();
        List<Integer> charBuffer = new ArrayList<>();

        leftBorder = 0L;
        rightBorder = 0L;
        int symbol;
        int currentZfunction;
        long currentIndex = 0L;

        for (i = 0; i < patternLength; i++) {
            charBuffer.add(stream.read());
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
            if ((symbol = stream.read()) == -1) {
                stream.close();
                return allEntries;
            }
            charBuffer.add(symbol);
            currentIndex++;
        }
    }
}
