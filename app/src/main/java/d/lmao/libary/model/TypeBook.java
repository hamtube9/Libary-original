package d.lmao.libary.model;

public class TypeBook {
    private String maloai, name,tacgia,vitri;


    public TypeBook(String maloai, String name, String tacgia, String vitri) {
        this.maloai = maloai;
        this.name = name;
        this.tacgia = tacgia;
        this.vitri = vitri;

    }
    public TypeBook(){

    }



    public String getMaloai() {
        return maloai;
    }

    public void setMaloai(String maloai) {
        this.maloai = maloai;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getVitri() {
        return vitri;
    }

    public void setVitri(String vitri) {
        this.vitri = vitri;
    }
}
