package com.example.vitaliy.saxparser;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Vitaliy on 9/3/2017.
 */

public class XMLHelper extends DefaultHandler{
    private String URL_MAIN ="";
    String TAG = "XMLHandler";


    Boolean currTag = false;
    String currTagVal = "";
    private PlaceMarkValue placeMarkValue = null;
    private ArrayList<PlaceMarkValue> placeMarkValues = new ArrayList<PlaceMarkValue>();

    public ArrayList<PlaceMarkValue> getPlaceMarkValues(){
        return this.placeMarkValues;
    }

    public void get() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser mSaxParser = factory.newSAXParser();
            XMLReader mXmlReader = mSaxParser.getXMLReader();
            mXmlReader.setContentHandler(this);
            InputStream mInputStream = new URL(URL_MAIN).openStream();
            mXmlReader.parse(new InputSource(mInputStream));
        } catch (Exception e) {
            // Exceptions can be handled for different types
            // But, this is about XML Parsing not about Exception Handling
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if (currTag) {
            currTagVal = currTagVal + new String(ch, start, length);
            currTag = false;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        currTag = false;

        if (localName.equalsIgnoreCase("name"))
            placeMarkValue.setName(currTagVal);

        else if (localName.equalsIgnoreCase("description"))
            placeMarkValue.setDescriptrion(currTagVal);

        else if (localName.equalsIgnoreCase("point"))
            placeMarkValue.setPoint(currTagVal);

        else if (localName.equalsIgnoreCase("post"))
            placeMarkValues.add(placeMarkValue);
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        Log.i(TAG, "TAG: " + localName);

        currTag = true;
        currTagVal = "";
        // Whenever <post> element is encountered it will create new object of PostValue
        if (localName.equals("Placemark"))
            placeMarkValue = new PlaceMarkValue();
    }

}