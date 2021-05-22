package com.shopnobazz.schoolManagement.security;

import com.shopnobazz.schoolManagement.domain.User;
import com.shopnobazz.schoolManagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

private final UserRepository userRepository;
    @Override
    @Transactional
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        System.out.println(username);
        User user = userRepository.findByUserName(username);
        return UserPrinciple.build(user);
    }
}
