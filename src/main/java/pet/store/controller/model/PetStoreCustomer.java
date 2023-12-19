package pet.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Customer;

@Data
@NoArgsConstructor
public class PetStoreCustomer {
    private Long customerId;
    private String customerLastName;
    private String customerEmail;

    public PetStoreCustomer(Customer customer) {
        this.customerId = customer.getCustomerId();
        this.customerLastName = customer.getCustomerLastName();
        this.customerEmail = customer.getCustomerEmail();
    }

    public PetStoreCustomer(Long customerId, String customerLastName, String customerEmail) {
        this.customerId = customerId;
        this.customerLastName = customerLastName;
        this.customerEmail = customerEmail;
    }
}
