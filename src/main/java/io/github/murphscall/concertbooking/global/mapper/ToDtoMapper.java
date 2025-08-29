package io.github.murphscall.concertbooking.global.mapper;

public interface ToDtoMapper<RES, E> {
	RES toDto(final E entity);
}
