package io.github.murphscall.concertbooking.concert.domain;

import io.github.murphscall.concertbooking.global.entity.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "concerts")
public class Concert extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "ticket_limit",nullable = false)
    private Long ticketLimit;

    @Column(name = "name" , nullable = false)
    private String name;

    @Column(name = "concert_venue", nullable = false)
    private String concertVenue;

    @Column(name = "concert_date" , nullable = false)
    private LocalDateTime concertDate;



}
