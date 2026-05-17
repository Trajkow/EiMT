package mk.ukim.finki.backend.service.domain;

import mk.ukim.finki.backend.model.enitites.ActivityLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActivityLogService {
    ActivityLog save(ActivityLog activityLog);
    Page<ActivityLog> findAll(Pageable pageable);
}
