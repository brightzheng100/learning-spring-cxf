learning-spring-cxf
===================

A consolidated sample webservice project by integrating Spring and Apache CXF


Briefing
===============
Apache CXF 2.7.x has provided a series of samples to illustrate its features.

But it takes time to learn and combine all those features so that we can provide below frequently required features:
1. Support for Generic-based web service design;
2. Support for Class extension-based web service design;
3. Support for custom SOAP header for simple authentication instead of heavy WS-*;
4. Etc.


Environment
===============
JDK 1.6
Maven 3.2.1 with jetty-maven-plugin
Spring 3.0.6.RELEASE
Apache CXF 2.7.10


Testing
===============
This sample project provides full test cases which can cover all required scenarios for provided web services

1. mvn jetty:run
To start the server

2. run respective test class
 - SayHiWebServiceTest
 - PersonWebServiceWithGenericTest
 - PersonWebServiceWithBaseClassTest

 
FEEDBACK?
===============
1) Please fork it or;

2) Talk directly to Bright Zheng (bright.zheng AT outlook DOT com) 
  
