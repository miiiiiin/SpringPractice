package com.site.sbb.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser, Long> {

    // 사용자 ID로 SiteUser 엔티티를 조회
    Optional<SiteUser> findByusername(String username);
}
