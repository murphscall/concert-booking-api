package io.github.murphscall.concertbooking.booking.domain;

import java.time.LocalDateTime;

import io.github.murphscall.concertbooking.global.entity.BaseEntity;
import io.github.murphscall.concertbooking.ticket.domain.Ticket;
import io.github.murphscall.concertbooking.user.domain.User;
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
@Entity
@Table(name = "bookings")
public class Booking extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private BookingStatus status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ticket_id", nullable = false, unique = true)
	private Ticket ticket;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "booked_at", nullable = false)
	private LocalDateTime bookedAt;

	protected Booking() {
	}

	public Booking(final User user, final Ticket ticket) {
		this.ticket = ticket;
		this.user = user;
		this.status = BookingStatus.CONFIRMED;
		this.bookedAt = LocalDateTime.now();
	}

	public void validateOwner(Long userId) {
		if (!this.user.getId().equals(userId)) {
			throw new IllegalStateException("해당 예매 정보를 조회 할 수 없습니다.");
		}
	}
}
