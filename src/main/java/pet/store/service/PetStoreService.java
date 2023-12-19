package pet.store.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pet.store.controller.model.PetStoreData;
import pet.store.dao.PetStoreDao;
import pet.store.entity.PetStore;

@Service
public class PetStoreService {

    private final PetStoreDao petStoreDao;

    @Autowired
    public PetStoreService(PetStoreDao petStoreDao) {
        this.petStoreDao = petStoreDao;
    }

    public PetStoreData savePetStore(PetStoreData petStoreData) {
        Long petStoreId = petStoreData.getPetStoreId();
        PetStore petStore = findOrCreatePetStore(petStoreId);
        copyPetStoreFields(petStore, petStoreData);
        PetStore savedStore = petStoreDao.save(petStore);
        return new PetStoreData(savedStore);
    }

    public PetStore findOrCreatePetStore(Long petStoreId) {
        if (petStoreId == null) {
            PetStore newPetStore = new PetStore();
            return newPetStore;
        } else {
            Optional<PetStore> existingPetStoreOptional = petStoreDao.findById(petStoreId);
            
            if (existingPetStoreOptional.isPresent()) {
                return existingPetStoreOptional.get();
            } else {
                throw new NoSuchElementException("PetStore with ID " + petStoreId + " not found.");
            }
        }
    }

    public void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
        petStore.setPetStoreId(petStoreData.getPetStoreId());
        petStore.setPetStoreName(petStoreData.getPetStoreName());
        petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
        petStore.setPetStoreCity(petStoreData.getPetStoreCity());
        petStore.setPetStoreState(petStoreData.getPetStoreState());
        petStore.setPetStoreZip(petStoreData.getPetStoreZip());
        petStore.setPetStorePhone(petStoreData.getPetStorePhone());
    }
}
