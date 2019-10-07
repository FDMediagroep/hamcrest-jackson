hamcrest-jackson
[![All Contributors](https://img.shields.io/badge/all_contributors-2-orange.svg?style=flat-square)](#contributors)
==============

The Hamcrest Jackson library provides a set of matchers for Json node elements.

### How to use hamcrest-jackson in your project:
- Clone this project 
- Build it using maven clean install 
- Add it to your dependencies using 
```
<dependency>
    <groupId>nl.fd</groupId>
      <artifactId>hamcrest-jackson</artifactId>
      <version>1.0.6-SNAPSHOT</version>
</dependency>
```

### Example Usage
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

### Matchers
The following matchers are provided: 
  - **HasJsonField** Matcher for checking if an object has a JSON field.
    - hasJsonField
  - **IsJsonArray** Matcher for checking if an object is a JSON array.
    - isJsonArray
  - **IsJsonBoolean** Matcher for checking if an object is a JSON boolean.
    - isJsonBoolean
  - **IsJsonInt** Matcher for checking if an object is a JSON integer.
    - isJsonInt
  - **IsJsonText** Matcher for checking if an object is a JSON integer. 
    - isJsonText
 

## Contributors ✨

Thanks goes to these wonderful people ([emoji key](https://allcontributors.org/docs/en/emoji-key)):

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore -->
<table>
  <tr>
    <td align="center"><a href="https://github.com/JHannema"><img src="https://avatars2.githubusercontent.com/u/5299964?v=4" width="100px;" alt="Joram Hannema"/><br /><sub><b>Joram Hannema</b></sub></a><br /><a href="https://github.com/FDMediagroep/hamcrest-jackson/commits?author=JHannema" title="Code">💻</a> <a href="https://github.com/FDMediagroep/hamcrest-jackson/commits?author=JHannema" title="Documentation">📖</a> <a href="#maintenance-JHannema" title="Maintenance">🚧</a></td>
    <td align="center"><a href="https://github.com/jmsnoeij"><img src="https://avatars3.githubusercontent.com/u/3830004?v=4" width="100px;" alt="Jeroen M Snoeij"/><br /><sub><b>Jeroen M Snoeij</b></sub></a><br /><a href="https://github.com/FDMediagroep/hamcrest-jackson/commits?author=jmsnoeij" title="Code">💻</a> <a href="https://github.com/FDMediagroep/hamcrest-jackson/commits?author=jmsnoeij" title="Documentation">📖</a> <a href="#maintenance-jmsnoeij" title="Maintenance">🚧</a></td>
  </tr>
</table>

<!-- ALL-CONTRIBUTORS-LIST:END -->

This project follows the [all-contributors](https://github.com/all-contributors/all-contributors) specification. Contributions of any kind welcome!