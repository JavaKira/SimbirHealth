package ru.vcodetsev.account.jwt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenBanListRepository extends JpaRepository<BannedToken, String> {
}
