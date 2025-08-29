package io.github.murphscall.concertbooking.concert.domain;

import java.time.LocalDateTime;

import io.github.murphscall.concertbooking.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "concerts")
public class Concert extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ticket_limit", nullable = false)
	private Long ticketLimit;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "concert_venue", nullable = false)
	private String concertVenue;

	@Column(name = "concert_date", nullable = false)
	private LocalDateTime concertDate;

}
