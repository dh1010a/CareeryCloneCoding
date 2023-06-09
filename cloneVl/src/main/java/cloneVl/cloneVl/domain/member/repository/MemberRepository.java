package cloneVl.cloneVl.domain.member.repository;

import cloneVl.cloneVl.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

    boolean existsByUsername(String username);
    Optional<Member> findByRefreshToken(String refreshToken);
}
