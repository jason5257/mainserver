package com.mainserver.controller.api;

import com.mainserver.controller.common.RestResult;
import com.mainserver.data.TestDto;
import com.mainserver.entity.TestEntity;
import com.mainserver.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RestApiController {

    private final TestRepository testRepository;
    private final ModelMapper modelMapper;
    private final Environment env;

    private static final String NEW_LINE = "\n";
    private static final String NEW_LINE_BR = "<br>";

    @GetMapping("/rest")
    public String restapi() {
        StringBuffer sf = new StringBuffer();

        Map<String, String> senv = System.getenv();
        System.out.println("senv = " + senv);

        senv.forEach((s, s2) -> {
            sf.append(s + " = " + s2 + NEW_LINE_BR);
        });

        sf.append("Hello My Server - " + LocalDateTime.now() + NEW_LINE_BR);
        sf.append("env.getActiveProfiles() = " + String.join(", ", env.getActiveProfiles()) + NEW_LINE_BR);

        return sf.toString();
    }

    @GetMapping("/api/test/{id}")
    public ResponseEntity<RestResult> load(@PathVariable Long id) {
        TestEntity testEntity = testRepository.findById(id).orElseThrow(() -> new RuntimeException("not exists data - id : " + id));
        return ResponseEntity.ok(RestResult.builder()
                .success(true)
                .result(modelMapper.map(testEntity, TestDto.class))
                .message("success").build());
    }

    @PostMapping("/api/test")
    public ResponseEntity<RestResult> save(@RequestBody TestDto dto) {

        TestEntity entity = modelMapper.map(dto, TestEntity.class);
        testRepository.save(entity);

        TestEntity testEntity = testRepository.findById(entity.getId()).orElseThrow(() -> new RuntimeException("save failed"));

        return ResponseEntity.ok(RestResult.builder()
                .success(true)
                .result(modelMapper.map(testEntity, TestDto.class))
                .message("success").build());
    }

    @DeleteMapping("/api/test/{id}")
    public ResponseEntity<RestResult> delete(@PathVariable Long id) {

        TestEntity testEntity = testRepository.findById(id).orElseThrow(() -> new RuntimeException("not exists data - id : " + id));

        testRepository.delete(testEntity);

        return ResponseEntity.ok(RestResult.builder()
                .success(true)
                .result(modelMapper.map(testEntity, TestDto.class))
                .message("success").build());
    }
}
