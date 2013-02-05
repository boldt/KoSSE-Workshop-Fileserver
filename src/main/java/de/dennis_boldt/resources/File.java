package de.dennis_boldt.resources;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import de.dennis_boldt.utils.MimeTypeUtil;
import de.dennis_boldt.utils.MimeTypeUtilException;

@XmlRootElement(name = "file")
@XmlType(propOrder = { "name", "size", "path", "type" })
public class File {

    private java.io.File file = null;

    // JAX-B
    public File() {}

    public File(java.io.File file) {
        this.file = file;
    }

    @XmlElement(name = "name")
    public String getName() {
        return file.getName();
    }

    @XmlElement(name = "size")
    public Long getSize() {
        return file.length();
    }

    @XmlElement(name = "path")
    public String getPath() {
        return file.getPath();
    }

    @XmlElement(name = "type")
    public String getType() {
        try {
            String t = MimeTypeUtil.getMimeType(file);
            return t;
        } catch (MimeTypeUtilException e) {
            // fall back mime type
            return "application/octet-stream";
        }
    }

}
