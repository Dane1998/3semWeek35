package rest;

import com.google.gson.Gson;
import entity.Animal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("animals_db")
public class AnimalFromDB {

    @Context
    private UriInfo context;

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    /**
     * Creates a new instance of AnimalsFromDB
     */
    public AnimalFromDB() {
    }

    @Path("animals")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimals() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
            List<Animal> animals = query.getResultList();
            return new Gson().toJson(animals);
        } finally {
            em.close();
        }
    }

    @Path("animalbyid/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimal(@PathParam("id") int id) {
        //Hvis den kaldes med .../animalbyid/2  vil id nu være lig 2.
        //Den værdi kan I så benytte til at slå op i databasen med em.find(
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a WHERE a.id = :id", Animal.class );
            query.setParameter("id", id);
            Animal animals = (Animal) query.getSingleResult();
            return new Gson().toJson(animals);
        } finally {
            em.close();
        }

    }
    
    @Path("animalbytype/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimal(@PathParam("type") String type) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a WHERE a.type = :type", Animal.class );
            query.setParameter("type", type);
            Animal animals = (Animal) query.getSingleResult();
            return new Gson().toJson(animals);
        } finally {
            em.close();
        }
    }
    
    @Path("random_animal")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimal() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a.type FROM Animal a ORDER BY RAND() LIMIT 1", Animal.class );
            Animal animals = (Animal) query.getSingleResult();
            return new Gson().toJson(animals);
        } finally {
            em.close();
        }
    }

    /**
     * PUT method for updating or creating an instance of AnimalsFromDB
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
