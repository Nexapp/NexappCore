package ca.nexapp.infrastructure.files.parser;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

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
        assertEquals(NUMBER_OF_LINES_IN_FILE, lines.size());
    }

    @Test
    public void givenAFileWhenReadingItShouldCorrespondToTheExpectedList() {
        List<String[]> lines = fileParser.parse(FILE_PATH);

        assertArrayEquals(FIRST_LINE, lines.get(0));
        assertArrayEquals(SECOND_LINE, lines.get(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenAnUnexistingFileWhenReadingItShouldThrowAnException() {
        fileParser.parse(UNEXISTING_FILE);
    }
}
