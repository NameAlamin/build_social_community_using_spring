package com.config;

import com.entity.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Utils {

    @Autowired
    private ServletContext servletContext;

    public static Attachment saveFile(MultipartFile file, String path) {
        Path rootLocation = Paths.get(Properties.WRITE_PATH + path);

        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            Path destinationFile = rootLocation.resolve(fileName);

            if (!destinationFile.toFile().exists()) {
                destinationFile.toFile().mkdirs();
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);

                Attachment attachment = new Attachment();
                attachment.setAttachmentName(fileName);
                attachment.setAttachmentPath(path + fileName);
                attachment.setAttachmentType(file.getContentType());

                return attachment;
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

//    public static Attachment saveFile(MultipartFile file, String path) throws IOException {
//        Path rootLocation = Paths.get(System.getProperty("user.home") + "/social-community");
//
//        byte[] bytes  = file.getBytes();
//
//        String filePath = servletContext.getRealPath(path);
//        File dir = new File(filePath);
//        File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getName());
//
//        //System.out.println("EmployeeService.saveFile()>>>>" + serverFile.getAbsolutePath());
//
//        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
//        stream.write(bytes);
//        stream.close();
//
//        return null;
//    }

}
