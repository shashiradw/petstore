package com.example.petstore.services;

import com.example.petstore.PetType;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class PetTypeService {
    public PetTypeService() {
    }

    @Transactional
    public PetType addPetType(PetType petType) {
        if (!petType.isPersistent()) {
            petType.persist();
        }

        petType.persistAndFlush();
        return petType;
    }
}
