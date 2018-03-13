package anddd7.springboot.utils;

/**
 * Created by edliao on 2017/5/5.
 */
public class ID {
    public Long current;
    public Long next;
    public Long end;

    public ID(Long current, Long next, Long end) {
        this.current = current;
        this.next = next;
        this.end = end;
    }
}
