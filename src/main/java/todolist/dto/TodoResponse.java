package todolist.dto;

import todolist.entity.TodoEntity;

public class TodoResponse {
    private Long id;
    private String title;

    public TodoResponse(TodoEntity todoEntity) {
        this.id = todoEntity.getId();
        this.title = todoEntity.getTitle();
    }
}
