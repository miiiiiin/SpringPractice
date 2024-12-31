package com.site.sbb.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// 스프링 시큐리티가 로그인 시 사용할 UserSecurityService
@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {
    private final UserRepository userRepository;

    // 사용자명 (username) 으로 스프링 시큐리티의 사용자 (User) 객 체를 조회하여 리턴하는 메서드
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SiteUser> _siteUser = this.userRepository.findByusername(username);
        if (_siteUser.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다");
        }
        SiteUser siteUser = _siteUser.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 권한 부여
        if ("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }
        // spring security user 객체 반환 (사용자명, 비밀번호, 권한 리스트가 전달)
        return new User(siteUser.getUsername(), siteUser.getPassword(), authorities);
    }
    /**
     * 스프링 시큐리티는 loadUserByUsername 메서드에 의해
     * 리턴된 User 객체의 비밀번호가 사용자로부터 입력받은 비밀번호와
     * 일치하는지를 검사하는 기능을 내부에 갖고있음.
     */
}
