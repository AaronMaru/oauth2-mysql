package com.example.maru.contoller;

import com.example.maru.model.UserInfo;
import com.example.maru.model.Users;
import com.example.maru.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserInfoService userService;

    @GetMapping("/user")
    public Object getAllUser(@RequestHeader HttpHeaders requestHeader) {
        List<UserInfo> userInfos = userService.getAllActiveUserInfo();
        if (userInfos == null || userInfos.isEmpty()) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        return userInfos;
    }

    @PostMapping("/user")
    public Users addUser(@RequestBody Users userRecord) {
        return userService.addUser(userRecord);
    }

    @PutMapping("/user/{id}")
    public UserInfo updateUser(@RequestBody UserInfo userRecord, @PathVariable Integer id) {
        return userService.updateUser(id, userRecord);
    }

    @PutMapping("/user/changePassword/{id}")
    public UserInfo updateUserPassword(@RequestBody UserInfo userRecord, @PathVariable Integer id) {
        return userService.updatePassword(id, userRecord);
    }

    @PutMapping("/user/changeRole/{id}")
    public UserInfo updateUserRole(@RequestBody UserInfo userRecord, @PathVariable Integer id) {
        return userService.updateRole(id, userRecord);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserInfo> getUserById(@PathVariable Integer id) {
        UserInfo userInfo = userService.getUserInfoById(id);
        if (userInfo == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @GetMapping("/bcrypt")
    public boolean bcrypt() {
        String password = "password";

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String afterEncode = bCryptPasswordEncoder.encode(password);
        System.out.println(afterEncode);

        SCryptPasswordEncoder sCryptPasswordEncoder = new SCryptPasswordEncoder();
        String afterScry = sCryptPasswordEncoder.encode(password);
        System.out.println(afterScry);

        StandardPasswordEncoder standardPasswordEncoder = new StandardPasswordEncoder();
        String afterStandard = standardPasswordEncoder.encode(password);
        System.out.println(afterStandard);

        StandardPasswordEncoder standardPasswordEncoderSecret = new StandardPasswordEncoder("abcmaru");
        String afterStandardSecret = standardPasswordEncoderSecret.encode(password);
        System.out.println(afterStandardSecret);

        return bCryptPasswordEncoder.matches("password", afterEncode);
    }
}
