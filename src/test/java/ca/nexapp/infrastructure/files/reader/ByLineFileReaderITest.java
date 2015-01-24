package ca.nexapp.infrastructure.files.reader;

import static com.google.common.truth.Truth.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ByLineFileReaderITest {

    private static final String FILE_PATH = "/aFile.txt";
    private static final String UNEXISTING_FILE = "aaaaa";

    private static final int NUMBER_OF_LINES_IN_FILE = 3;

    private static final List<String> EXPECTED_LIST = Arrays.asList("first line", "second line", "third line");

    private FileReader<List<String>> fileReader;

    @Before
    public void setUp() {
	    fileReader = new ByLineFileReader();
    }

    @Test
    public void givenAFileWhenReadingItShouldReturnTheExactSameNumberOfLines() {
        List<String> lines = fileReader.read(FILE_PATH);

        assertThat(lines).hasSize(NUMBER_OF_LINES_IN_FILE);
    }

    @Test
    public void givenAFileWhenReadingItShouldCorrespondToTheExpectedList() {
        List<String> lines = fileReader.read(FILE_PATH);

        assertThat(lines).isEqualTo(EXPECTED_LIST);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenAnUnexistingFileWhenReadingItShouldThrowAnException() {
        fileReader.read(UNEXISTING_FILE);
    }
}
