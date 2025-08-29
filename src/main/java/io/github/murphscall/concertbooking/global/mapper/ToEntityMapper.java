package io.github.murphscall.concertbooking.global.mapper;

public interface ToEntityMapper<REQ, E> {
	E toEntity(final REQ dto);
}
