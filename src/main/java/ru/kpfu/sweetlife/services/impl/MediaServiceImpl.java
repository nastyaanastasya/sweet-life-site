package ru.kpfu.sweetlife.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.sweetlife.exceptions.ImageNotFoundException;
import ru.kpfu.sweetlife.exceptions.ImpossibleToUploadMediaException;
import ru.kpfu.sweetlife.services.MediaService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class MediaServiceImpl implements MediaService {

    @Value("${repository.path}")
    private String repositoryPath;

    @Override
    public String upload(MultipartFile file) {
        String fileName = UUID.randomUUID().toString() + ".png";
        Path filePath = Paths.get(repositoryPath).resolve(fileName);
        try {
            file.transferTo(Files.createFile(filePath));
        } catch (IOException e) {
            throw new ImpossibleToUploadMediaException("Image can't be uploaded");
        }
        return fileName;
    }

    @Override
    public Path download(String path) {
        File file = new File(Paths.get(repositoryPath).resolve(path).toString());
        if(file.exists()){
            return file.toPath().normalize();
        }
        throw new ImageNotFoundException("Image not found");
    }
}
