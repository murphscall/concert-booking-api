package io.github.murphscall.concertbooking.booking.domain;

import io.github.murphscall.concertbooking.global.entity.BaseEntity;
import io.github.murphscall.concertbooking.ticket.domain.Ticket;
import io.github.murphscall.concertbooking.user.domain.User;
import jakarta.persistence.*;


@Entity
@Table(name = "bookings")
public class Booking extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id" , nullable = false)
    private Ticket ticket;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;


}
