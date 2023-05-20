package hello.core.member; //보기 힘들어서 usages setting 가서 제거했음

public class Member {   //alt+insert: generator 단축키, ctrl+a: 전체 선택

    private Long id;
    private String name;
    private Grade grade;

    public Member(Long id, String name, Grade grade) { //생성자
        this.id = id;
        this.name = name;
        this.grade = grade;
    }
    //getter, setter 만들어줌: 이걸 통해 private의 값을 setting 가능
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
