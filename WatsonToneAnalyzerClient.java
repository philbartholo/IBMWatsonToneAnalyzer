package com.intuit.ebs.watson;

import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneCategory;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneScore;

import java.util.Iterator;

/**
 * @author pbartholo
 
 This is a small code sample containing a call to IBM Bluemix Watson Tone Analyzer to get the tone of 
 any text string. In order to get this to work, you must sign up for an IBM Bluemix ID and replace your 
 username and password in the code. You must also download the Watson Developer SDK.

You can register for an ID at: https://developer.ibm.com/sso/register

The SDK with the client classes you'll need are at: https://github.com/watson-developer-cloud/java-sdk
 
 */
public class WatsonToneAnalyzerClient {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		getWatsonTone("I thought chess was easy.");
	}
		
	
	/**
	 * @author pbartholo
	 *
	 * This method accepts any string, and calls the IBM Watson Tone analyzer to return
	 * whether the string contains more anger, disgust, fear, sadness, or joy.
	 */
	public static void getWatsonTone( String text )
	{
			// These credentials authenticate to the IBM Watson BlueMix Tone Analyzer
		    ToneAnalyzer service = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_05_19);
		    service.setUsernameAndPassword("YOUR USERNAME HERE", "YOUR PASSWORD HERE");

		    // Call the service and get the tone
		    ToneAnalysis tone = service.getTone(text, null).execute();
		    Iterator<ToneCategory> it = tone.getDocumentTone().getTones().iterator();

		    // Loop through all the Tone Categories
		    while ( it.hasNext() )
		    {
		    	ToneCategory tc = it.next();
		    	Iterator<ToneScore> tsi = tc.getTones().iterator();
		    	//Loop through the specific scores of each tone
		    	while ( tsi.hasNext() )
		    	{
		    		ToneScore ts = tsi.next();
		    		String n = ts.getName();
		    		double s = ts.getScore();

		    		System.out.println(n+":"+s);
		    	}
		    	
		    }
		  }	
	}
