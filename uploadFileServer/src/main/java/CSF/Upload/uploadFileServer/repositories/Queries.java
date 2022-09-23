package CSF.Upload.uploadFileServer.repositories;

public interface Queries {

    public static final String SQL_SAVE_FILE = 
        "insert into upload (name, file, mediatype) values (?, ?, ?)";

    public static final String SQL_GET_FILE = 
        "select * from upload where file_id = ?";
    
}
