package ca.nexapp.infrastructure.files.parser;

public interface FileParser<T> {

    T parse(String filePath);
}
