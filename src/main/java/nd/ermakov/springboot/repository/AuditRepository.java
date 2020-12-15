package nd.ermakov.springboot.repository;

import nd.ermakov.springboot.model.Audit;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class AuditRepository {

    private Collection<Audit> storage = new ConcurrentLinkedQueue<>();

    public void save(Audit audit) {
        storage.add(audit);
    }

    public Collection<Audit> findAll() {
        return storage;
    }
}
