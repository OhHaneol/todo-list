package todolist.repository;

import todolist.entity.MemberEntity;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, MemberEntity> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public MemberEntity save(MemberEntity memberEntity) {
        memberEntity.setSeq(++sequence);
        store.put(memberEntity.getSeq(), memberEntity);
        return memberEntity;
    }

    @Override
    public Optional<MemberEntity> findBySeq(Long seq) {
        return Optional.ofNullable(store.get(seq));
    }

    @Override
    public Optional<MemberEntity> findById(String id) {
        return store.values().stream()
                .filter(memberEntity -> memberEntity.getId().equals(id))
                .findAny();
    }

    @Override
    public List<MemberEntity> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
