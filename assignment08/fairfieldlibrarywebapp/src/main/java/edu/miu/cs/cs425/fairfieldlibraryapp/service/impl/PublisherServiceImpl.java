package edu.miu.cs.cs425.fairfieldlibraryapp.service.impl;

import edu.miu.cs.cs425.fairfieldlibraryapp.model.Publisher;
import edu.miu.cs.cs425.fairfieldlibraryapp.repository.AddressRepository;
import edu.miu.cs.cs425.fairfieldlibraryapp.repository.PublisherRepository;
import edu.miu.cs.cs425.fairfieldlibraryapp.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {
    private PublisherRepository publisherRepository;
    @Autowired
    private AddressRepository addressRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override
    public Page<Publisher> getAllPublishersPaged(int pageNo) {
        return publisherRepository.findAll(
            PageRequest.of(pageNo,
                3,
                Sort.by(Sort.Direction.ASC, "name"))
        );
    }

    @Override
    public Publisher savePublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    public Publisher getPublisherById(Integer publisherId) {
        return publisherRepository.findById(publisherId).orElse(null);
    }

    @Override
    public Publisher updatePublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    public void deletePublisherById(Integer publisherId) {
        publisherRepository.deleteById(publisherId);
    }

    @Override
    public void deletePrimaryAddressOfPublisher(Integer publisherId) {
        var publisher = publisherRepository.findById(publisherId).orElse(null);
        if(publisher != null) {
            var address = publisher.getPrimaryAddress();
            Integer addressId = null;
            if(address != null) {
                addressId = address.getAddrId();
            }
            publisher.setPrimaryAddress(null);
            publisherRepository.save(publisher);
            if(addressId != null) {
                addressRepository.deleteById(addressId);
            }
        }
    }

    @Override
    public List<Publisher> searchPublishers(String searchString) {
//        return publisherRepository.findByNameContaining(searchString);
        return publisherRepository.findAllByNameContainingOrPrimaryAddress_StreetContainingOrPrimaryAddress_CityContainingOrPrimaryAddress_StateContainingOrPrimaryAddress_ZipCodeContainingOrderByName
                (
                    searchString, searchString,
                        searchString, searchString, searchString
                );
//                .stream()
//                .sorted(Comparator.comparing(Publisher::getName))
//                .toList();
    }
    
}
