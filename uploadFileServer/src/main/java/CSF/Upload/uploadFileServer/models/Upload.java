package CSF.Upload.uploadFileServer.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;

public class Upload {
    private String name;
    private String filetype;
    private byte[] content;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFiletype() {
        return filetype;
    }
    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }
    public byte[] getContent() {
        return content;
    }
    public void setContent(byte[] content) {
        this.content = content;
    }

    public static Upload create(ResultSet rs) throws SQLException {
        Upload u = new Upload();
        u.setName(rs.getString("name"));
        u.setFiletype(rs.getString("mediatype"));
        u.setContent(rs.getBytes("file"));

        System.out.println(">>>>> filename: " + u.name);
        return u;
    }
    
}
