package edu.miu.cs.cs425.fairfieldlibraryapp.repository;

import edu.miu.cs.cs425.fairfieldlibraryapp.model.Publisher;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
/**
 * This interface definition relies on the public abstract methods
 * inherited from the super interface, JpaRepository<T, ID>
 * We may override any or add more methods here, as needed.
 */
// @Repository
public interface PublisherRepository 
                        extends JpaRepository<Publisher, Integer> {

    /* Search Queries */
    /*
        SELECT p.*, a.* FROM fairfieldlibrarydb2.publishers p
        left outer join fairfieldlibrarydb2.addresses a 
            on p.primary_addr_id = a.addr_id
        where p.name like '%pub%' or a.street like '%venue%'
        order by p.name;
    */
    //List<Publisher> findAllPublishersMatchingInputText(String searchString);
    List<Publisher> findByNameContaining(String name);
    List<Publisher> findAllByNameContainingOrPrimaryAddress_StreetContainingOrPrimaryAddress_CityContainingOrPrimaryAddress_StateContainingOrPrimaryAddress_ZipCodeContainingOrderByName
            (
                String name, String street,
                String city, String state, String zipCode
            );
    
}
