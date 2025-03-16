package bookstore.application.controller;

import bookstore.application.dto.ExemplaryDto;
import bookstore.application.entity.Exemplary;
import bookstore.application.mapper.ExemplaryMapper;
import bookstore.application.service.ExemplaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/exemplars")
public class ExemplaryController {
    @Autowired
    private ExemplaryService exemplaryService;

    @PostMapping("/{bookId}/{exemplarsNumber}")
    public ResponseEntity<?> create(
            @RequestBody ExemplaryDto exemplaryDto,
            @PathVariable Long bookId,
            @PathVariable Integer exemplarsNumber) {
        Exemplary exemplary = ExemplaryMapper.mapExemplaryDtoToExemplary.apply(exemplaryDto);
        List<Exemplary> createdExemplars = exemplaryService.create(exemplary, bookId, exemplarsNumber);
        List<ExemplaryDto> createdExemplarsDto = createdExemplars.stream()
                .map(ExemplaryMapper.mapExemplaryToExemplaryDto)
                .toList();
        return ResponseEntity.ok(createdExemplarsDto);
    }

    @GetMapping("/all/{bookId}")
    public ResponseEntity<?> getAll(@PathVariable Long bookId) {
        List<Exemplary> exemplars = exemplaryService.getAll(bookId);
        return ResponseEntity.ok(exemplars.stream()
                .map(ExemplaryMapper.mapExemplaryToExemplaryDto));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> findExemplary(
            @PathVariable Long bookId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        Exemplary exemplary = exemplaryService.findFirstAvailable(bookId, startDate, endDate);
        return ResponseEntity.ok(ExemplaryMapper.mapExemplaryToExemplaryDto.apply(exemplary));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> delete(@PathVariable Long bookId) {
        exemplaryService.delete(bookId);
        return ResponseEntity.ok().build();
    }
}
