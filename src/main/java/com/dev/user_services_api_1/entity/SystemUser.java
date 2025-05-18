package com.dev.user_services_api_1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "system_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SystemUser {

    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "email" ,unique = true, nullable = false, length = 150)
    private String email;
    @Column(name = "status" ,nullable = false, columnDefinition = "TINYINT")
    private boolean status;
    @Column(name = "full_name" ,nullable = false, length = 150)
    private String fullName;
    @Column(name = "is_account_non_expired", columnDefinition = "TINYINT")
    private boolean isAccountNonExpired;
    @Column(name = "is_credentials_non_expired", columnDefinition = "TINYINT")
    private boolean isCredentialsNonExpired;
    @Column(name = "is_account_non_locked", columnDefinition = "TINYINT")
    private boolean isAccountNonLocked;
    @Column(name = "is_enable", columnDefinition = "TINYINT")
    private boolean isEnable;

    @OneToOne(mappedBy = "systemUser",fetch = FetchType.LAZY)
    private BillingAddress billingAddress;
}
