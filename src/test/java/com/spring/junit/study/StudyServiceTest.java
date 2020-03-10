package com.spring.junit.study;

import com.spring.junit.domain.Member;
import com.spring.junit.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    /*@Mock
    MemberService memberService;
    @Mock
    StudyRepository studyRepository;*/

    @Test
    void createStudyService(@Mock MemberService memberService,
                            @Mock StudyRepository studyRepository) {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertThat(studyService).isNotNull();

        Member member = Member.builder()
                .id(1L)
                .email("test@test.com")
                .build();

        when(memberService.findById(any())).thenReturn(Optional.of(member)).thenThrow(new RuntimeException()).thenReturn(Optional.empty());
        assertThat("test@test.com").isEqualTo(memberService.findById(1L).get().getEmail());
        assertThrows(RuntimeException.class, () -> memberService.findById(1L));
        assertThat(Optional.empty()).isEqualTo(memberService.findById(1L));

        doThrow(new IllegalArgumentException()).when(memberService).validate();
        assertThrows(IllegalArgumentException.class, () -> memberService.validate());

//        Study study = Study.builder()
//                .id(10L)
//                .name("java")
//                .build();
//
//        studyService.createNewStudy(1L, study);
    }

}