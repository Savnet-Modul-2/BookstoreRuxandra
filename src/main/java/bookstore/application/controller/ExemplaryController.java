package bookstore.application.controller;

import bookstore.application.dto.ExemplaryDto;
import bookstore.application.entity.Exemplary;
import bookstore.application.mapper.ExemplaryMapper;
import bookstore.application.service.ExemplaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getAll(@PathVariable Long bookId) {
        List<Exemplary> exemplars = exemplaryService.getAll(bookId);
        return ResponseEntity.ok(exemplars.stream()
                .map(ExemplaryMapper.mapExemplaryToExemplaryDto));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> delete(@PathVariable Long bookId) {
        exemplaryService.delete(bookId);
        return ResponseEntity.ok().build();
    }
}
