package hello.core.member; //인터페이스

public interface MemberRepository {

    void save(Member member); //회원 저장

    Member findById(Long memberId); //회원의 Id로 회원을 찾는 기능
}
