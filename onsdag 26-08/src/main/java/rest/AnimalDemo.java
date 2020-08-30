
package rest;

import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import model.AnimalNoDB;


@Path("animals")
public class AnimalDemo {

    @Context
    private UriInfo context;
    

    /**
     * Creates a new instance of AnimalDemo
     */
    public AnimalDemo() {
    }

    /**
     * Retrieves representation of an instance of rest.AnimalDemo
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getJson() {
        return "Vuf… (Message from a dog)";
    }
    @GET
    @Path("/animal_list")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson1(){
        return  "[\"Dog\", \"Cat\", \"Mouse\", \"Bird\"]";
    }
    @GET
    @Path("/animal")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson2(){
        AnimalNoDB animal = new AnimalNoDB("Dog", "Bark");
        return new Gson().toJson(animal);
    }

    /**
     * PUT method for updating or creating an instance of AnimalDemo
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
