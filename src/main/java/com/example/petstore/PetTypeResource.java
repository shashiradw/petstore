package com.example.petstore;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/v1/pettypes")
@Produces("application/json")
public class PetTypeResource {

    //return all Pet types =========================================================
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "All Pet Types", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))) })
    @GET
    public Response getPetTypes(){
        List<PetType> pettypes=PetType.listAll();
        return Response.ok(pettypes).build();
    }


    //return Pet type by ID =========================================================
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet type for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))),
            @APIResponse(responseCode = "404", description = "No Pet type found for the id.") })
    @GET
    @Path("{id}")
    public Response getPetType(@PathParam("id") int id) {

        PetType petType=PetType.findById(id);
        if(petType == null){
            return Response.ok("Pet type ID invalid").build();
        }else {
            return Response.ok(petType).build();
        }
    }

    //Add new pet type =========================================================
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Pet type added", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))),
            @APIResponse(responseCode = "204", description = "Pet type adding failed.") })
    @POST
    @Path("addpettype")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addPetType(@RequestBody PetType petType) {
        PetType.persist(petType);
        return Response.ok(petType).status(201).build();
    }

    //Delete Pet type by ID =========================================================
    @APIResponses(value = {
            @APIResponse(responseCode = "202", description = "Pet type deleted successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))),
            @APIResponse(responseCode = "204", description = "Pet type id not exist.") })
    @DELETE
    @Transactional
    @Path("delete/{typeId}")
    public Response deletePetType(@PathParam("typeId") int id) {

        System.out.println("Deleting...");

        Boolean isDeleted=PetType.deleteById(id);

        if(isDeleted){
            return Response.ok("Pet Type deleted successfully").status(202).build();
        }else {
            return Response.ok("PetType id not exist").status(204).build();
        }
    }

    //Update pet =======================================
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet type Updated", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))),
            @APIResponse(responseCode = "204", description = "Pet type not exist.") })
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("update")
    public Response updatePet(@RequestBody PetType petType) {

        int n=PetType.update("petType= ?1 where id = ?2",petType.getPetType(),petType.getId());

        if(n == 0){
            return Response.ok("Pet ID invalid").status(204).build();
        }else {
            PetType pt=PetType.findById(petType.getId());
            return Response.ok(pt).build();
        }

    }


}
