package com.mark.bootwebpractice2.repository;

import com.mark.bootwebpractice2.domain.Board;
import com.mark.bootwebpractice2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
  Board findByUser(User user);
}
