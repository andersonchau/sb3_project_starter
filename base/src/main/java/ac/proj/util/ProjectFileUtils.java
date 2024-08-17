package ac.proj.util;
import org.aspectj.apache.bcel.util.ClassPath;
import org.springframework.util.ResourceUtils;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import static ac.proj.util.FileLocType.CLASS_PATH;

public class ProjectFileUtils {

    @FunctionalInterface
    public interface GetFileContentInf {
        String getFileContentAsString(String path) throws IOException;
    }

    public static String getFileContentAsString(FileLocType locType, String path) throws IOException {
        GetFileContentInf fileGetter = null;
        switch (locType) {
            case HTTP_FILE: // TODO
            case FILE_SYSTEM:
                fileGetter = (String myPath) -> {
                    return Files.readString(Paths.get(myPath));
                };
            case CLASS_PATH:
            default:
                fileGetter = (String myPath) -> {
                    ClassPathResource cps = new ClassPathResource(myPath);
                    return IOUtils.toString(cps.getInputStream(), StandardCharsets.UTF_8);
                };
        }
        return fileGetter.getFileContentAsString(path);

    }

    public static void saveFile(String path, byte[] fileData) throws IOException {
        Files.write(Paths.get(path), fileData);
    }
}


