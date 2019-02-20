package d.lmao.libary.model;

public class Book {
    private String masach,tensach,gia,soluong;
    private byte[] anh;

    public Book(){

    }

    public Book(String masach, String tensach, String gia, String soluong, byte[] anh) {

        this.masach = masach;
        this.tensach = tensach;
        this.gia = gia;
        this.soluong = soluong;
        this.anh = anh;
    }





    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }
}
