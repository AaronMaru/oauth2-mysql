package com.example.maru.service;

import com.example.maru.model.CustomUserDetails;
import com.example.maru.model.UserInfo;
import com.example.maru.model.Users;
import com.example.maru.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by : maru
 * Date  : 5/18/2020
 * Time  : 11:53 AM
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

       /* Users userInfo = userInfoService.getUserByUserName(userName);
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userInfo.getRole());
        return new User(userInfo.getUserName(), userInfo.getPassword(), Arrays.asList(grantedAuthority));*/

        Optional<Users> usersOptional = usersRepository.findByName(userName);

        usersOptional
            .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
        return usersOptional
            .map(CustomUserDetails::new)
            .get();
    }
}
