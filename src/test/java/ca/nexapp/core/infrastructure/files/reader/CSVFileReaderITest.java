package ca.nexapp.core.infrastructure.files.reader;

import static com.google.common.truth.Truth.assertThat;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CSVFileReaderITest {

    private static final String UNEXISTING_FILE = "aaaaa";

    private static final int NUMBER_OF_LINES_IN_FILE = 2;

    private static final String[] FIRST_LINE = { "a", "b", "c" };
    private static final String[] SECOND_LINE = { "1", "2", "3" };

    private String filePath;
    private FileReader<List<String[]>> fileReader;

    @Before
    public void setUp() throws URISyntaxException {
        fileReader = new CSVFileReader();
        filePath = Paths.get(getClass().getResource("/csvFile.csv").toURI()).toString();
    }

    @Test
    public void givenAFile_WhenReadingIt_ShouldReturnTheExactSameNumberOfLines() {
        List<String[]> lines = fileReader.read(filePath);

        assertThat(lines).hasSize(NUMBER_OF_LINES_IN_FILE);
    }

    @Test
    public void givenAFile_WhenReadingIt_ShouldCorrespondToTheExpectedContent() {
        List<String[]> lines = fileReader.read(filePath);

        assertThat(lines.get(0)).isEqualTo(FIRST_LINE);
        assertThat(lines.get(1)).isEqualTo(SECOND_LINE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenAnUnexistingFile_WhenReadingIt_ShouldThrowAnException() {
        fileReader.read(UNEXISTING_FILE);
    }
}
