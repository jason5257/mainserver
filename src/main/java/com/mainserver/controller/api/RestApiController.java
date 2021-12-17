package com.mainserver.controller.api;

import com.mainserver.controller.common.RestResult;
import com.mainserver.data.TestDto;
import com.mainserver.entity.TestEntity;
import com.mainserver.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RestApiController {

    private final TestRepository testRepository;
    private final ModelMapper modelMapper;

    @GetMapping("/rest")
    public String restapi() {
        return "Hello My Server - " + LocalDateTime.now();
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
