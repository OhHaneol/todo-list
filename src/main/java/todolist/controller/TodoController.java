package todolist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todolist.dto.TodoRequest;
import todolist.dto.TodoResponse;
import todolist.service.TodoService;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    // 생성
    @PostMapping
    public ResponseEntity<?> create(@RequestBody TodoRequest request) {
        System.out.println("CREATE");
        TodoResponse response = todoService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 수정
    @PatchMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody TodoRequest request
    ) {
        System.out.println("UPDATE");
        TodoResponse response = todoService.update(id, request);

        return ResponseEntity.ok(response);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        System.out.println("DELETE");
        todoService.delete(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    // 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        System.out.println("READ ONE");
        TodoResponse response = todoService.get(id);

        return ResponseEntity.ok(response);
    }

    // 전체 조회
    @GetMapping
    public ResponseEntity<?> getAll(@PageableDefault(size = 10) Pageable pageable) {
        System.out.println("READ ALL");
        Page<TodoResponse> responses = todoService.getAll(pageable);

        return ResponseEntity.ok(responses);
    }
}
