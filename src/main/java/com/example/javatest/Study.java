package com.example.javatest;

public class Study {
    private StudyStatus status = StudyStatus.DRAFT;

    private int limit;
    private String name;

    public Study(int limit, String name) {
        this.limit = limit;
        this.name = name;
    }

    public Study(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit는 0보다 커야 한다");
        }

        this.limit = limit;
    }

    public StudyStatus getStatus() {
        return this.status;
    }

    public String getName() {
        return name;
    }

    public int getLimit() {
        return this.limit;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
