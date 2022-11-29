package ru.nsu.krasnikov;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for searching substring in file.
 */
public class SubStringSearch {
    private InputStream mainStringStream;
    private InputStream patternStream;
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
        this.mainStringStream = null;
        try {
            mainStringStream = new FileInputStream(file);
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
        this.mainStringStream = new ByteArrayInputStream(string.getBytes());
        initFields(pattern);
    }

    /**
     * class constructor for searching substring.
     *
     * @param stream  stream, where we must search a substring.
     * @param pattern substring, we need to find.
     */
    public SubStringSearch(InputStream stream, String pattern) {
        this.mainStringStream = stream;
        initFields(pattern);
    }

    private void initFields(String pattern) {
        this.patternLength = pattern.length();
        this.patternZfunction = new Long[patternLength];
        this.patternInChars = pattern.toCharArray();
        this.patternStream = new ByteArrayInputStream(pattern.getBytes());
    }

    /**
     * Find indexes of pattern entries into file.
     *
     * @return list of indexes where pattern was found.
     * @throws IOException if file is not found or some errors with reading from file.
     */
    public List<Long> findIndexes() throws IOException {

        long currentZfunction;
        long currentIndex = 1L;
        long leftBorder = 0L;
        long rightBorder = 0L;
        int symbol;
        long streamIdx = 0L;
        InputStream input = new SequenceInputStream(patternStream, mainStringStream);
        List<Long> allEntries = new ArrayList<>();
        List<Integer> charBuffer = new ArrayList<>();

        patternZfunction[0] = 0L;
        for (int i = 0; i < patternLength; i++) {
            charBuffer.add(input.read());
        }
        charBuffer.remove(0);
        charBuffer.add((int) '\0');
        while (true) {
            currentZfunction = ((rightBorder > currentIndex)
                    ? Long.min(
                    patternZfunction[(int) (currentIndex - leftBorder)],
                    rightBorder - currentIndex)
                    : 0L);
            if (currentIndex < patternLength) {
                patternZfunction[(int) currentIndex] = currentZfunction;
            }
            while (currentZfunction < patternLength
                    && charBuffer.get((int) currentZfunction)
                    == patternInChars[(int) currentZfunction]) {
                currentZfunction++;
            }
            if (currentIndex < patternLength) {
                patternZfunction[(int) currentIndex] = currentZfunction;
            }
            if (currentIndex + currentZfunction > rightBorder) {
                leftBorder = currentIndex;
                rightBorder = currentIndex + currentZfunction;
            }
            if (currentZfunction == patternLength) {
                allEntries.add(currentIndex);
            }
            charBuffer.remove(0);
            if ((symbol = input.read()) == -1) {
                input.close();
                return allEntries;
            }
            charBuffer.add(symbol);
            currentIndex++;
            streamIdx++;
            if (streamIdx == patternLength) {
                rightBorder = leftBorder = currentIndex = 0L;
            }
        }
    }
}
