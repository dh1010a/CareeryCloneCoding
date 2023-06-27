package cloneVl.cloneVl.domain.Users.repository;

import cloneVl.cloneVl.domain.Users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUserName(String userName);

    boolean existsByUserName(String userName);
}

