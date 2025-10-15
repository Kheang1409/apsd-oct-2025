package edu.miu.cs.cs425.fairfieldlibraryapp.service;

import edu.miu.cs.cs425.fairfieldlibraryapp.model.Publisher;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PublisherService {

    public abstract List<Publisher> getAllPublishers();
    public abstract Page<Publisher> getAllPublishersPaged(int pageNo);
    public abstract Publisher savePublisher(Publisher publisher);
    public abstract Publisher getPublisherById(Integer publisherId);
    public abstract Publisher updatePublisher(Publisher publisher);
    public abstract void deletePublisherById(Integer publisherId);
    public abstract void deletePrimaryAddressOfPublisher(Integer publisherId);
    public abstract List<Publisher> searchPublishers(String searchString);
}
