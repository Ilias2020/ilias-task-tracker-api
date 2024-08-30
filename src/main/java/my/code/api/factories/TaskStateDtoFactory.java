package my.code.api.factories;

import my.code.api.dto.TaskStateDto;
import my.code.store.entities.TaskStateEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskStateDtoFactory {

    public TaskStateDto makeTaskStateDto(TaskStateEntity entity) {

        return TaskStateDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .ordinal(entity.getOrdinal())
                .build();
    }
}
