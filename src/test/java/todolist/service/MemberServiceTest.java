package todolist.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import todolist.entity.MemberEntity;
import todolist.repository.MemberRepository;
import todolist.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId("ohohoh");
        memberEntity.setPw("ohoh123");

        String saveId = memberService.join(memberEntity);

        MemberEntity findMember = memberService.findOne(saveId).get();
        assertThat(memberEntity.getId()).isEqualTo(findMember.getId());
    }

    @Test
    public void 중복_회원_예외() {
        MemberEntity memberEntity1 = new MemberEntity();
        memberEntity1.setId("member1");
        memberEntity1.setPw("member123");

        MemberEntity memberEntity2 = new MemberEntity();
        memberEntity2.setId("member1");
        memberEntity2.setPw("member123");

        // member1 을 가입시키고
        memberService.join(memberEntity1);
        // member2 의 중복 여부를 체크
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(memberEntity2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

}