package com.example.cinema_booking.services;

import com.example.cinema_booking.models.Movie;
import com.example.cinema_booking.repositories.MovieRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryMovieRepository implements MovieRepository {

    private final Map<UUID, Movie> storage = new ConcurrentHashMap<>();

    @Override
    public Optional<Movie> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Movie> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public <S extends Movie> S save(S entity) {
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID());
        }
        storage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public void deleteById(UUID id) {
        storage.remove(id);
    }

    private UnsupportedOperationException notImplemented() {
        return new UnsupportedOperationException("Not implemented in InMemoryMovieRepository");
    }

    @Override
    public void flush() {
        throw notImplemented();
    }

    @Override
    public <S extends Movie> S saveAndFlush(S entity) {
        throw notImplemented();
    }

    @Override
    public <S extends Movie> List<S> saveAllAndFlush(Iterable<S> entities) {
        throw notImplemented();
    }

    @Override
    public void deleteAllInBatch(Iterable<Movie> entities) {
        throw notImplemented();
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<UUID> uuids) {
        throw notImplemented();
    }

    @Override
    public void deleteAllInBatch() {
        throw notImplemented();
    }

    @Override
    public Movie getOne(UUID uuid) {
        throw notImplemented();
    }

    @Override
    public Movie getById(UUID uuid) {
        throw notImplemented();
    }

    @Override
    public Movie getReferenceById(UUID uuid) {
        throw notImplemented();
    }

    @Override
    public <S extends Movie> Optional<S> findOne(org.springframework.data.domain.Example<S> example) {
        throw notImplemented();
    }

    @Override
    public <S extends Movie> List<S> findAll(org.springframework.data.domain.Example<S> example) {
        throw notImplemented();
    }

    @Override
    public <S extends Movie> List<S> findAll(org.springframework.data.domain.Example<S> example, org.springframework.data.domain.Sort sort) {
        throw notImplemented();
    }

    @Override
    public <S extends Movie> org.springframework.data.domain.Page<S> findAll(org.springframework.data.domain.Example<S> example, org.springframework.data.domain.Pageable pageable) {
        throw notImplemented();
    }

    @Override
    public <S extends Movie> long count(org.springframework.data.domain.Example<S> example) {
        throw notImplemented();
    }

    @Override
    public <S extends Movie> boolean exists(org.springframework.data.domain.Example<S> example) {
        throw notImplemented();
    }

    @Override
    public <S extends Movie, R> R findBy(org.springframework.data.domain.Example<S> example,
                                         java.util.function.Function<org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        throw notImplemented();
    }

    @Override
    public <S extends Movie> List<S> saveAll(Iterable<S> entities) {
        throw notImplemented();
    }

    @Override
    public boolean existsById(UUID uuid) {
        throw notImplemented();
    }

    @Override
    public List<Movie> findAllById(Iterable<UUID> uuids) {
        throw notImplemented();
    }

    @Override
    public long count() {
        throw notImplemented();
    }

    @Override
    public void delete(Movie entity) {
        throw notImplemented();
    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {
        throw notImplemented();
    }

    @Override
    public void deleteAll(Iterable<? extends Movie> entities) {
        throw notImplemented();
    }

    @Override
    public void deleteAll() {
        throw notImplemented();
    }

    @Override
    public List<Movie> findAll(org.springframework.data.domain.Sort sort) {
        throw notImplemented();
    }

    @Override
    public org.springframework.data.domain.Page<Movie> findAll(org.springframework.data.domain.Pageable pageable) {
        throw notImplemented();
    }
}
