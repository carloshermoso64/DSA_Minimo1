package edu.upc.dsa.services;

import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.Usuario;
import edu.upc.dsa.utils.GameManager;
import edu.upc.dsa.utils.GameManagerImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/user", description = "Endpoint to Students Service")
@Path("/")
public class GameService{

    private GameManager gm;

    public GameService(){
        this.gm = GameManagerImp.getInstance();
        if(this.gm.getNumUsuario() == 0){

            this.gm.addUsuario("Carlos", "Hermoso", "seko");
            this.gm.addUsuario("Anas","Amlou","3azzy");
            this.gm.addUsuario("Juninho", "Penambucano", "ORey");

            this.gm.addObjetoUsuario("seko","coche", "rojo");
            this.gm.addObjetoUsuario("seko","moto", "amarillo");
            this.gm.addObjetoUsuario("3azzy", "avion", "verde");
        }
    }

    @GET //GET all users sorted by lastname (alphabetically)
    @ApiOperation(value = "get all users", notes = "Returns list of all users sorted by lastname")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer="List"),
    })
    @Path("/Usuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(){

        List<Usuario> allUsuarios = this.gm.getUsuarios();

        GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(allUsuarios) {};
        return Response.status(201).entity(entity).build() ;
    }

    @GET // GET a specific user that has an id
    @ApiOperation(value = "get user by id", notes = "We get a user based of a id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class),
            @ApiResponse(code = 404, message = "Student not found")
    })
    @Path("/Usuario/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id) {
        Usuario u = this.gm.getUsuario(id);
        if (u == null) {
            return Response.status(404).build();
        }
        else return Response.status(201).entity(u).build();
    }

    @POST
    @ApiOperation(value = "add new user", notes = "Adding new user to database")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Usuario.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/Usuario/{id}/{name}/{lastname}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newStudent(@PathParam("id") String id, @PathParam("name") String name, @PathParam("lastname") String lastname) {

        if(id == null || name == null || lastname == null){
            return Response.status(500).build();
        }
        this.gm.addUsuario(name,lastname,id);
        Usuario u = this.gm.getUsuario(id);
        return Response.status(201).entity(u).build();
    }

    @GET
    @ApiOperation(value = "get objects that a user has", notes = "Getting objects from user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Objeto.class),
            @ApiResponse(code = 404, message = "Usuario not found")
    })
    @Path("/Usuario/{id}/Objects")
    public Response getObjectsUser(@PathParam("id") String id){
        Usuario u = this.gm.getUsuario(id);
        if(u == null) return Response.status(404).build();
        else{
            List<Objeto> objs = u.getObjects();
            GenericEntity<List<Objeto>> entity = new GenericEntity<List<Objeto>>(objs) {};
            return Response.status(201).entity(entity).build() ;
        }
    }

    @PUT
    @ApiOperation(value = "add object to a user", notes = "Object add")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful",response = Objeto.class),
            @ApiResponse(code = 404, message = "Subject/Student not found")
    })
    @Path("/Usuario/{id}")
    public Response enrollStudent(@PathParam("id") String id, Objeto obj) {

        Usuario u = this.gm.getUsuario(id);

        if (u == null) return Response.status(404).build();
        else {
            this.gm.addObjetoUsuario(id,obj.getId(),obj.getDescrp());
            return Response.status(201).entity(u).build();
        }
    }

    @PUT
    @ApiOperation(value = "update a user based on a ID", notes = "user updated (id cannot change) IMPORTANT")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful",response = Usuario.class),
            @ApiResponse(code = 404, message = "Usuario not found")
    })
    @Path("/Usuario")
    public Response updateUser(Usuario u) {

        Usuario us = this.gm.getUsuario(u.getId());
        if (us == null || u == null) return Response.status(404).build();
        else {
            this.gm.updateUsuario(u.getName(),u.getLastname(),u.getMail(),u.getId());
            return Response.status(201).entity(us).build();
        }
    }
}
