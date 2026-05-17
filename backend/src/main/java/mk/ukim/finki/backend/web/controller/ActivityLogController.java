package mk.ukim.finki.backend.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import mk.ukim.finki.backend.model.enitites.ActivityLog;
import mk.ukim.finki.backend.service.domain.ActivityLogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Activity Log", description = "Audit log of accommodation rental events")
@RestController
@RequestMapping("/api/activity-log")
@AllArgsConstructor
public class ActivityLogController {

    private final ActivityLogService activityLogService;

    @Operation(summary = "Get paged activity log",
            description = "Returns a paginated list of activity log entries. Events include RENTED and FULLY_OCCUPIED.")
    @GetMapping
    public ResponseEntity<Page<ActivityLog>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "eventTime"));
        return ResponseEntity.ok(activityLogService.findAll(pageable));
    }
}
