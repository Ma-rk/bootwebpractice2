package com.mark.bootwebpractice2;

import com.mark.bootwebpractice2.domain.Board;
import com.mark.bootwebpractice2.domain.User;
import com.mark.bootwebpractice2.domain.enums.BoardType;
import com.mark.bootwebpractice2.repository.BoardRepository;
import com.mark.bootwebpractice2.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@SpringBootTest
class MarkNameApplicationTests {
  private final String name = "Mark";
  private final String password = "pwpwpw";
  private final String email = "test@email.com";

  private final String title = "the title";
  private final String subTitle = "the subTitle";
  private final String content = "the content";

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private BoardRepository boardRepository;

  @BeforeEach
  public void init() {
    User newUser = User.builder()
            .name(this.name)
            .password(this.password)
            .email(this.email)
            .createdDate(LocalDateTime.now())
            .build();
    User retrievedUser = this.userRepository.save(newUser);

    Board newBoard = Board.builder()
            .title(this.title)
            .subTitle(this.subTitle)
            .content(this.content)
            .boardType(BoardType.free)
            .createdDate(LocalDateTime.now())
            .updatedDate(LocalDateTime.now())
            .user(retrievedUser)
            .build();
    this.boardRepository.save(newBoard);
  }

  @Test
  public void runTest() {
    User user = this.userRepository.findByEmail(email);
    assertThat(user.getName(), is(this.name));
    assertThat(user.getPassword(), is(this.password));
    assertThat(user.getEmail(), is(this.email));

    Board board = this.boardRepository.findByUser(user);
    assertThat(board.getTitle(), is(this.title));
    assertThat(board.getSubTitle(), is(this.subTitle));
    assertThat(board.getContent(), is(this.content));
  }
}
