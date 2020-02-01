package life.majiang.community.controller;

import life.majiang.community.dto.FileDTO;
import life.majiang.community.provider.ALiYunProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Controller
public class FileController {

    @Autowired
    private ALiYunProvider aLiYunProvider;

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("editormd-image-file");
        String fileName = aLiYunProvider.upload(file.getInputStream(), Objects.requireNonNull(file.getOriginalFilename()));
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setMessage("success");
        fileDTO.setUrl(fileName);
        return fileDTO;

    }
}
