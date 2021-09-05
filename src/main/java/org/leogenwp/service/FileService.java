package org.leogenwp.service;

import org.leogenwp.model.File;
import org.leogenwp.model.User;
import org.leogenwp.repository.FileRepository;
import org.leogenwp.repository.io.JavaIOFileRepository;


import java.util.List;

public class FileService {

    FileRepository fileRepository = new JavaIOFileRepository();
    public File save(File f) {
        return fileRepository.save(f);
    }
    public File getById(Integer id) {
        File file = fileRepository.getById(id);
        return file;
    }
    public List<File> getAll() {
        return fileRepository.getall();
    }

    public File update(File file) {
        file = fileRepository.update(file);
        return file;
    }

    public void deleteById(Integer id) {
        fileRepository.deleteById(id);
    }
}
