package com.dev.user_services_api_1.service.impl;

import com.dev.user_services_api_1.dto.request.RequestUserDto;
import com.dev.user_services_api_1.entity.SystemUser;
import com.dev.user_services_api_1.exception.DuplicateEntryException;
import com.dev.user_services_api_1.repo.SystemUserRepo;
import com.dev.user_services_api_1.service.SystemUserService;
import com.dev.user_services_api_1.util.KeyCloakSecurityUtil;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemUserServiceImpl implements SystemUserService {

    private final KeyCloakSecurityUtil keyCloakSecurityUtil;
    private SystemUserRepo systemUserRepo;

    @Value("${keycloak.config.realm}")
    private String realm;
    @Value("${keycloak.config.client-id}")
    private String clientId;
    @Value("${keycloak.config.secret}")
    private String secret;
    @Value("${spring.security.oauth2.resourceserver.jwt.token-uri}")
    private String keyCloakUrl;

    @Override
    public void signup(RequestUserDto dto) {
        String userId;
        Keycloak keycloak = null;
        UserRepresentation existingUser = null;
        keycloak = keyCloakSecurityUtil.getKeycloakInstance();

        keycloak.realm(realm).users().search(dto.getEmail()).stream().findFirst().orElse(null));
        if (existingUser != null) {
            Optional<SystemUser> existingSystemUserData = systemUserRepo.findByEmail(existingUser.getEmail());
            if (existingSystemUserData.isEmpty()) {
                keycloak.realm(realm).users().delete(existingUser.getId());
            } else {
                throw new DuplicateEntryException("user already exists");
            }
        } else {
            Optional<SystemUser> byEmail = systemUserRepo.findByEmail(dto.getEmail());
            if (byEmail.isPresent()) {
                systemUserRepo.deleteById(byEmail.get().getUserId());
            }
        }
        UserRepresentation userRep = mapUserRep(dto);
        Response response = keycloak.realm(realm).users().create(userRep);
        if(response.getStatus()==Response.Status.CREATED.getStatusCode()){
            RoleRepresentation roleRepresentation = keycloak.realm(realm).roles().get("user").toRepresentation();
            userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
            keycloak.realm(realm).users().get(userId).roles().realmLevel().add(Arrays.asList(roleRepresentation));

            SystemUser systemUser = SystemUser.builder()
                    .userId(userId)
                    .email(dto.getEmail())
                    .status(true)
                    .fullName(dto.getFirstName() + " " + dto.getLastName())
                    .isEnabled(true)
                    .isAccountNonExpired(true)
                    .isAccountNonLocked(true)
                    .isCredentialsNonExpired(true)
                    .build();
            systemUserRepo.save(systemUser);

        }

    }

    private UserRepresentation mapUserRep(RequestUserDto dto) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setEmailVerified(true);
        user.setEnabled(true);
        ArrayList<CredentialRepresentation> creds = new ArrayList<>();
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setValue(dto.getPassword());
        creds.add(credentialRepresentation);
        user.setCredentials(creds);
        return user;
    }

    @Override
    public void login(String email, String password) {

    }
}
