package com.magellian.movies.domain.service;

import com.magellian.movies.domain.exceptions.DirectorManagementException;
import com.magellian.movies.domain.exceptions.DirectorRepositoryException;
import com.magellian.movies.domain.model.Director;
import com.magellian.movies.domain.outbound.DirectorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DirectorServiceImplTest {

    @Mock
    DirectorRepository directorRepository;

    private DirectorsService directorService;

    @BeforeEach
    public void init() {
        directorService = new DirectorServiceImpl(directorRepository);
    }

    @Test
    void create_ErrorInPersistence() throws DirectorRepositoryException {
        Director d = getTestDirector();
        when(directorRepository.create(any())).thenThrow(new DirectorRepositoryException("create test"));
        DirectorManagementException ex = assertThrows(DirectorManagementException.class,
                () -> directorService.create(d));
        Assertions.assertEquals("create test", ex.getCause().getMessage());
    }
    @Test
    void create_OK() throws DirectorRepositoryException, DirectorManagementException {
        String expectedID = UUID.randomUUID().toString();
        Director d = getTestDirector();
        when(directorRepository.create(any())).thenReturn(expectedID);
        String actual = directorService.create(d);
        Assertions.assertEquals(expectedID, actual);
    }

    @Test
    void delete_ErrorInPersistence() throws DirectorRepositoryException {
        Director d = getTestDirector();
        doThrow(new DirectorRepositoryException("delete test")).when(directorRepository).delete(any());
        DirectorManagementException ex = assertThrows(DirectorManagementException.class,
                () -> directorService.delete(d));
        Assertions.assertEquals("delete test", ex.getCause().getMessage());
    }

    @Test
    void delete_OK() throws DirectorRepositoryException, DirectorManagementException {
        doNothing().when(directorRepository).delete(any());
        directorService.delete(getTestDirector());
        verify(directorRepository).delete(any());
    }

    private static Director getTestDirector() {
        return new Director("directorID", "Director 1");
    }
}