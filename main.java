/*
 	
# Arabic light stemmer

A command line version of the Arabic light stemmer, which is implemented in Apache lucene https://lucene.apache.org 

Light stemming for Arabic words is to remove common affix (prefix and suffix) from words, but it does not convert words into their root form.  

Version 1.0

Author: Motaz Saad (motaz dot saad at gmail dot com)

This software is a modification of the Arabic light stemmer. The original implementation is available at https://lucene.apache.org/


Arabic light stemming algorithm is described in: 

Larkey, Leah S., Lisa Ballesteros, and Margaret E. Connell. "Light stemming for Arabic information retrieval." Arabic computational morphology. Springer Netherlands, 2007. 221-243.‏
 	
 */


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

import myfileutil.myFileHandler;

import org.apache.lucene.analysis.ar.ArabicNormalizer;
import org.apache.lucene.analysis.ar.ArabicStemmer;


/**
 *
 * @author Motaz
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException {
	// check command-line argument
	if(args.length == 0) {
		System.out.println("Usage: java main <input file> <output file>");
		System.exit(0);
	}

	// file handler for I/O
	myfileutil.myFileHandler fr = new myFileHandler();

	// load stopwords
	String stopwords = fr.readEntilerFile("stopwords.txt");
	List<String> stopwordsList = Arrays.asList(stopwords.split("\n"));
			
	String fin = args[0];	// input file 
	String fout = args[1];	// output file 
			
	String s = fr.readEntilerFile(fin);	// read input file
	
	String[] lines = s.split("\n");
	StringBuilder sbuf = new StringBuilder();
	for (int i = 0; i < lines.length; i++) {
	    String[] tokens = lines[i].split("\\s");
	    for (int j = 0; j < tokens.length; j++) {
	        String t = tokens[j];
		if (! stopwordsList.contains(t)) {	// ignore stopwords 
			String resut = lightStem(t);	// Light stemming algorithm 
			sbuf.append(resut).append(" ");
		}

	    }
	    sbuf.append("\n");
	}
	
	fr.writeFileUTF(sbuf.toString(),fout);	// write results to the output file
                
    }
    
    private static String lightStem(String string) {
        ArabicNormalizer arabicNorm = new ArabicNormalizer();
        char[] c = string.toCharArray();
        int len = c.length;
        len = arabicNorm.normalize(c, len);
        char[] normalizedWord = new char[len];
        System.arraycopy(c, 0, normalizedWord, 0, len);



        ArabicStemmer araLightStemmer = new ArabicStemmer();
        len = araLightStemmer.stem(normalizedWord, len);
        char[] lightWord = new char[len];
        System.arraycopy(normalizedWord, 0, lightWord, 0, len);
       

        StringBuilder sbuf = new StringBuilder();
        sbuf.append(lightWord);


        String result = sbuf.toString();
        return result;
    }
    
}
