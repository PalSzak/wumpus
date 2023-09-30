package demo.wumpus;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ImmputableCoord {

  private final Integer x;
  private final Integer y;

  public ImmputableCoord(Integer x, Integer y) {
    this.x = x;
    this.y = y;
  }

  public Integer getX() {
    return x;
  }

  public Integer getY() {
    return y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (!(o instanceof ImmputableCoord)) return false;

    ImmputableCoord that = (ImmputableCoord) o;

    return new EqualsBuilder().append(getX(), that.getX()).append(getY(), that.getY()).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(getX()).append(getY()).toHashCode();
  }
}
