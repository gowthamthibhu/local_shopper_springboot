package com.localshopper.backend.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter
@Setter
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shopName;
    private String address;
    private String city;
    private String area;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(nullable = false)
    private Integer visitCount = 0;

    private boolean open;
    private LocalTime openingTime;
    private LocalTime closingTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
}
