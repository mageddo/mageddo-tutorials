This is a very simple example of how to create a REST api using spring and saving / getting 
information from a relational database, in this case HSQLDB was utilized.

### Running the app

```
$ ./gradlew run
2018-11-18 23:46:02.936  INFO 16534 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
```

## API DOCS

### Listing customers

```bash
$ curl -X GET -s -w '\n' localhost:8080/api/customers | jq '.'
[
  {
    "id": 1,
    "name": "Elvis",
    "age": 23,
    "balance": 1024.39
  },
  {
    "id": 4,
    "name": "Bruna",
    "age": 23,
    "balance": 590.39
  }
]
```

### Creating customer
```bash
$ curl -X POST -H 'Content-Type: application/json' -s -w '\n' localhost:8080/api/customers -d '{"name": "Elvis", "age": 23, "balance": 590.39}' | jq '.'
{
  "id": 1
}
```

### Updating customer
```bash
$ curl -X PUT -H 'Content-Type: application/json' -s -w '\n' localhost:8080/api/customers/1 -d '{"name": "Elvis", "age": 23, "balance": 1024.39}' | jq '.'
```

### Deleting customer

```bash
$ curl -X DELETE -H 'Content-Type: application/json' -s -w '\n' localhost:8080/api/customers/1 
```