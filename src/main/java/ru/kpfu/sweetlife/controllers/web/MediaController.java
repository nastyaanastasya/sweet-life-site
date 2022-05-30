package ru.kpfu.sweetlife.controllers.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kpfu.sweetlife.exceptions.ImageNotFoundException;
import ru.kpfu.sweetlife.services.MediaService;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

@Controller
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @GetMapping("/media/{fileName}")
    public void getImage(
            @PathVariable("fileName") String filename,
            OutputStream outputStream
    ) {
        try {
            Files.copy(mediaService.download(filename), outputStream);
        } catch (IOException e) {
            throw new ImageNotFoundException("Image not found");
        }
    }
}
