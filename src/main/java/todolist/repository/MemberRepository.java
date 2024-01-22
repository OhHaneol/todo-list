package todolist.repository;

import todolist.entity.MemberEntity;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    MemberEntity save(MemberEntity memberEntity);

    Optional<MemberEntity> findBySeq(Long seq);
    Optional<MemberEntity> findById(String id);
    List<MemberEntity> findAll();

    void clearStore();
}
