package ca.nexapp.infrastructure.files.parser;

import static com.google.common.truth.Truth.*;

import java.util.List;

import ca.nexapp.infrastructure.files.reader.ByLineFileReader;
import org.junit.Before;
import org.junit.Test;

public class CSVFileReaderITest {

    private static final String FILE_PATH = "/csvFile.csv";
    private static final String UNEXISTING_FILE = "aaaaa";

    private static final int NUMBER_OF_LINES_IN_FILE = 2;

    private static final String[] FIRST_LINE = { "a", "b", "c" };
    private static final String[] SECOND_LINE = { "1", "2", "3" };

    private FileParser<List<String[]>> fileParser;

    @Before
    public void setUp() {
        fileParser = new CSVFileParser(new ByLineFileReader());
    }

    @Test
    public void givenAFileWhenReadingItShouldReturnTheExactSameNumberOfLines() {
        List<String[]> lines = fileParser.parse(FILE_PATH);

        assertThat(lines).hasSize(NUMBER_OF_LINES_IN_FILE);
    }

    @Test
    public void givenAFileWhenReadingItShouldCorrespondToTheExpectedList() {
        List<String[]> lines = fileParser.parse(FILE_PATH);

        assertThat(FIRST_LINE).isEqualTo(lines.get(0));
        assertThat(SECOND_LINE).isEqualTo(lines.get(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenAnUnexistingFileWhenReadingItShouldThrowAnException() {
        fileParser.parse(UNEXISTING_FILE);
    }
}
