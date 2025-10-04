package urban.filestorage.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import urban.filestorage.service.FileService;

import java.io.IOException;
import java.net.URISyntaxException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class FileController {
    private final FileService fileService;

    @PostMapping("/item")
    public ResponseEntity<Boolean> uploadFile(@RequestParam("file") MultipartFile file) {
        if(file.isEmpty()) {
            return ResponseEntity.badRequest().body(false);
        }
        String path = "/" + file.getOriginalFilename();

        try {
            fileService.upload(path, file.getBytes());
            return ResponseEntity.ok(true);
        } catch (URISyntaxException | IOException e) {
            log.error(e.getMessage());
        }

        return ResponseEntity.ok(false);
    }
}
