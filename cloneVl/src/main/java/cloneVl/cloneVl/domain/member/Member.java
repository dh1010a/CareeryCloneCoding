package cloneVl.cloneVl.domain.member;
import cloneVl.cloneVl.domain.BaseTimeEntity;
import cloneVl.cloneVl.domain.member.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Column(nullable = false, length = 30)
    private Integer age;//나이

    @Enumerated(EnumType.STRING)
    private Role role;//권한 -> USER, ADMIN

    @Column(length = 1000)
    private String refreshToken;//RefreshToken


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

}
