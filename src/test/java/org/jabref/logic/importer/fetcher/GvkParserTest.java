package org.jabref.logic.importer.fetcher;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.jabref.logic.bibtex.BibEntryAssert;
import org.jabref.logic.importer.fileformat.GvkParser;
import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.field.StandardField;
import org.jabref.testutils.category.FetcherTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.jabref.DIYcoverage.DIYCoverage; /*ASSI3: Extra class for coverage*/
@FetcherTest
public class GvkParserTest {
    private void doTest(String xmlName, int expectedSize, List<String> resourceNames) throws Exception {
        try (InputStream is = GvkParserTest.class.getResourceAsStream(xmlName)) {
            GvkParser parser = new GvkParser();
            List<BibEntry> entries = parser.parseEntries(is);
            assertNotNull(entries);
            assertEquals(expectedSize, entries.size());
            int i = 0;
            for (String resourceName : resourceNames) {
                BibEntryAssert.assertEquals(GvkParserTest.class, resourceName, entries.get(i));
                i++;
            }

            for(int k = 0; k < parser.taken.length; k++){ /*ASSI3: For branch coverage DIY*/
                if(parser.taken[k]) {
                    DIYCoverage.takenTest[0][k] = true;
                }
            }
        }
    }
    /**
     * Requirements:
     * The function returns a valid BibEntry with the same information as in the input XML-file
     * ('gvk_empty_result_because_of_bad_query.xml')
     * */
    @Test
    public void emptyResult() throws Exception {
        doTest("gvk_empty_result_because_of_bad_query.xml", 0, Collections.emptyList());
    }
    /**
     * Requirements:
     * The function returns a valid BibEntry with the same information as in the input XML-file
     * ('gvk_empty_result_because_of_bad_query.xml')
     * */
    @Test
    public void resultFor797485368() throws Exception {
        doTest("gvk_result_for_797485368.xml", 1, Collections.singletonList("gvk_result_for_797485368.bib"));
    }
    /**
     * Requirements:
     * The function returns valid BibEntries with the same information as in the input XML-file
     * ('gvk_gmp.xml')
     * */
    @Test
    public void testGMP() throws Exception {
        doTest("gvk_gmp.xml", 2, Arrays.asList("gvk_gmp.1.bib", "gvk_gmp.2.bib"));
    }

    /**
     * Added test *
     *Requirements:
     * The function returns a valid BibEntry with the same information as in the input XML-file
     * ('gvk_sebveij_added_tests_2.xml')
     * */
    @Test
    public void resultFor_sebveij_added_tests() throws Exception { /*ASSI3: For branch coverage DIY*/
        doTest("gvk_sebveij_added_tests.xml", 1, Collections.singletonList("gvk_sebveij_added_tests.bib"));
    }
    /**
     * Added test *
     *Requirements:
     * The function returns a valid BibEntry with the same information as in the input XML-file
     * ('gvk_sebveij_added_tests_2.xml')
     * */
    @Test
    public void resultFor_sebveij_added_tests_2() throws Exception { /*ASSI3: For branch coverage DIY*/
        doTest("gvk_sebveij_added_tests_2.xml", 1, Collections.singletonList("gvk_sebveij_added_tests_2.bib"));
    }

    /**
     * Added test *
     *Requirements:
     * The function returns a valid BibEntry where the "SUBTITLE" field matches the XML-file's elements
     * but with a capitalized first letter ('C', 'Word', 'Word1 word2', 'Word1 word2')
     * */
    @Test
    public void subTitleTest() throws Exception {
        try (InputStream is = GvkParserTest.class.getResourceAsStream("gvk_artificial_subtitle_test.xml")) {
            GvkParser parser = new GvkParser();
            List<BibEntry> entries = parser.parseEntries(is);
            assertNotNull(entries);
            assertEquals(5, entries.size());

            assertEquals(Optional.empty(), entries.get(0).getField(StandardField.SUBTITLE));
            assertEquals(Optional.of("C"), entries.get(1).getField(StandardField.SUBTITLE));
            assertEquals(Optional.of("Word"), entries.get(2).getField(StandardField.SUBTITLE));
            assertEquals(Optional.of("Word1 word2"), entries.get(3).getField(StandardField.SUBTITLE));
            assertEquals(Optional.of("Word1 word2"), entries.get(4).getField(StandardField.SUBTITLE));
            for(int k = 0; k < parser.taken.length; k++){ /*ASSI3: For branch coverage DIY*/
                if(parser.taken[k]) {
                    DIYCoverage.takenTest[0][k] = true;
                }
            }
        }

    }

    @AfterAll
    public static void printCoverage(){ /*ASSI3: For branch coverage DIY*/
        DIYCoverage.printAllTrue();
    }

}
