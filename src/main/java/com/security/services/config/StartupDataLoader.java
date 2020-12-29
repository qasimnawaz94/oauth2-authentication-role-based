package com.security.services.config;

import static com.security.services.constants.ApplicationConstants.DEFAULT_EMAIL;
import static com.security.services.constants.ApplicationConstants.DEFAULT_FIRST_NAME;
import static com.security.services.constants.ApplicationConstants.DEFAULT_LAST_NAME;
import static com.security.services.constants.ApplicationConstants.DEFAULT_PASSWORD;
import static com.security.services.constants.ApplicationConstants.DEFAULT_USERNAME;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import javax.transaction.Transactional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.security.services.constants.ApplicationConstants;
import com.security.services.constants.RoleConstants;
import com.security.services.constants.SuperAdminPrivileges;
import com.security.services.constants.UsersPrivilegesMap;
import com.security.services.model.Privilege;
import com.security.services.model.Role;
import com.security.services.model.User;
import com.security.services.repository.PrivilegeRepository;
import com.security.services.repository.RolePrivilegeRepository;
import com.security.services.repository.RoleRepository;
import com.security.services.repository.UserRepository;


@Component
public class StartupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = Logger.getLogger(StartupDataLoader.class);
    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private RolePrivilegeRepository rolePrivilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup) {
            return;
        }

        LOGGER.info("---------------------------Application checking Default User ------------------------------");
        Privilege readPrivilege = createPrivilegeIfNotFound(SuperAdminPrivileges.READ_PRIVILEGE.name(), null);
        Privilege writePrivilege = createPrivilegeIfNotFound(SuperAdminPrivileges.WRITE_PRIVILEGE.name(), null);
        Privilege updatePrivilege = createPrivilegeIfNotFound(SuperAdminPrivileges.UPDATE_PRIVILEGE.name(), null);

        List<Privilege> superAdminPrivileges = Arrays.asList(readPrivilege, writePrivilege, updatePrivilege);

        Role adminRole = createRoleIfNotFound(RoleConstants.ROLE_SUPER_ADMIN.name(), superAdminPrivileges);
        createSuperAdminIfNotExists(superAdminPrivileges, adminRole);


        List<Privilege> privileges = checkAndCreateOtherPrivilges();
        checkAndCreateAdminRole(privileges);

        alreadySetup = true;
    }

    Privilege createPrivilegeIfNotFound(String name, String endpoint) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = Privilege.builder().name(name).endpoint(endpoint).build();
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = Role.builder().name(name).privileges(privileges).build();
            roleRepository.save(role);
        }
        return role;
    }


    void createSuperAdminIfNotExists(List<Privilege> adminPrivileges, Role adminRole) {
        if (Objects.isNull(userRepository.findOneByUsername(DEFAULT_USERNAME))) {
            User user = new User();
            user.setUsername(ApplicationConstants.DEFAULT_USERNAME);
            user.setFirstName(DEFAULT_FIRST_NAME);
            user.setLastName(DEFAULT_LAST_NAME);
            user.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
            user.setEmail(DEFAULT_EMAIL);
            user.setRoles(Arrays.asList(adminRole));
            user.setEnabled(true);
            userRepository.save(user);

        }
    }


    void checkAndCreateAdminRole(List<Privilege> privileges) {
        Stream.of(RoleConstants.values()).forEach(one -> {
            if (one != RoleConstants.ROLE_SUPER_ADMIN) {
                createRoleIfNotFound(one.name(), privileges);
            }
        });
    }

    List<Privilege> checkAndCreateOtherPrivilges() {
        List<Privilege> privileges = new ArrayList<>();
        Stream.of(UsersPrivilegesMap.values()).forEach(one -> {
            privileges.add(createPrivilegeIfNotFound(one.getName(), one.getEndpoint()));
        });
        return privileges;
    }

}
