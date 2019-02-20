package d.lmao.libary.model;

public class HoaDon {
    private String id;
    private long datetime;

    public HoaDon(){

    }

    public HoaDon(String id, long datetime) {
        this.id = id;
        this.datetime = datetime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }
}
