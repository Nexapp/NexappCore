package ca.nexapp.core.infrastructure.files.reader;

public interface FileReader<T> {

    T read(String filePath);
}
