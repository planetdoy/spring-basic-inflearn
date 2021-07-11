package hello.core.member;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MemoryMemberRepository implements MemberRepository {

    //동시성 문제로 HashMap이 아닌 ConcurrentHashMap을 사용해야 합니다.
    private static Map<Long, Member> store = new HashMap<>();

    Map<?,?> map =  new ConcurrentHashMap();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
        //원래는 오류처리도 해야합니다.
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
