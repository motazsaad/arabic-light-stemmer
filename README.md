# Arabic light stemmer


A command line version of the Arabic light stemmer, which is implemented in Apache lucene https://lucene.apache.org 

Light stemming for Arabic words is to remove common affix (prefix and suffix) from words, but it does not convert words into their root form.  

Version 1.0

Author: Motaz Saad (motaz dot saad at gmail dot com)

This software is a modification of the Arabic light stemmer. The original implementation is available at https://lucene.apache.org/


Arabic light stemming algorithm is described in: 

Larkey, Leah S., Lisa Ballesteros, and Margaret E. Connell. "Light stemming for Arabic information retrieval." Arabic computational morphology. Springer Netherlands, 2007. 221-243.‏



## Usage example
```
java main test-in.txt test-out.txt
```

