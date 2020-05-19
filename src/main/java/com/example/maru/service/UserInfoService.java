package com.example.maru.service;

import com.example.maru.model.CustomUserDetails;
import com.example.maru.model.UserInfo;
import com.example.maru.model.Users;
import com.example.maru.repository.UserDetailsRepository;
import com.example.maru.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created by : maru
 * Date  : 5/18/2020
 * Time  : 11:57 AM
 */

@Service
@Transactional
public class UserInfoService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private UsersRepository usersRepository;

    public Users getUserByUserName(String userName) {
        Optional<Users> usersOptional = usersRepository.findByName(userName);

        usersOptional
            .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
        return usersOptional
            .map(CustomUserDetails::new)
            .get();
    }

    public UserInfo getUserInfoByUserName(String userName) {
        short enabled = 1;
        return userDetailsRepository.findByUserNameAndEnabled(userName, enabled);
    }

    public List<UserInfo> getAllActiveUserInfo() {
        return userDetailsRepository.findAllByEnabled((short) 1);
    }

    public UserInfo getUserInfoById(Integer id) {
        return userDetailsRepository.findById(id);
    }

    public UserInfo addUser(UserInfo userInfo) {
        userInfo.setPassword(new BCryptPasswordEncoder().encode(userInfo.getPassword()));
        return userDetailsRepository.save(userInfo);
    }

    public UserInfo updateUser(Integer id, UserInfo userRecord) {
        UserInfo userInfo = userDetailsRepository.findById(id);
        userInfo.setUserName(userRecord.getUserName());
        userInfo.setPassword(userRecord.getPassword());
        userInfo.setRole(userRecord.getRole());
        userInfo.setEnabled(userRecord.getEnabled());
        return userDetailsRepository.save(userInfo);
    }

    public void deleteUser(Integer id) {
        userDetailsRepository.deleteById(id);
    }

    public UserInfo updatePassword(Integer id, UserInfo userRecord) {
        UserInfo userInfo = userDetailsRepository.findById(id);
        userInfo.setPassword(userRecord.getPassword());
        return userDetailsRepository.save(userInfo);
    }

    public UserInfo updateRole(Integer id, UserInfo userRecord) {
        UserInfo userInfo = userDetailsRepository.findById(id);
        userInfo.setRole(userRecord.getRole());
        return userDetailsRepository.save(userInfo);
    }

}
