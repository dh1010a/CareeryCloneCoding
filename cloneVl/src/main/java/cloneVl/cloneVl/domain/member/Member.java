package cloneVl.cloneVl.domain.member;
import cloneVl.cloneVl.domain.BaseTimeEntity;
import cloneVl.cloneVl.domain.comment.Comment;
import cloneVl.cloneVl.domain.member.Role;
import cloneVl.cloneVl.domain.post.Post;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Table(name = "MEMBER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AllArgsConstructor
@Builder
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id; //primary Key

    @Column(nullable = false, length = 30, unique = true)
    private String username;//아이디

    private String password;//비밀번호

    @Column(nullable = false, length = 30)
    private String name;//이름(실명)

    @Column(nullable = false, length = 30)
    private String nickName;//별명

    @Column(length = 100)
    private String intro;//소개글

    @Column
    private String imgUrl;

    @Column(nullable = false, length = 30)
    private Integer age;//나이

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Role role;//권한 -> USER, ADMIN

    @Column(length = 1000)
    private String refreshToken;//RefreshToken

    //== 회원탈퇴 -> 작성한 게시물, 댓글 모두 삭제 ==//
    @OneToMany(mappedBy = "writer", cascade = ALL, orphanRemoval = true)
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "writer", cascade = ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    //== 연관관계 메서드 ==//
    public void addPost(Post post){
        //post의 writer 설정은 post에서 함
        postList.add(post);
    }

    public void addComment(Comment comment){
        //comment의 writer 설정은 comment에서 함
        commentList.add(comment);
    }


    //== 정보 수정 ==//
    public void updatePassword(PasswordEncoder passwordEncoder, String password){
        this.password = passwordEncoder.encode(password);
    }

    public void updateName(String name){
        this.name = name;
    }

    public void updateAge(int age){
        this.age = age;
    }

    public void updateNickName(String nickName){
        this.nickName = nickName;
    }
    public void updateIntro(String intro){
        this.intro = intro;
    }

    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }
    public void destroyRefreshToken(){
        this.refreshToken = null;
    }

    //== 패스워드 암호화 ==//
    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
    }

    //비밀번호 변경, 회원 탈퇴 시, 비밀번호를 확인하며, 이때 비밀번호의 일치여부를 판단하는 메서드입니다.
    public boolean matchPassword(PasswordEncoder passwordEncoder, String checkPassword){
        return passwordEncoder.matches(checkPassword, getPassword());
    }

    //회원가입시, USER의 권한을 부여하는 메서드입니다.
    public void addUserAuthority() {
        this.role = Role.USER;
    }
}
