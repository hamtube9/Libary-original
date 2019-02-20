package d.lmao.libary.model;

public class User {
    private String username, pass, name , phone;
    private byte[] anh;


    public User() {

    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }

    public User( String username, String pass, String name, String phone, byte[] anh) {


        this.username = username;
        this.pass = pass;
        this.name = name;
        this.phone = phone;
        this.anh = anh;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


}
