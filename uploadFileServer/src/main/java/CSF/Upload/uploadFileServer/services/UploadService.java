package CSF.Upload.uploadFileServer.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import CSF.Upload.uploadFileServer.models.Upload;
import CSF.Upload.uploadFileServer.repositories.UploadRepository;

@Service
public class UploadService {

    @Autowired
    private UploadRepository uploadRepo;

    public boolean saveFile(MultipartFile file, String name) {
        return uploadRepo.saveFile(file, name);
    }

    public Optional<Upload> getFile(Integer fileId) {
        return uploadRepo.getFile(fileId);
    }    
}
