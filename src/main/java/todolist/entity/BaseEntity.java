package todolist.entity;

import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// 시간 데이터 (JPA Auditing)
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    private LocalDateTime createdAt;    // 생성일시

    private LocalDateTime modifiedAt;   // 수정일시

}