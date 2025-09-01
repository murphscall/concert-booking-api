package io.github.murphscall.concertbooking.global;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import jakarta.persistence.EntityNotFoundException;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {
	default void validateById(ID id) {
		if (!existsById(id)) {
			String entityName = getClass().getSimpleName();
			throw new EntityNotFoundException(entityName + "not found with id " + id);
		}
	}

	default T findByIdOrThrow(ID id) {
		return findById(id).orElseThrow(() -> {
			String entityName = getClass().getSimpleName();
			return new EntityNotFoundException(entityName + "not found with id " + id);
		});
	}
}
