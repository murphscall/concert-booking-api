package io.github.murphscall.concertbooking.global.mapper;

public interface ModelMapper<REQ, RES, E> {
    RES toDto(final E entity);
    E toEntity(final REQ dto);
}
