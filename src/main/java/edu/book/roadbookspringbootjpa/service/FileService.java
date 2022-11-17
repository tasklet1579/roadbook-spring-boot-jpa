package edu.book.roadbookspringbootjpa.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Log
@Service
public class FileService {
    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws IOException {
        UUID uuid = UUID.randomUUID();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String savedFileName = uuid + extension;
        String fileUploadFulUrl = uploadPath + "/" + savedFileName;
        FileOutputStream fileOutputStream = new FileOutputStream(fileUploadFulUrl);
        fileOutputStream.write(fileData);
        fileOutputStream.close();
        return savedFileName;
    }

    public void deleteFile(String filePath) {
        File file = new File(filePath);

        if (file.exists()) {
            file.delete();
            log.info("파일을 삭제하였습니다.");
        } else {
            log.info("파일이 존재하지 않습니다.");
        }
    }
}
