package ru.vcodetsev.account.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String string);

    boolean existsAccountByRolesAndId(Collection<Role> roles, Long id);
}
