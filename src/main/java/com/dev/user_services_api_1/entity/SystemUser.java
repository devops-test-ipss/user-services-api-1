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

    @OneToOne(mappedBy = "systemUser",fetch = FetchType.LAZY)
    private BillingAddress billingAddress;
}
