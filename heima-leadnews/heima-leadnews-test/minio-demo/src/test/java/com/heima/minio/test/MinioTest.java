package com.heima.minio.test;

import com.heima.file.service.FileStorageService;
import com.heima.minio.MinioDemoApplication;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest(classes = MinioDemoApplication.class)
@RunWith(SpringRunner.class)
public class MinioTest {

    @Autowired
    private FileStorageService fileStorageService;

    @Test
    public void test(){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("C:/project/java/plugins/js/index.js");

            MinioClient minioClient = MinioClient.builder()
                    .credentials("root", "cghmyr123")
                    .endpoint("http://123.249.120.19:9000/").build();
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .object("/plugins/js/index.js")
                    .contentType("text/js")
                    .bucket("leadnews")
                    .stream(fileInputStream, fileInputStream.available(), -1)
                    .build();

            minioClient.putObject(putObjectArgs);
            System.out.println("http://123.249.120.19:9000/leadnews/list.html");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public  void testfileStorageService() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("C:/project/templates/list.html");
        String path = fileStorageService.uploadHtmlFile("", "list.html", fileInputStream);
        System.out.println(path);
    }


}
