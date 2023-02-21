package org.jabref.logic.importer.fileformat;

import org.jabref.DIYcoverage.DIYCoverage; /*ASSI3: Extra class for coverage*/
import java.nio.file.Path;
import java.util.Collections;

import org.jabref.logic.util.StandardFileType;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BiblioscapeImporterTest {

    private BiblioscapeImporter importer;

    @BeforeEach
    public void setUp() throws Exception {
        importer = new BiblioscapeImporter();
    }

    @Test
    public void testGetFormatName() {
        assertEquals("Biblioscape", importer.getName());
    }

    @Test
    public void testsGetExtensions() {
        assertEquals(StandardFileType.TXT, importer.getFileType());
    }

    @Test
    public void testGetDescription() {
        assertEquals("Imports a Biblioscape Tag File.\n" +
                "Several Biblioscape field types are ignored. Others are only included in the BibTeX field \"comment\".", importer.getDescription());
    }

    @Test
    public void testGetCLIID() {
        assertEquals("biblioscape", importer.getId());
    }

    @Test
    public void testImportEntriesAbortion() throws Throwable {
        Path file = Path.of(BiblioscapeImporter.class.getResource("BiblioscapeImporterTestCorrupt.txt").toURI());
        assertEquals(Collections.emptyList(),
                importer.importDatabase(file).getDatabase().getEntries());
        for(int k = 0; k < importer.taken.length; k++){ /*ASSI3: For branch coverage DIY*/
            if(importer.taken[k]) {
                DIYCoverage.takenTest[0][k] = true;
            }
        }
    }

    @AfterAll
    public static void printCoverage(){
        DIYCoverage.printAllTrue();
        DIYCoverage.cleanArray(0);
    }
}



