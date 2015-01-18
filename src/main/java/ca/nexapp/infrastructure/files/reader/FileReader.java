package ca.nexapp.infrastructure.files.reader;

public interface FileReader<T> {

    T read(String filePath);
}