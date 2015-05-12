hamcrest-jackson
==============
The Hamcrest Jackson library provides a set of matchers for Json node elements.

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

###Example Usage
Here we provide a code sample to give you a rough idea how easy to use this library is.
```
JsonNode queryNode = mapper.readTree(someQueryBuilder.toString());
assertThat(queryNode,
    hasJsonField("function_score",
        hasJsonField("query", isJsonText("apple annual figures")),
        hasJsonField("fields",isJsonArray("title", "content"))
    )
);
```

###Matchers
The following matchers are provided: 
  - **HasJsonField** Matcher for checking if an object has a JSON field.
  - HasJsonField
  - **IsJsonArray** Matcher for checking if an object is a JSON array.
  - **IsJsonBoolean** Matcher for checking if an object is a JSON boolean.
  - **IsJsonInt** Matcher for checking if an object is a JSON integer.
  - **IsJsonText** Matcher for checking if an object is a JSON integer. 
 
