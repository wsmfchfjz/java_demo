package anddd7.springboot.controller.bean;


import java.util.List;

public class ResponseListWrapper<T> {
    Long totalCount;
    Long currentCount;
    List<T> objs;

    public ResponseListWrapper(Long totalCount, List<T> objs) {
        this.totalCount = totalCount;
        this.objs = objs;
        this.currentCount = Long.valueOf(objs.size());
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getObjs() {
        return objs;
    }

    public void setObjs(List<T> objs) {
        this.objs = objs;
        this.currentCount = Long.valueOf(objs.size());
    }
}
