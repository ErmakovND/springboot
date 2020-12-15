package nd.ermakov.springboot.service;

import nd.ermakov.springboot.model.Audit;
import nd.ermakov.springboot.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AuditService {

    private AuditRepository auditRepository;

    public AuditService(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    public void log(String name, String password, AuthStatus authStatus) {
        auditRepository.save(new Audit(name, password, getStringStatus(authStatus)));
    }

    public Collection<Audit> get() {
        return auditRepository.findAll();
    }

    private String getStringStatus(AuthStatus authStatus) {
        switch (authStatus) {
            case OK: return "login success";
            case WRONG_PASSWORD: return "login fail: wrong password";
            case NOT_FOUND: return "login fail: user doesn't exist";
            case ALREADY_EXISTS: return "signup fail: user already exists";
            case CREATED: return "signup success";
            default: return "unknown status";
        }
    }
}
