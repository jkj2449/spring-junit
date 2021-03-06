package com.spring.junit.domain;

import com.spring.junit.study.StudyStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Study {
    @Id @GeneratedValue
    private Long id;
    private StudyStatus status = StudyStatus.DRAFT;
    private int limitCount;
    private String name;
    private LocalDateTime openedDateTime;
    @ManyToOne
    private Member owner;

    public Study(int limit, String name) {
        this.limitCount = limit;
        this.name = name;
    }

    @Builder
    public Study(Long id, StudyStatus status, int limitCount, String name, LocalDateTime openedDateTime, Member owner) {
        this.id = id;
        this.status = status;
        this.limitCount = limitCount;
        this.name = name;
        this.openedDateTime = openedDateTime;
        this.owner = owner;
    }

    public Study(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit은 0보다 커야 한다.");
        }
        this.limitCount = limit;
    }

    public void publish() {
        this.openedDateTime = LocalDateTime.now();
        this.status = StudyStatus.OPENED;
    }
}
