package nd.ermakov.springboot.repository;

import nd.ermakov.springboot.model.Audit;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuditRepository {

    private List<Audit> storage = new ArrayList<>();

    public void save(Audit audit) {
        storage.add(audit);
    }

    public List<Audit> findAll() {
        return storage;
    }
}
