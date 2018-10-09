package com.spidercrawl.crawler;

import java.util.*;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class SpiderTest {
    /**
     * This is our test. It creates a spider (which creates spider legs) and crawls the web.
     * 
     * @param args
     *            - not used
     */
    public static void main(String[] args)
    {       
        File inFile = new File("src/com/spidercrawl/crawler/linkData.json");
        
        Spider spider = new Spider();
        
        JSONParser parser = new JSONParser();
        
        try {

            Object obj = parser.parse(new FileReader(inFile));
            JSONObject jsonObject = (JSONObject) obj;
 
            // loop array for json array links
            JSONArray linksArray = (JSONArray) jsonObject.get("links");
            Iterator<String> iterator = linksArray.iterator();
            
            while (iterator.hasNext()) {
                spider.search(iterator.next());           
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }                
    } 
}
