package com.spring.junit;

public class Study {
    private StudyStatus status = StudyStatus.DRAFT;
    private int limit;

    public Study(int limit) {
        if(limit < 0) {
            throw new IllegalArgumentException("limit은 0보다 커야 된다.");
        }
        this.limit = limit;
    }

    public StudyStatus getStatus() {
        return this.status;
    }

    public int getLimit() {
        return this.limit;
    }
}