package com.dev.user_services_api_1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "billing_address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BillingAddress {

    @Id
    @Column(name = "address_id")
    private String addressId;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @Column(name = "postal")
    private String postal;

    @OneToOne
    @JoinColumn(name = "user_id")
    private SystemUser systemUser;

}
