package lect.prac.demoredis;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import lect.prac.demoredis.account.Account;
import lect.prac.demoredis.account.AccountRepository;

@Component
public class RedisRunner implements ApplicationRunner {
    
    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // ValueOperations<String, String> values = redisTemplate.opsForValue();
        // values.set("lect", "prac");
        // values.set("springboot", "2.4.4");
        // values.set("hello", "world");

        Account account = new Account();
        account.setEmail("test@gmail.com");
        account.setUsername("lect");

        accountRepository.save(account);
        Optional<Account> byId = accountRepository.findById(account.getId());
        if(byId.isPresent()) {
            System.out.println(byId.get().getUsername());
            System.out.println(byId.get().getEmail());
        }
    }
}
