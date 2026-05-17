package mk.ukim.finki.backend.service.domain.Impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.backend.model.enitites.ActivityLog;
import mk.ukim.finki.backend.repository.ActivityLogRepository;
import mk.ukim.finki.backend.service.domain.ActivityLogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ActivityLogServiceImpl implements ActivityLogService {

    private final ActivityLogRepository activityLogRepository;

    @Override
    public ActivityLog save(ActivityLog activityLog) {
        return activityLogRepository.save(activityLog);
    }

    @Override
    public Page<ActivityLog> findAll(Pageable pageable) {
        return activityLogRepository.findAll(pageable);
    }
}
