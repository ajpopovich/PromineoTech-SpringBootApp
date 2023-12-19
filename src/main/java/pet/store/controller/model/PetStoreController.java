package pet.store.controller.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pet.store.service.PetStoreService;

@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {

    private final PetStoreService petStoreService;

    @Autowired
    public PetStoreController(PetStoreService petStoreService) {
        this.petStoreService = petStoreService;
    }

    @PostMapping
    public PetStoreData createPetStore(@RequestBody PetStoreData petStoreData) {
        log.info("Received request to create a pet store: {}", petStoreData);
        return petStoreService.savePetStore(petStoreData);
    }
    
    @PutMapping("/{storeId}")
    public PetStoreData updatePetStore(@PathVariable Long storeId, @RequestBody PetStoreData petStoreData) {
        log.info("Received request to update pet store with ID {}: {}", storeId, petStoreData);
        
        petStoreData.setPetStoreId(storeId);
        
        return petStoreService.savePetStore(petStoreData);
    }
}
