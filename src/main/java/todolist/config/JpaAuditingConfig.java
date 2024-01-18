package todolist.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 시간 데이터(생성, 수정 시간)을 자동으로 등록하기 위해서 필요하다.
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {
}
