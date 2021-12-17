package com.mainserver.runners;

import com.mainserver.data.TestDto;
import com.mainserver.entity.TestEntity;
import com.mainserver.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class PostgreSQLRunner implements ApplicationRunner {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;
    private final TestRepository testRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println(dataSource.getClass());
            System.out.println(connection.getMetaData().getURL());
            System.out.println(connection.getMetaData().getUserName());
//            Optional<TestEntity> test = testRepository.findById(1L);
//            test.ifPresent(d -> System.out.println("test = " + (new TestDto(d)).toString()));
//            TestDto testDto = this.jdbcTemplate.queryForObject("SELECT id, content, create_at FROM test where id = ?", TestDto.class, 1);
//            System.out.println("testDto = " + testDto);
        }
    }
}