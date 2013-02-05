## Example project

This project uses

* [Maven] (http://maven.apache.org/)
* [Grizzly] (http://grizzly.java.net/)
* [Jersey] (http://jersey.java.net/)
* [JAXB (Java Architecture for XML Binding)](http://www.oracle.com/technetwork/articles/javase/index-140168.html)
* [JSON] (http://www.ietf.org/rfc/rfc4627.txt)

## Howto

### Get the sources

`git clone git@github.com:boldt/KoSSE-Workshop-Fileserver.git`

#### Eclipse

Import as Maven project with [Maven Integration (m2e)](http://eclipse.org/m2e/).

Run the `GrizzlyServer`:

* The one and only argument is the port, which is 9999 by default.

#### One-jar standalone

Go to the project folder and run `mvn clean package`.
The file `target/KoSSE-Workshop-1.0.jar` will be created

Run Grizzly server on port 5000:

  * `java -jar target/KoSSE-Workshop-1.0.jar 5000`

### Test:

  * `curl -X GET curl -X GET http://localhost:5000/files`

