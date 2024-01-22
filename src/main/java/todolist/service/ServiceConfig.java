package todolist.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import todolist.repository.MemberRepository;
import todolist.repository.MemoryMemberRepository;

@Configuration
public class ServiceConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
