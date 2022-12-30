package com.example.bt3;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class XmlPullParserHandler {
    private List<Item> items = new ArrayList<Item>();
    private Item item;
    private String text;
    private boolean firstTitle = true;
    private boolean firstDescription = true;
    private boolean firstLink = true;
    private boolean isMedia = false;

    public List<Item> getItems(){
        return items;
    }

    public List<Item> parse(String input){
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();

            parser.setInput(new StringReader(input));
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT){
                String tagname = parser.getName() == null ? "" : parser.getName();
                String prefix = parser.getPrefix() == null ? "" : parser.getPrefix();
                if(tagname.equalsIgnoreCase("title") && prefix.equalsIgnoreCase("media") && eventType == XmlPullParser.START_TAG)
                    isMedia = true;
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        if(tagname.equalsIgnoreCase("item")){
                            item = new Item();
                        } else if (tagname.equalsIgnoreCase("content")){
                            item.setImgURL(parser.getAttributeValue(0));
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if(tagname.equalsIgnoreCase("item"))
                            items.add(item);
                        else if(tagname.equalsIgnoreCase("title")){
                            if(!firstTitle)
                                if(isMedia){
                                    text.replace(".jpg", "");
                                    item.setImgName(text);
                                    isMedia = false;
                                }
                                else
                                    item.setTitle(text);
                            firstTitle = false;
                        }
                        else if (tagname.equalsIgnoreCase("description")){
                            if(!firstDescription)
                                item.setDescription(text);
                            firstDescription = false;
                        }
                        else if (tagname.equalsIgnoreCase("pubDate"))
                            item.setDate(text);
                        else if (tagname.equalsIgnoreCase("link")){
                            if(!firstLink)
                                item.setLink(text);
                            firstLink = false;
                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return items;
    }
}
