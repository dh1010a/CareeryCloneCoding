package cloneVl.cloneVl.domain.Users;

import cloneVl.cloneVl.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Builder
@AllArgsConstructor
@Table(name = "USERS")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id; //PK

    @Column(nullable = false, length = 30, unique = true)
    private String userName;//아이디

    private String password;


    @Column(nullable = false, length = 30, unique = true)
    private String nickName; //별명

    @Column(nullable = false, length = 30, unique = true)
    private String addressName; // 주소


    @Column(length = 30, unique = true)
    private String email;//이메일

    @Column
    private String imgUrl;


    // -- 정보 수정 --//
    public void updatePassword(PasswordEncoder passwordEncoder, String password){
        this.password = passwordEncoder.encode(password);
    }

    public void updateEmail(String email){
        this.email = email;
    }

    public void updateNickName(String nickName){
        this.nickName = nickName;
    }

    public void updateAddressName(String addressName){
        this.addressName = addressName;
    }
    public void updateImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }
    // == 패스워드 암호화 == //
    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
    }

}