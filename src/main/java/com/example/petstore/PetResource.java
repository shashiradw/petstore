package com.example.petstore;

import java.util.List;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.example.petstore.services.PetService;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/v1/pets")
@Produces("application/json")
public class PetResource {


	private PetService petService=new PetService();

	//return all Pets =========================================================
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	public Response getPets(){
		List<Pet> pets=petService.getAllPets();
		return Response.ok(pets).build();
	}

	//return Pet by ID =========================================================
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@GET
	@Path("{petId}")
	public Response getPet(@PathParam("petId") int petId) {

		Pet pet=petService.getPetByID(petId);
		if(pet == null){
			return Response.ok("Pet ID invalid").build();
		}else {
			return Response.ok(pet).build();
		}
	}

	//Add new pet =========================================================
	@APIResponses(value = {
			@APIResponse(responseCode = "201", description = "Pet added", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "204", description = "Pet adding failed.") })
	@POST
	@Path("addpet")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response addPet(@RequestBody Pet pet) {
		petService.addPet(pet);

		return Response.ok(pet).status(201).build();
	}

	//Delete Pet by ID =========================================================
	@APIResponses(value = {
			@APIResponse(responseCode = "202", description = "Pet deleted successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "204", description = "Pet id not exist.") })
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	@Path("delete/{petId}")
	public Response deletePet(@PathParam("petId") int id) {

		boolean isExsist=petService.deletePet(id);

		if(isExsist){
			return Response.ok("Pet deleted successfully").status(202).build();
		}else {
			return Response.ok("Pet id not exist").status(204).build();
		}
	}

	//Update pet =======================================
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet Updated", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "204", description = "Pet not exist.") })
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	@Path("update")
	public Response updatePet(@RequestBody Pet pet) {

		Pet updatedPet=petService.updatePet(pet);

		if(updatedPet == null){
			return Response.ok("Pet ID invalid").status(204).build();
		}else {
			return Response.ok(updatedPet).build();
		}

	}

	/// Search for pets by name, age...etc through the API

	// Pet Search by name =========================================================
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	@Path("search/name/{petName}")
	public Response getPetByName(@PathParam("petName") String name){
		Pet pet=Pet.findByName(name);
		return Response.ok(pet).build();
	}

	// Pet Search by age =========================================================
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	@Path("search/age/{petAge}")
	public Response getPetByAge(@PathParam("petAge") int petAge){
		List<Pet> pet=Pet.findByAge(petAge);
		return Response.ok(pet).build();
	}

	// Pet Search by age =========================================================
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	@Path("search/type/{petType}")
	public Response getPetByType(@PathParam("petType") String petType){
		List<Pet> pet=Pet.findByType(petType);
		return Response.ok(pet).build();
	}




}
