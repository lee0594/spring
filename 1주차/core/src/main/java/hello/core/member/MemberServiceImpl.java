package hello.core.member;

public class MemberServiceImpl implements MemberService{
    //구현체가 하나만 있을 때에는 인터페이스 뒤에 Impl이라고 관례상 많이 씀
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //참고: 바로 enter하지 말고 ctrl shift enter하면 ';'까지 같이 생김
    //가입을 하고 회원을 찾으려면 MemberRepository 인터페이스가 필요함
    //구현체를 쓰지 않고 interface만 가지고 있으면 null point..터짐: 구현 객체를 선택해 주어야 함

    @Override
    public void join(Member member) {
        memberRepository.save(member); //다형성에 의해서 MemoryMemberRepository에 있는 save가 호출됨(interface에 있는 것이 아니라 override한 것)
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
