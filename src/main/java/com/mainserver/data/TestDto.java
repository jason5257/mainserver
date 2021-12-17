package com.mainserver.data;

import com.mainserver.entity.TestEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TestDto {

    private Long id;
    private String content;
    private LocalDateTime createAt;

    public TestDto(TestEntity entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.createAt = entity.getCreateAt();
    }
}
