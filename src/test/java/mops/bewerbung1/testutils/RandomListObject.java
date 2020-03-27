package mops.bewerbung1.testutils;

import java.util.List;

public class RandomListObject<T> {
  public T getRandomObject(List<T> list) {
    return list.get((int) (Math.random() * list.size()));
  }
}