package ca.nexapp.infrastructure.files.parser;

import ca.nexapp.infrastructure.files.reader.FileReader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CSVFileParser implements FileParser<List<String[]>> {

    private final FileReader<List<String>> fileReader;

    public CSVFileParser(FileReader<List<String>> fileReader) {
        this.fileReader = fileReader;
    }

    @Override
    public List<String[]> parse(String filePath) {
        List<String> nonParsedLines = fileReader.read(filePath);
        return parseAllLines(nonParsedLines);
    }

    private List<String[]> parseAllLines(Collection<String> nonParsedLines) {
        List<String[]> parsedLines = new ArrayList<>(nonParsedLines.size());
        for (String nonParsedLine : nonParsedLines) {
            parsedLines.add(parseLine(nonParsedLine));
        }
        return parsedLines;
    }

    private String[] parseLine(String nonParsedLine) {
        return nonParsedLine.split(",");
    }
}
