package com.spidercrawl.crawler;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Spider {
	  private static final int MAX_PAGES_TO_SEARCH = 55;
	  private Set<String> pagesVisited = new HashSet<String>();
	  private List<String> pagesToVisit = new LinkedList<String>();
	  private int NUMBER_OF_FAILED_ATTEMPTS = 0;
	  
	  
	  /**
	   * Our main launching point for the Spider's functionality. Internally it creates spider legs
	   * that make an HTTP request and parse the response (the web page).
	   * 
	   * @param url
	   *            - The starting point of the spider
	   */

	  public void search(String url)
	  {
	      while(this.pagesVisited.size() < MAX_PAGES_TO_SEARCH)
	      {
	          String currentUrl = null;
	          
	          SpiderLeg leg = new SpiderLeg();
	          if(this.pagesToVisit.isEmpty())
	          {
	              currentUrl = (String) url;
	              this.pagesVisited.add((String)url);
	          }
	          else
	          {
	              currentUrl = this.nextUrl();
	          }
	          boolean success = leg.crawl(currentUrl); // Lots of stuff happening here. Look at the crawl method in
	                                                   // SpiderLeg
          
	          if(success)
	          {
	              System.out.println(String.format("**Success** found %s",currentUrl));
	              this.pagesToVisit.addAll(leg.getLinks());
	              break;
	          }
	          else{
	        	  System.out.println(String.format("**Failure** found %s",currentUrl));
	        	  NUMBER_OF_FAILED_ATTEMPTS++;
	        	  break;
	          }
	      }	      
	      System.out.println("\n**Done** Visited " + this.pagesVisited.size() + " web page(s)");
	      System.out.println("\n*** Number of failed attempts: " + Integer.toString(NUMBER_OF_FAILED_ATTEMPTS));
	  }


	  /**
	   * Returns the next URL to visit (in the order that they were found). We also do a check to make
	   * sure this method doesn't return a URL that has already been visited.
	   * 
	   * @return
	   */
	  private String nextUrl()
	  {
	      String nextUrl = null;;
	      
	      do
	      {
	    	  if (this.pagesToVisit.size() > 0){
	           nextUrl = this.pagesToVisit.remove(0);
	    	  }
	      } while(this.pagesVisited.contains(nextUrl));
	      	      
	      this.pagesVisited.add(nextUrl);	          
	      return nextUrl;
	  }
}
