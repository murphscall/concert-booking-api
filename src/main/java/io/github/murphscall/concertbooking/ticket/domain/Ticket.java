package io.github.murphscall.concertbooking.ticket.domain;

import java.math.BigDecimal;

import io.github.murphscall.concertbooking.concert.domain.Concert;
import io.github.murphscall.concertbooking.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Table(name = "tickets")
@Entity
public class Ticket extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "seat_number", nullable = false)
	private String seatNumber;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private TicketStatus status;

	@Column(name = "grade", nullable = false)
	@Enumerated(EnumType.STRING)
	private TicketGrade grade;

	@Column(name = "price", nullable = false)
	private BigDecimal price;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "concert_id", nullable = false)
	private Concert concert;

	public void checkOrUpdate() {
		if (this.status == TicketStatus.BOOKED) {
			throw new IllegalStateException("This ticket is already booked");
		}
		this.status = TicketStatus.BOOKED;
	}

	protected Ticket() {
	}

	public Ticket(Concert concert, String seatNumber, TicketStatus status, TicketGrade grade, BigDecimal price) {
		this.concert = concert;
		this.seatNumber = seatNumber;
		this.status = status;
		this.grade = grade;
		this.price = price;
	}

}
