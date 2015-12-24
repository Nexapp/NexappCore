package ca.nexapp.core.infrastructure.files.reader;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ByLineFileReader implements FileReader<List<String>> {

    @Override
    public List<String> read(String filePath) {
        try {
            Path pathResource = Paths.get(getClass().getResource(filePath).toURI());
            return Files.readAllLines(pathResource, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot read file '" + filePath + "'.");
        }
    }

}
