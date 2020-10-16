package top.erzhiqian.weixin.message.domain.entity;

public class AutoIncrementEntity {
    protected Long id;

    public AutoIncrementEntity() {
    }

    public AutoIncrementEntity(Long id) {
        setId(id);
    }

    public Long id() {
        return id;
    }

    protected void setId(Long id) {
        if (null == id){
            throw new IllegalArgumentException("illegal state");
        }
        this.id = id;
    }

    public void saveId(Long id){
       setId(id);
    }
}
