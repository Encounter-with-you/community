package life.majiang.community.dto;

import lombok.Data;

import java.net.URL;

@Data
public class FileDTO {
    private int success;
    private String message;
    private String url;
}
