package ru.kpfu.sweetlife.services;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface MediaService {
    String upload(MultipartFile file);
    Path download(String path);
}
