package io.github.murphscall.concertbooking.global.mapper;

/**
 * DTO <-> ENTITY 변환을 위한 interface.</->
 * 이 인터페이스를 구현하는 각 도메인 Mapper 들은 toDto , toEntity 메소드를 정의해야합니다.
 * @param <REQ> 요청 DTO
 * @param <RES> 응답 DTO
 * @param <E> Entity
 */
public interface ModelMapper<REQ, RES, E> {
	RES toDto(final E entity);

	E toEntity(final REQ dto);
}
