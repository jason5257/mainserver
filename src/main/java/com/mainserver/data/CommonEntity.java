package com.mainserver.data;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class CommonEntity implements Serializable {

//    @CreatedBy
//    @Column(nullable = false, updatable = false)
//    private String regId;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createAt;

//    @LastModifiedBy
//    private String updId;
//
//    @LastModifiedDate
//    private LocalDateTime updDate;

    @PrePersist
    public void setCreateAt() {
        if(createAt == null) {
            createAt = LocalDateTime.now();
        }
    }
}