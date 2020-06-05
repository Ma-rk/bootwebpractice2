package com.mark.bootwebpractice2;

import com.mark.bootwebpractice2.domain.Board;
import com.mark.bootwebpractice2.domain.User;
import com.mark.bootwebpractice2.domain.enums.BoardType;
import com.mark.bootwebpractice2.repository.BoardRepository;
import com.mark.bootwebpractice2.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@Slf4j
@SpringBootApplication
public class MarkNameApplication {

  public static void main(String[] args) {
    SpringApplication.run(MarkNameApplication.class, args);
  }

  @Bean
  public CommandLineRunner runner(UserRepository userRepository,
                                  BoardRepository boardRepository) throws Exception {
    log.info("inserts initial data:");
    return (args -> {
      log.info("begin inserting....");
      User user = userRepository.save(User.builder()
              .name("Mark nim")
              .password("a pw")
              .email("email@email.com")
              .createdDate(LocalDateTime.now())
              .build());
      IntStream.rangeClosed(1, 200).forEach(index ->
              boardRepository.save(Board.builder()
                      .title("글 제목 " + index)
                      .subTitle("부제목" + index)
                      .content("본문" + index)
                      .boardType(BoardType.free)
                      .createdDate(LocalDateTime.now())
                      .updatedDate(LocalDateTime.now())
                      .user(user)
                      .build()
              )
      );
      log.info("inserting finished!");
    });
  }
}
