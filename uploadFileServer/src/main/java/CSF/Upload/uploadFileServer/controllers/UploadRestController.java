package CSF.Upload.uploadFileServer.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import CSF.Upload.uploadFileServer.models.Upload;
import CSF.Upload.uploadFileServer.services.UploadService;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;

@RestController
@RequestMapping("/upload")
public class UploadRestController {

    @Autowired
    private UploadService uploadSvc;

    @PostMapping()
    public ResponseEntity<String> savedFile(
            @RequestPart MultipartFile file, @RequestPart String name) {
                
        JsonObjectBuilder response = Json.createObjectBuilder();

        if (uploadSvc.saveFile(file, name)) {
            return ResponseEntity.status(HttpStatus.OK)
                        .body(response
                        .add("name", name)
                        .add("filename", file.getOriginalFilename())
                        .add("filetype", file.getContentType())
                        .build().toString());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body("{}");
        }
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<byte[]> getFile(@PathVariable String fileId) {

        Optional<Upload> getFileOtp = uploadSvc.getFile(Integer.parseInt(fileId));

        if (getFileOtp.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("{}".getBytes());
        }

        Upload file = getFileOtp.get();

        return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.parseMediaType(file.getFiletype()))
                    .body(file.getContent());
    }
}
