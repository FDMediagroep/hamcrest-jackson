hamcrest-jackson
==============

The JavaDoc can be found at: http://files2.fd.nl/development/hamcrest-jackson/javadoc/  

###How to use hamcrest-jackson in your project:
- Clone this project 
- Build it using maven clean install 
- Add it to your dependencies using 
```
<dependency>
    <groupId>nl.fd</groupId>
      <artifactId>hamcrest-jackson</artifactId>
      <version>1.0.5-SNAPSHOT</version>
</dependency>
```
###Matchers
The following matchers are provided: 
  -**HasJsonField** Matcher for checking if an object has a JSON field.
  -**IsJsonArray<T extends JsonNode>** Matcher for checking if an object is a JSON array.
  -**IsJsonBoolean<T extends JsonNode>** Matcher for checking if an object is a JSON boolean.
  -**IsJsonInt	** Matcher for checking if an object is a JSON integer.
  -**IsJsonText<T extends JsonNode>** Matcher for checking if an object is a JSON integer. 
 
