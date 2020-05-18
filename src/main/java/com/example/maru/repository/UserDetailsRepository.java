package com.example.maru.repository;

import com.example.maru.model.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by : maru
 * Date  : 5/18/2020
 * Time  : 11:58 AM
 */
@Repository
@Transactional
public interface UserDetailsRepository extends CrudRepository<UserInfo, String> {

    public UserInfo findByUserNameAndEnabled(String username, short enabled);
    public List<UserInfo> findAllByEnabled(short enabled);

    public UserInfo findById(Integer id);

    public void deleteById(Integer id);
}
