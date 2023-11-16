package com.magellian.movies.infrastructure.service;

import com.magellian.movies.domain.exceptions.DirectorRepositoryException;
import com.magellian.movies.domain.model.Director;
import com.magellian.movies.infrastructure.persistence.DirectorDB;
import com.magellian.movies.infrastructure.persistence.DirectorDBRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DirectorRepositoryImplTest {

    @Mock
    DirectorDBRepository dbRepository;

    @InjectMocks
    DirectorRepositoryImpl directorRepository;

    @BeforeEach
    void setup() {

    }
    @Test
    void create() throws DirectorRepositoryException {
        DirectorDB directorDB = DirectorDB.builder().id("123-abc").name("director1").build();
        when(dbRepository.save(any())).thenReturn(directorDB);
        Director director = Director.builder().name("director 1").build();
        String id = directorRepository.create(director);
        Assertions.assertEquals("123-abc", id);
    }

    @Test
    void getById() throws DirectorRepositoryException {
        DirectorDB directorDB = DirectorDB.builder().id("123-abc").name("director1").build();
        when(dbRepository.findById(any())).thenReturn(Optional.of(directorDB));
        Director director = directorRepository.getById("123-abc");
        Assertions.assertNotNull(director);
        Assertions.assertEquals("123-abc", director.getId());
        Assertions.assertEquals("director1", director.getName());
    }

    @Test
    void delete() throws DirectorRepositoryException {
        doNothing().when(dbRepository).deleteById(any());
        Director director = Director.builder().id("directorId").build();
        directorRepository.delete(director);
        verify(dbRepository).deleteById(any());
    }
}