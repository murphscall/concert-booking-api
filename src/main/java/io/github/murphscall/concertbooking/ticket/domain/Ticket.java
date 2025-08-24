package io.github.murphscall.concertbooking.ticket.domain;

import io.github.murphscall.concertbooking.concert.domain.Concert;
import io.github.murphscall.concertbooking.global.entity.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Ticket extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_number" , nullable = false)
    private String seatNumber;

    @Column(name = "status" , nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @Column(name = "grade" , nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketGrade grade;

    @Column(name = "price" , nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_id" , nullable = false)
    private Concert concert;

}
