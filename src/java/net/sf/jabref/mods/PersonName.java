/*
 * Created on Oct 25, 2004
 * Updated on May 03, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.sf.jabref.mods;

import java.util.Vector;
import wsi.ra.tool.WSITools;


import net.sf.jabref.AuthorList;

/**
 * @author Michael Wrighton, S M Mahbub Murshed
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * 
 * S M Mahbub Murshed : added few functions for convenience. May 15, 2007
 */
public class PersonName {
    protected String givenName = null;
    protected String surname = null;
    protected String middleName = null;

    public PersonName() {
    }

    public PersonName(String name) {
        parseName(name);
    }

    public PersonName(String firstName, String _middleName, String lastName) {
        givenName = firstName;
        middleName = _middleName;
        surname = lastName;
    }

    protected void parseName(String author) {
            // TODO: replace special characters
            Vector v = new Vector();
            String authorMod = AuthorList.fixAuthor_firstNameFirst(author);

            WSITools.tokenize(v, authorMod, " \n\r");

            if (v.size() == 1)
                surname = (String) v.get(0);
            else if (v.size() == 2) {
                givenName = (String) v.get(0);
                surname = (String) v.get(1);
            }
            else {
                givenName = (String) v.get(0);
                middleName = (String) v.get(1);
                surname = (String) v.get(2);
            }
    }

    public String getGivenNames() {
        String result = "";
        if (givenName != null)
            result += givenName;
        if (middleName != null)
            result += " " + middleName;
        return result;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String lastName)
    {
        surname = lastName;
    }

    public String getFirstname()
    {
        return givenName;
    }

    public void setFirstname(String firstName)
    {
        givenName = firstName;
    }

    public String getMiddlename()
    {
        return middleName;
    }

    public void setMiddlename(String _middleName)
    {
        middleName = _middleName;
    }

    public String getFullname()
    {
    	String fullName = "";
    	if(givenName != null && givenName != "")
    		fullName += givenName + " "; 
    	if(middleName != null && middleName != "")
    		fullName += middleName + " ";
    	if(surname != null && surname != "")
    		fullName += surname;
    	
    	return fullName.trim();
    }

    public String toString() {
        return surname;
    }
}
