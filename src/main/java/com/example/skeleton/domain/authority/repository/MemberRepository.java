package com.example.skeleton.domain.authority.repository;

import com.example.skeleton.domain.authority.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}