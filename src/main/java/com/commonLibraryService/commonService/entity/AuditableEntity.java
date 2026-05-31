package com.commonLibraryService.commonService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass   // this annotation is used to common this 2 fields in multiple entity class instead of seprate.

// this class use as parent class
// add dependency and addressService and employeeService and extends this AuditableEntity class to both empService and addressService

public class AuditableEntity {

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
