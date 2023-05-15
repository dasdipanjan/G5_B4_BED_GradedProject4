package com.glearning.emp.mgm.ga.lab4.services.impl;

import com.glearning.emp.mgm.ga.lab4.entity.User;
import com.glearning.emp.mgm.ga.lab4.repository.UserRepository;
import com.glearning.emp.mgm.ga.lab4.security.AppUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * This is an implementation class of {@link UserDetailsService} interface which is responsible to load username
 * and provides {@link UserDetails}
 *
 * @author DIPANJAN DAS
 * @version 1.0
 * @since 15-May-2023
 */
@Slf4j
public class AppUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public AppUserDetailsServiceImpl() {

    }

    /**
     * This method is responsible to load user information from database and provide when it is requred in form of {@link UserDetails}
     *
     * @param username Username provided by client.
     * @return Instance of {@link UserDetails}
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return new AppUserDetails(user);
    }
}
