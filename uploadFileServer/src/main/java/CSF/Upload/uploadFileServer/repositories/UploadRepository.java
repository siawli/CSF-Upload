package CSF.Upload.uploadFileServer.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Template;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.cj.protocol.Resultset;

import CSF.Upload.uploadFileServer.models.Upload;

import static CSF.Upload.uploadFileServer.repositories.Queries.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Optional;

@Repository
public class UploadRepository {
    
    @Autowired
    private JdbcTemplate template;

    public boolean saveFile(MultipartFile file, String name) {

        int added = 0;

        try {
            added = template.update(SQL_SAVE_FILE, 
                name, file.getInputStream(), file.getContentType());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return added == 1;
    }

    public Optional<Upload> getFile(Integer fileId) {

        System.out.println(">>> in repo + fileId: " + fileId);
        return template.query(SQL_GET_FILE,
            (ResultSet rs) -> {
                if (!rs.next()) {
                    return Optional.empty();
                }
                return Optional.of(Upload.create(rs));
            }, fileId);
    }
}
