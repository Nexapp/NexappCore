package ca.nexapp.core.infrastructure.files.reader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CSVFileReader implements FileReader<List<String[]>> {

    @Override
    public List<String[]> read(String filePath) {
        List<String> nonParsedLines = new ByLineFileReader().read(filePath);
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
