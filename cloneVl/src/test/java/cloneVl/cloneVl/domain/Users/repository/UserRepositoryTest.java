package cloneVl.cloneVl.domain.Users.repository;

import cloneVl.cloneVl.domain.Users.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired UsersRepository usersRepository;
    @Autowired EntityManager em;

    @AfterEach
    public void after(){
        em.clear();
    }

    private void clear(){
        em.flush();
        em.clear();
    }

    @Test
    public void 회원저장_성공() throws Exception{
        //given
        Users users = Users.builder().userName("dh1010a").password("12345678").nickName("dh1010a").addressName("dh1010a.log").email("hi").build();


        //when
        Users saveUser = usersRepository.save(users);

        //then
        Users findUser = usersRepository.findById(saveUser.getId()).orElseThrow(() -> new RuntimeException("저장된 회원이 없습니다"));
        assertThat(findUser).isSameAs(saveUser);
        assertThat(findUser).isSameAs(users);

    }

    @Test
    public void 회원가입시_아이디_없음_오류() throws Exception{
        //given
        Users users = Users.builder().password("12345678").nickName("dh1010a").addressName("dh1010a.log").email("hi").build();

        // when, then
        assertThrows(Exception.class, () -> usersRepository.save(users));
    }

    @Test
    public void 회원가입시_닉네임_없음_오류() throws Exception{
        //given
        Users users = Users.builder().userName("dh1010a").password("12345678").addressName("dh1010a.log").email("hi").build();

        // when, then
        assertThrows(Exception.class, () -> usersRepository.save(users));
    }

    @Test
    public void 회원가입시_주소_없음_오류() throws Exception{
        //given
        Users users = Users.builder().userName("dh1010a").password("12345678").nickName("dh1010a").email("hi").build();

        // when, then
        assertThrows(Exception.class, () -> usersRepository.save(users));
    }

    @Test
    public void 회원가입시_중복된아이디_오류() throws Exception{
        //given
        Users users1 = Users.builder().userName("dh1010a").password("12345678").nickName("dh1010a").addressName("dh1010a.log").email("hi").build();
        Users users2 = Users.builder().userName("dh1010a").password("1234567").nickName("dh1010a2").addressName("dh1010a2.log").email("hi2").build();

        usersRepository.save(users1);
        clear();
        // when, then
        assertThrows(Exception.class, () -> usersRepository.save(users2));
    }

    @Test
    public void 회원_삭제_성공() throws Exception {
        // given
        Users users = Users.builder().userName("dh1010a").password("12345678").nickName("dh1010a").addressName("dh1010a.log").email("hi").build();
        usersRepository.save(users);
        clear();

        //when
        usersRepository.delete(users);
        clear();

        // then
        assertThrows(Exception.class, () -> usersRepository.findById(users.getId()).orElseThrow(() -> new Exception()));

    }

    @Test
    public void existByUsername_정상작동() throws Exception{
        // given
        Users users = Users.builder().userName("dh1010a").password("12345678").nickName("dh1010a").addressName("dh1010a.log").email("hi").build();
        String username = "dh1010a";
        usersRepository.save(users);
        clear();

        //when, then
        assertThat(usersRepository.existsByUserName(username)).isTrue();
        assertThat(usersRepository.existsByUserName(username+"123")).isFalse();
    }

    @Test
    public void findByUsername_정상작동() throws Exception{
        // given
        String username = "dh1010a";
        Users users = Users.builder().userName("dh1010a").password("12345678").nickName("dh1010a").addressName("dh1010a.log").email("hi").build();
        usersRepository.save(users);
        clear();

        //when, then
        assertThat(usersRepository.findByUserName(username).get().getUserName()).isEqualTo(users.getUserName());
        assertThat(usersRepository.findByUserName(username).get().getAddressName()).isEqualTo(users.getAddressName());
        assertThat(usersRepository.findByUserName(username).get().getId()).isEqualTo(users.getId());
        assertThrows(Exception.class, () -> usersRepository.findByUserName(username+"123").orElseThrow(() -> new Exception()));

    }

    @Test
    public void 회원가입시_생성시간_등록() throws Exception{
        //given
        Users users = Users.builder().userName("dh1010a").password("12345678").nickName("dh1010a").addressName("dh1010a.log").email("hi").build();
        usersRepository.save(users);
        clear();

        //when
        Users findUsers = usersRepository.findById(users.getId()).orElseThrow(() -> new Exception());

        // then
        assertThat(findUsers.getCreatedDate()).isNotNull();
        assertThat(findUsers.getLastModifiedDate()).isNotNull();
    }



}