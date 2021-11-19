package com.example.petstore.services;

import com.example.petstore.Pet;
import com.example.petstore.PetType;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PetService {

    private PetTypeService petTypeService=new PetTypeService();

    //Add a pet to database
    @Transactional
    public void addPet(Pet pet) {

        //Check if pet type is available in the pet type table
        String type=pet.getPetType();
        PetType petType=PetType.findByType(type);

        if(petType == null){
            //If petType not exist add new petType
            petTypeService.addPetType(new PetType(0,type));
        }
         pet.persist();
    }

    //Delete a pet by ID
    @Transactional
    public boolean deletePet(int id){
        Pet pet = Pet.findById(id);

        if(pet != null){
            pet.delete();
            return true;
        }else {
            return false;
        }
    }


    public List<Pet> getAllPets(){
        return Pet.listAll();
    }

    public Pet getPetByID(int petId) {
        return Pet.findById(petId);
    }

    //Update Pet
    @Transactional
    public Pet updatePet(Pet pet) {
        //If exist then update
        int n=Pet.update("petAge= ?1 , petName= ?2 , petType= ?3 where id = ?4",pet.getPetAge(),pet.getPetName(),pet.getPetType(), pet.getId());

        if(n==0){
            return null;
        }else{
            return Pet.findById(pet.getId());
        }

    }
}
