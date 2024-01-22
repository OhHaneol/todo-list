package todolist.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todolist.entity.MemberEntity;
import todolist.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    @Transactional
    public String join(MemberEntity memberEntity) {
        validateDuplicateMember(memberEntity);
        memberRepository.save(memberEntity);
        return memberEntity.getId();
    }

    @Transactional
    private void validateDuplicateMember(MemberEntity memberEntity) {
        memberRepository.findById(memberEntity.getId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }


    // 회원 조회
    @Transactional
    public Optional<MemberEntity> findOne(String memberId) {
        return memberRepository.findById(memberId);
    }

    // 전체 회원 조회
    @Transactional
    public List<MemberEntity> findMembers() {
        return memberRepository.findAll();
    }

}
