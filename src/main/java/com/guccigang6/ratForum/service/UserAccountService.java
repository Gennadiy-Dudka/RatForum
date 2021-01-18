package com.guccigang6.ratForum.service;

import com.guccigang6.ratForum.entity.UserAccount;
import com.guccigang6.ratForum.repository.UserAccountDao;
import com.guccigang6.ratForum.security.UserAccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserAccountService implements UserDetailsService {

    private final UserAccountDao userAccountDao;
    private final PasswordEncoder encoder;

    @Autowired
    public UserAccountService(UserAccountDao userAccountDao,
                              PasswordEncoder encoder
                              ) {
        this.userAccountDao = userAccountDao;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountDao.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return new UserAccountDetails(userAccount);
    }

    @Transactional
    public void registerUser(UserAccount userAccount, String confirmPassword) throws RegistrationException {
        if(userAccountDao.findByUsername(userAccount.getUsername()).isPresent()){
            throw new RegistrationException("user with this username already exists");
        }
        if(!userAccount.getPassword().equals(confirmPassword)){
            throw new RegistrationException("entered passwords are different");
        }
        userAccount.setPassword(encoder.encode(userAccount.getPassword()));
        userAccountDao.save(userAccount);
    }
}
