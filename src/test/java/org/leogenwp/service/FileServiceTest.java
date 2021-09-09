package org.leogenwp.service;

import org.junit.jupiter.api.Test;
import org.leogenwp.model.File;
import org.leogenwp.model.User;
import org.leogenwp.repository.FileRepository;
import org.leogenwp.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FileServiceTest {

    @Test
    void save() {
        File file = new File();
        FileRepository fileRepository = mock(FileRepository.class);
        FileService fileService_underTest = new FileService(fileRepository);
        when(fileRepository.save(file)).thenReturn(file);
        assertEquals(file,fileService_underTest.save(file));
    }

    @Test
    void getById() {
        File file = new File();
        FileRepository fileRepository = mock(FileRepository.class);
        FileService fileService_underTest = new FileService(fileRepository);
        when(fileService_underTest.getById(1)).thenReturn(file);
        assertEquals(file,fileService_underTest.getById(1));
    }

    @Test
    void getAll() {
        List<File> files = getFileList();
        FileRepository fileRepository = mock(FileRepository.class);
        FileService fileService_underTest = new FileService(fileRepository);
        when(fileService_underTest.getAll()).thenReturn(files);
        assertEquals(files,fileService_underTest.getAll());
    }

    @Test
    void update() {
        File file = new File();
        FileRepository fileRepository = mock(FileRepository.class);
        FileService fileService_underTest = new FileService(fileRepository);
        when(fileService_underTest.update(file)).thenReturn(file);
        assertEquals(file,fileService_underTest.update(file));
    }

    @Test
    void deleteById() {
        FileService fileService_underTest = mock(FileService.class);
        fileService_underTest.deleteById(5);
        verify(fileService_underTest).deleteById(5);
    }



    private List<File> getFileList() {
        List<File> files = Arrays.asList(
                new File(),
                new File()
        );
        return files;
    }
}