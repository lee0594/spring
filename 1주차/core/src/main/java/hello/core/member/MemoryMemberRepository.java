package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{
    //구조체: 개발을 진행할 수는 있지만 메모리에서만 하기 때문에 테스트용으로만 써야함
    //alt+enter: 오류 생겼을때 누르면 됨
    private static Map<Long, Member> store = new HashMap<>(); //저장소:map 필요
    //여러군데에서 store 접근하면 동시성 이슈가 있을 수 있기 때문에 실무에서는 ConcurrentHashMap을 써야 함
    @Override
    public void save(Member member) {
        store.put(member.getId(),member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}

//간단하게 하기 위해 오류처리 생략