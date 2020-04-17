package edu.upc.dsa.services;

import edu.upc.dsa.models.Brote;
import edu.upc.dsa.models.Caso;
import edu.upc.dsa.utils.Covid19Manager;
import edu.upc.dsa.utils.Covid19ManagerImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/Brote", description = "Endpoint to Brote Service")
@Path("/")
public class Covid19Service{

    private Covid19Manager gm;

    public Covid19Service(){
        this.gm = Covid19ManagerImp.getInstance();


        this.gm.addBrote("Barcelona",20200417,"4");
        this.gm.addBrote("Madrid",20200413,"2");

        this.gm.addCasoToBrote("2","Carlos","Hermoso","64",19980920,20200327,"A","M","carlos@gmail.com","680420180","Gava","R");


    }

    @GET //GET all brotes sorted by date (alphabetically)
    @ApiOperation(value = "get all brotes", notes = "Returns list of all brotes sorted by date")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Brote.class, responseContainer="List"),
    })
    @Path("/Brote")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBrotes(){

        List<Brote> allBrotes = this.gm.getBrotes();

        GenericEntity<List<Brote>> entity = new GenericEntity<List<Brote>>(allBrotes){};
        return Response.status(201).entity(entity).build() ;
    }

    @GET // GET a specific brote that has an id
    @ApiOperation(value = "get brote by id", notes = "We get a brote based of a id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Brote.class),
            @ApiResponse(code = 404, message = "Student not found")
    })
    @Path("/Brote/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBrote(@PathParam("id") String id) {
        Brote u = this.gm.getBrote(id);
        if (u == null) {
            return Response.status(404).build();
        }
        else return Response.status(201).entity(u).build();
    }

    @POST
    @ApiOperation(value = "add new brote", notes = "Adding new brote to database")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Brote.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/Brote/{id}/{ubicacion}/{fecha}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newStudent(@PathParam("id") String id, @PathParam("name") String ubicacion, @PathParam("lastname") int fecha) {

        if(id == null || ubicacion == null ||fecha == 0){
            return Response.status(500).build();
        }
        this.gm.addBrote(ubicacion,fecha,id);
        Brote u = this.gm.getBrote(id);
        return Response.status(201).entity(u).build();
    }

    @GET
    @ApiOperation(value = "get caso that a brote has", notes = "Getting casos from brote")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Caso.class),
            @ApiResponse(code = 404, message = "Brote not found")
    })
    @Path("/Brote/{id}/Casos")
    public Response getCasosBrote(@PathParam("id") String id){
        Brote u = this.gm.getBrote(id);
        if(u == null) return Response.status(404).build();
        else{
            List<Caso> objs = u.getCasos();
            GenericEntity<List<Caso>> entity = new GenericEntity<List<Caso>>(objs) {};
            return Response.status(201).entity(entity).build() ;
        }
    }


}
