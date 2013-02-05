package de.dennis_boldt.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.sun.jersey.spi.container.ResourceFilters;

import de.dennis_boldt.Config;
import de.dennis_boldt.cors.CORSFilter;
import de.dennis_boldt.utils.FileExistsException;
import de.dennis_boldt.utils.FileUtil;
import de.dennis_boldt.utils.SystemUtils;

@Path("/files")
@ResourceFilters({CORSFilter.class})
public class FileRessource {

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllFiles() {

        java.io.File folder = new java.io.File(Config.FILES_PATH + SystemUtils.FILE_SEPARATOR);
        List<File> fileList = new LinkedList<File>();
        for (final java.io.File file : folder.listFiles()) {
            if (!file.isDirectory()) {
                fileList.add(new File(file));
            }
        }
        return Response.ok().entity(new GenericEntity<List<File>>(fileList) {}).build();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response postFile(
            @FormDataParam("file") InputStream inputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetails
            ) {

        // Check, if the parameter `file` is used
        if(fileDetails.getFileName().isEmpty()) {
            return Response.status(403).entity("<h1>Error 403: Forbidden</h1>No file selected!").build();
        }

        // Generate path
        String filePath = Config.FILES_PATH + SystemUtils.FILE_SEPARATOR + fileDetails.getFileName();
        java.io.File f = new java.io.File(filePath);

        try {
            FileUtil.writeToFile(inputStream, f);
        } catch (FileExistsException e) {
            // 409
            return Response.status(Status.CONFLICT).entity(new File(f)).build();
        } catch (IOException e) {
            // 500
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("<h1>500 Internal Server Error</h1>" + e.getMessage()).build();
        }

        // 201
        return Response.status(Status.CREATED).entity(new File(f)).build();
    }

}