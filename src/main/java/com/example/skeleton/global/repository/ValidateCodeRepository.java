package com.example.skeleton.global.repository;

import com.example.skeleton.global.entity.ValidateCode;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ValidateCodeRepository extends CrudRepository<ValidateCode, String> {
    Optional<ValidateCode> findByValidateCode(String validateCode);
}