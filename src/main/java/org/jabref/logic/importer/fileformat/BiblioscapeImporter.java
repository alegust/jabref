package org.jabref.logic.importer.fileformat;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import org.jabref.logic.importer.Importer;
import org.jabref.logic.importer.ParserResult;
import org.jabref.logic.util.StandardFileType;
import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.field.Field;
import org.jabref.model.entry.field.StandardField;
import org.jabref.model.entry.types.EntryType;
import org.jabref.model.entry.types.StandardEntryType;

/**
 * Imports a Biblioscape Tag File. The format is described on
 * http://www.biblioscape.com/download/Biblioscape8.pdf Several
 * Biblioscape field types are ignored. Others are only included in the BibTeX
 * field "comment".
 */
public class BiblioscapeImporter extends Importer {
    int branchIdx = 2; /*ASSI3: For branch coverage DIY*/
    public static boolean[] taken = new boolean[1000]; /*ASSI3: Temporary array for branch coverage DIY */


    @Override
    public String getName() {
        return "Biblioscape";
    }

    @Override
    public StandardFileType getFileType() {
        return StandardFileType.TXT;
    }

    @Override
    public String getDescription() {
        return "Imports a Biblioscape Tag File.\n" +
                "Several Biblioscape field types are ignored. Others are only included in the BibTeX field \"comment\".";
    }

    @Override
    public boolean isRecognizedFormat(BufferedReader reader) {
        Objects.requireNonNull(reader);
        return true;
    }

    @Override
    public ParserResult importDatabase(BufferedReader reader) throws IOException {
        List<BibEntry> bibItems = new ArrayList<>();
        String line;
        Map<Field, String> hm = new HashMap<>();
        Map<String, StringBuilder> lines = new HashMap<>();
        StringBuilder previousLine = null;
        while ((line = reader.readLine()) != null) {
            //taken[0] = true; /*ASSI3: For branch coverage DIY*/
            if (line.isEmpty()) {
                taken[0] = true; /*ASSI3: For branch coverage DIY*/
                continue; // ignore empty lines, e.g. at file
            }else{taken[1] = true; /*ASSI3: For branch coverage DIY*/}
            // end
            // entry delimiter -> item complete
            if ("------".equals(line)) {
                taken[2] = true; /*ASSI3: For branch coverage DIY*/
                String[] type = new String[2];
                String[] pages = new String[2];
                String country = null;
                String address = null;
                String titleST = null;
                String titleTI = null;
                List<String> comments = new ArrayList<>();
                // add item
                for (Map.Entry<String, StringBuilder> entry : lines.entrySet()) {
                    //taken[3] = true; /*ASSI3: For branch coverage DIY*/
                    if ("AU".equals(entry.getKey())) {
                        taken[5] = true; /*ASSI3: For branch coverage DIY*/
                        hm.put(StandardField.AUTHOR, entry.getValue()
                                                          .toString());
                    }
                    else if ("TI".equals(entry.getKey())) {
                        taken[6] = true; /*ASSI3: For branch coverage DIY*/
                        titleTI = entry.getValue()
                                       .toString();
                    } else if ("ST".equals(entry.getKey())) {
                        taken[7] = true; /*ASSI3: For branch coverage DIY*/
                        titleST = entry.getValue()
                                       .toString();
                    } else if ("YP".equals(entry.getKey())) {
                        taken[8] = true; /*ASSI3: For branch coverage DIY*/
                        hm.put(StandardField.YEAR, entry
                                .getValue().toString());
                    } else if ("VL".equals(entry.getKey())) {
                        taken[9] = true; /*ASSI3: For branch coverage DIY*/
                        hm.put(StandardField.VOLUME, entry
                                .getValue().toString());
                    } else if ("NB".equals(entry.getKey())) {
                        taken[10] = true; /*ASSI3: For branch coverage DIY*/
                        hm.put(StandardField.NUMBER, entry
                                .getValue().toString());
                    } else if ("PS".equals(entry.getKey())) {
                        taken[11] = true; /*ASSI3: For branch coverage DIY*/
                        pages[0] = entry.getValue()
                                        .toString();
                    } else if ("PE".equals(entry.getKey())) {
                        taken[12] = true; /*ASSI3: For branch coverage DIY*/
                        pages[1] = entry.getValue()
                                        .toString();
                    } else if ("KW".equals(entry.getKey())) {
                        taken[13] = true; /*ASSI3: For branch coverage DIY*/
                        hm.put(StandardField.KEYWORDS, entry
                                .getValue().toString());
                    } else if ("RT".equals(entry.getKey())) {
                        taken[14] = true; /*ASSI3: For branch coverage DIY*/
                        type[0] = entry.getValue()
                                       .toString();
                    } else if ("SB".equals(entry.getKey())) {
                        taken[15] = true; /*ASSI3: For branch coverage DIY*/
                        comments.add("Subject: "
                                + entry.getValue());
                    } else if ("SA".equals(entry.getKey())) {
                        taken[16] = true; /*ASSI3: For branch coverage DIY*/
                        comments
                                .add("Secondary Authors: " + entry.getValue());
                    } else if ("NT".equals(entry.getKey())) {
                        taken[17] = true; /*ASSI3: For branch coverage DIY*/
                        hm.put(StandardField.NOTE, entry
                                .getValue().toString());
                    } else if ("PB".equals(entry.getKey())) {
                        taken[18] = true; /*ASSI3: For branch coverage DIY*/
                        hm.put(StandardField.PUBLISHER, entry
                                .getValue().toString());
                    } else if ("TA".equals(entry.getKey())) {
                        taken[19] = true; /*ASSI3: For branch coverage DIY*/
                        comments
                                .add("Tertiary Authors: " + entry.getValue());
                    } else if ("TT".equals(entry.getKey())) {
                        taken[20] = true; /*ASSI3: For branch coverage DIY*/
                        comments
                                .add("Tertiary Title: " + entry.getValue());
                    } else if ("ED".equals(entry.getKey())) {
                        taken[21] = true; /*ASSI3: For branch coverage DIY*/
                        hm.put(StandardField.EDITION, entry
                                .getValue().toString());
                    } else if ("TW".equals(entry.getKey())) {
                        taken[22] = true; /*ASSI3: For branch coverage DIY*/
                        type[1] = entry.getValue()
                                       .toString();
                    } else if ("QA".equals(entry.getKey())) {
                        taken[23] = true; /*ASSI3: For branch coverage DIY*/
                        comments
                                .add("Quaternary Authors: " + entry.getValue());
                    } else if ("QT".equals(entry.getKey())) {
                        taken[24] = true; /*ASSI3: For branch coverage DIY*/
                        comments
                                .add("Quaternary Title: " + entry.getValue());
                    } else if ("IS".equals(entry.getKey())) {
                        taken[25] = true; /*ASSI3: For branch coverage DIY*/
                        hm.put(StandardField.ISBN, entry
                                .getValue().toString());
                    } else if ("AB".equals(entry.getKey())) {
                        taken[26] = true; /*ASSI3: For branch coverage DIY*/
                        hm.put(StandardField.ABSTRACT, entry
                                .getValue().toString());
                    } else if ("AD".equals(entry.getKey())) {
                        taken[27] = true; /*ASSI3: For branch coverage DIY*/
                        address = entry.getValue()
                                       .toString();
                    } else if ("LG".equals(entry.getKey())) {
                        taken[28] = true; /*ASSI3: For branch coverage DIY*/
                        hm.put(StandardField.LANGUAGE, entry
                                .getValue().toString());
                    } else if ("CO".equals(entry.getKey())) {
                        taken[29] = true; /*ASSI3: For branch coverage DIY*/
                        country = entry.getValue()
                                       .toString();
                    } else if ("UR".equals(entry.getKey()) || "AT".equals(entry.getKey())) {
                        taken[30] = true; /*ASSI3: For branch coverage DIY*/
                        String s = entry.getValue().toString().trim();
                        hm.put(s.startsWith("http://") || s.startsWith("ftp://") ? StandardField.URL
                                : StandardField.PDF, entry.getValue().toString());
                    } else if ("C1".equals(entry.getKey())) {
                        taken[31] = true; /*ASSI3: For branch coverage DIY*/
                        comments.add("Custom1: "
                                + entry.getValue());
                    } else if ("C2".equals(entry.getKey())) {
                        taken[32] = true; /*ASSI3: For branch coverage DIY*/
                        comments.add("Custom2: "
                                + entry.getValue());
                    } else if ("C3".equals(entry.getKey())) {
                        taken[33] = true; /*ASSI3: For branch coverage DIY*/
                        comments.add("Custom3: "
                                + entry.getValue());
                    } else if ("C4".equals(entry.getKey())) {
                        taken[34] = true; /*ASSI3: For branch coverage DIY*/
                        comments.add("Custom4: "
                                + entry.getValue());
                    } else if ("C5".equals(entry.getKey())) {
                        taken[35] = true; /*ASSI3: For branch coverage DIY*/
                        comments.add("Custom5: "
                                + entry.getValue());
                    } else if ("C6".equals(entry.getKey())) {
                        taken[36] = true; /*ASSI3: For branch coverage DIY*/
                        comments.add("Custom6: "
                                + entry.getValue());
                    } else if ("DE".equals(entry.getKey())) {
                        taken[37] = true; /*ASSI3: For branch coverage DIY*/
                        hm.put(StandardField.ANNOTE, entry
                                .getValue().toString());
                    } else if ("CA".equals(entry.getKey())) {
                        taken[38] = true; /*ASSI3: For branch coverage DIY*/
                        comments.add("Categories: "
                                + entry.getValue());
                    } else if ("TH".equals(entry.getKey())) {
                        taken[39] = true; /*ASSI3: For branch coverage DIY*/
                        comments.add("Short Title: "
                                + entry.getValue());
                    } else if ("SE".equals(entry.getKey())) {
                        taken[40] = true; /*ASSI3: For branch coverage DIY*/
                        hm.put(StandardField.CHAPTER, entry
                                .getValue().toString());
                        // else if (entry.getKey().equals("AC"))
                        //   hm.put("",entry.getValue().toString());
                        // else if (entry.getKey().equals("LP"))
                        //   hm.put("",entry.getValue().toString());
                    }else{taken[41] = true; /*ASSI3: For branch coverage DIY*/}
                }

                EntryType bibtexType = BibEntry.DEFAULT_TYPE;
                // to find type, first check TW, then RT
                for (int i = 1; (i >= 0) && BibEntry.DEFAULT_TYPE.equals(bibtexType); --i) {
                    if (type[i] == null) {
                        taken[42] = true; /*ASSI3: For branch coverage DIY*/
                        continue;
                    }else{taken[43] = true; /*ASSI3: For branch coverage DIY*/}
                    type[i] = type[i].toLowerCase(Locale.ROOT);
                    if (type[i].contains("article")) {
                        taken[44] = true; /*ASSI3: For branch coverage DIY*/
                        bibtexType = StandardEntryType.Article;
                    }
                    else if (type[i].contains("journal")) {
                        taken[45] = true; /*ASSI3: For branch coverage DIY*/
                        bibtexType = StandardEntryType.Article;
                    } else if (type[i].contains("book section")) {
                        taken[46] = true; /*ASSI3: For branch coverage DIY*/
                        bibtexType = StandardEntryType.InBook;
                    } else if (type[i].contains("book")) {
                        taken[47] = true; /*ASSI3: For branch coverage DIY*/
                        bibtexType = StandardEntryType.Book;
                    } else if (type[i].contains("conference")) {
                        taken[48] = true; /*ASSI3: For branch coverage DIY*/
                        bibtexType = StandardEntryType.InProceedings;
                    } else if (type[i].contains("proceedings")) {
                        taken[49] = true; /*ASSI3: For branch coverage DIY*/
                        bibtexType = StandardEntryType.InProceedings;
                    } else if (type[i].contains("report")) {
                        taken[50] = true; /*ASSI3: For branch coverage DIY*/
                        bibtexType = StandardEntryType.TechReport;
                    } else if (type[i].contains("thesis")
                            && type[i].contains("master")) {
                        taken[51] = true; /*ASSI3: For branch coverage DIY*/
                        bibtexType = StandardEntryType.MastersThesis;
                    } else if (type[i].contains("thesis")) {
                        taken[52] = true; /*ASSI3: For branch coverage DIY*/
                        bibtexType = StandardEntryType.PhdThesis;
                    }else{taken[53] = true; /*ASSI3: For branch coverage DIY*/}
                }

                // depending on bibtexType, decide where to place the titleRT and
                // titleTI
                if (bibtexType.equals(StandardEntryType.Article)) {
                    taken[54] = true; /*ASSI3: For branch coverage DIY*/
                    if (titleST != null) {
                        taken[55] = true; /*ASSI3: For branch coverage DIY*/
                        hm.put(StandardField.JOURNAL, titleST);
                    }else{taken[56] = true; /*ASSI3: For branch coverage DIY*/}
                    if (titleTI != null) {
                        taken[57] = true; /*ASSI3: For branch coverage DIY*/
                        hm.put(StandardField.TITLE, titleTI);
                    }else{taken[58] = true; /*ASSI3: For branch coverage DIY*/}
                } else if (bibtexType.equals(StandardEntryType.InBook)) {
                    taken[59] = true; /*ASSI3: For branch coverage DIY*/
                    if (titleST != null) {
                        taken[60] = true; /*ASSI3: For branch coverage DIY*/
                        hm.put(StandardField.BOOKTITLE, titleST);
                    }else{taken[61] = true; /*ASSI3: For branch coverage DIY*/}
                    if (titleTI != null) {
                        taken[62] = true; /*ASSI3: For branch coverage DIY*/
                        hm.put(StandardField.TITLE, titleTI);
                    }else{taken[63] = true; /*ASSI3: For branch coverage DIY*/}
                } else {
                    taken[64] = true; /*ASSI3: For branch coverage DIY*/
                    if (titleST != null) {
                        taken[65] = true; /*ASSI3: For branch coverage DIY*/
                        hm.put(StandardField.BOOKTITLE, titleST);
                    }else{taken[66] = true; /*ASSI3: For branch coverage DIY*/}
                    if (titleTI != null) {
                        taken[67] = true; /*ASSI3: For branch coverage DIY*/
                        hm.put(StandardField.TITLE, titleTI);
                    }else{taken[68] = true; /*ASSI3: For branch coverage DIY*/}
                }

                // concatenate pages
                if ((pages[0] != null) || (pages[1] != null)) {
                    taken[69] = true; /*ASSI3: For branch coverage DIY*/
                    hm.put(StandardField.PAGES, (pages[0] == null ? "" : pages[0]) + (pages[1] == null ? "" : "--" + pages[1]));
                }else{taken[70] = true; /*ASSI3: For branch coverage DIY*/}

                // concatenate address and country
                if (address != null) {
                    taken[71] = true; /*ASSI3: For branch coverage DIY*/
                    hm.put(StandardField.ADDRESS, address + (country == null ? "" : ", " + country));
                }else{taken[72] = true; /*ASSI3: For branch coverage DIY*/}

                if (!comments.isEmpty()) { // set comment if present
                    taken[73] = true; /*ASSI3: For branch coverage DIY*/
                    hm.put(StandardField.COMMENT, String.join(";", comments));
                }else{taken[74] = true; /*ASSI3: For branch coverage DIY*/}
                BibEntry b = new BibEntry(bibtexType);
                b.setField(hm);
                bibItems.add(b);

                hm.clear();
                lines.clear();
                previousLine = null;

                continue;
            }
            // new key
            if (line.startsWith("--") && (line.length() >= 7)
                    && "-- ".equals(line.substring(4, 7))) {
                taken[75] = true; /*ASSI3: For branch coverage DIY*/
                previousLine = new StringBuilder(line.substring(7));
                lines.put(line.substring(2, 4), previousLine);
                continue;
            }else{taken[76] = true; /*ASSI3: For branch coverage DIY*/}
            // continuation (folding) of previous line
            if (previousLine == null) {
                taken[77] = true; /*ASSI3: For branch coverage DIY*/
                return new ParserResult();
            }else{taken[78] = true; /*ASSI3: For branch coverage DIY*/}
            previousLine.append(line.trim());
        }

        return new ParserResult(bibItems);
    }
}
