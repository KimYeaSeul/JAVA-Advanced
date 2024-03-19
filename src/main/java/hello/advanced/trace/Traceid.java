package hello.advanced.trace;

import java.util.UUID;

import lombok.Getter;

@Getter
public class Traceid {

  private String id;
  private int level; // 로그의 깊이 표현

  public Traceid() {
    this.id = createId();
    this.level = 0;
  }

  private Traceid(String id, int level) {
    this.id = id;
    this.level = level;
  }
  private String createId(){
    return UUID.randomUUID().toString().substring(0, 8);
  }

  public Traceid createNextId() {
    return new Traceid(id, level+1);
  }

  public Traceid createPreviousId() {
    return new Traceid(id, level-1);
  }

  public boolean isFirstLevel() {
    return level == 0;
  }
}
