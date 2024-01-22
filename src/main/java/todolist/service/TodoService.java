package todolist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todolist.dto.TodoRequest;
import todolist.dto.TodoResponse;
import todolist.entity.TodoEntity;
import todolist.repository.TodoRepository;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    @Transactional  // 하나의 작업 단위를 명시하기 위한 어노테이션이라는 점이 default. 하나는 성공하고 하나는 실패하는 경우 이 작업이 실패라는 뜻이다.
    public TodoResponse create(TodoRequest request) {
        TodoEntity instance = new TodoEntity(request.getTitle());
        TodoEntity todoEntity = todoRepository.save(instance);

        return new TodoResponse(todoEntity);
    }

    @Transactional
    public TodoResponse update(Long id, TodoRequest request) {
        // 업데이트 하려는 todo객체 찾기_id 값으로 조회가 되지 않으면 에러를 내보낸다.
        TodoEntity todoEntity = todoRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("잘못된 입력입니다."));
        // 업데이트 한 투두를 담기
        TodoEntity updatedTodo = todoEntity.update(request.getTitle());
        // 응답하려는 객체에 담아서 반환하기.
        return new TodoResponse(updatedTodo);
    }

    @Transactional
    public void delete(Long id) {
        todoRepository.deleteById(id);
    }

    @Transactional
    public TodoResponse get(Long id) {
        // id 값으로 조회가 되지 않으면 에러를 내보낸다.
        TodoEntity todoEntity = todoRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("잘못된 입력입니다."));

        return new TodoResponse(todoEntity);
    }

    @Transactional
    public Page<TodoResponse> getAll(Pageable pageable) {
        // pageable로 todo를 조회
        Page<TodoEntity> todoEntities = todoRepository.findAll(pageable);

        // entity를 그대로 반환하지 않고 한 번 감싼다.
        Page<TodoResponse> todoResponses = todoEntities.map(TodoResponse::new);

        return todoResponses;
    }
}
