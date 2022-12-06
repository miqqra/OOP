package ru.nsu.krasnikov;

import java.util.ArrayList;
import java.util.List;

/**
 * Record book class, stores subject names, what semester they were and scores.
 */
public class RecordBook {
    private final List<Discipline> subjects;
    private Integer qualificationWorkScore;

    /**
     * create record book.
     */
    public RecordBook() {
        subjects = new ArrayList<>();
        this.qualificationWorkScore = null;
    }

    /**
     * add subject into record book.
     *
     * @param name     name of the subject.
     * @param semester what semester it was.
     * @param score    score for that subject in semester.
     */
    public void addSubject(String name, int semester, int score) {
        subjects.add(new Discipline(name, semester, score));
    }

    /**
     * add subject into record book.
     *
     * @param name     name of the subject.
     * @param semester what semester it was.
     * @param score    passed or failed score.
     */
    public void addSubject(String name, int semester, PassedOrFailed score) {
        subjects.add(new Discipline(name, semester, score));
    }

    /**
     * set score for qualification work.
     *
     * @param score score.
     */
    public void setQualificationWorkScore(int score) {
        this.qualificationWorkScore = score;
    }

    /**
     * get average score for certain semester.
     *
     * @param semester number of a semester.
     * @return average score.
     */
    public Double getAverageScore(int semester) {
        return subjects
                .stream()
                .filter(x -> x.semester == semester)
                .filter(x -> x.passedOrFailed == PassedOrFailed.SCORED)
                .mapToDouble(Discipline::getScore)
                .average()
                .orElse(0);
    }

    /**
     * get average score for all semesters.
     *
     * @return average score.
     */
    public Double getAverageScore() {
        return subjects
                .stream()
                .filter(x -> x.passedOrFailed == PassedOrFailed.SCORED)
                .mapToDouble(Discipline::getScore)
                .average()
                .orElse(0);
    }

    /**
     * checks if student can get red diploma after graduating.
     *
     * @return true if he/she can, false - otherwise.
     */
    public boolean canGetRedDiploma() {
        return (this.qualificationWorkScore == null)
                ? !subjects.isEmpty()
                && getAverageScore() >= 4.75
                && subjects.stream().allMatch(Discipline::checkScore)
                : !subjects.isEmpty()
                && getAverageScore() >= 4.75
                && this.qualificationWorkScore == 5
                && subjects.stream().allMatch(Discipline::checkScore);
    }

    /**
     * checks if student can get increased scholarship for certain semester.
     *
     * @param semester number of semester.
     * @return true if he/she can
     */
    public boolean canGetIncreasedScholarship(int semester) {
        return !subjects.isEmpty()
                && subjects
                .stream()
                .filter(x -> x.semester == semester)
                .allMatch(Discipline::checkScore);
    }


    private static class Discipline {
        private final String name;
        private final int semester;
        private final int score;
        private final PassedOrFailed passedOrFailed;

        private Discipline(String name, int semester, int score) {
            this.name = name;
            this.semester = semester;
            this.score = score;
            this.passedOrFailed = PassedOrFailed.SCORED;
        }

        private Discipline(String name, int semester, PassedOrFailed passedOrFailed) {
            this.name = name;
            this.semester = semester;
            this.score = -1;
            this.passedOrFailed = passedOrFailed;
        }

        private int getScore() {
            return score;
        }

        private boolean checkScore() {
            return this.passedOrFailed == PassedOrFailed.SCORED
                    ? this.score > 3
                    : this.passedOrFailed == PassedOrFailed.PASSED;
        }
    }

    /**
     * passed or failed scores.
     */
    public enum PassedOrFailed {
        PASSED, FAILED, SCORED
    }
}
