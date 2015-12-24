package ca.nexapp.core.infrastructure.files.reader;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ByLineFileReaderITest {

    private static final String FILE_PATH = "/aFile.txt";
    private static final String UNEXISTING_FILE = "aaaaa";

    private static final int NUMBER_OF_LINES_IN_FILE = 3;

    private static final String FIRST_LINE = "first line";
    private static final String SECOND_LINE = "second line";
    private static final String THIRD_LINE = "third line";

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
    public void givenAFileWhenReadingItShouldCorrespondToTheExpectedContent() {
        List<String> lines = fileReader.read(FILE_PATH);

        assertThat(lines).containsExactly(FIRST_LINE, SECOND_LINE, THIRD_LINE).inOrder();
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenAnUnexistingFileWhenReadingItShouldThrowAnException() {
        fileReader.read(UNEXISTING_FILE);
    }
}
