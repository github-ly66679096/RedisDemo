package cn.edu.ncst.pojo;


public class City {

    private String id;
    private String codeCid;
    private String name;
    private String codePid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodeCid() {
        return codeCid;
    }

    public void setCodeCid(String codeCid) {
        this.codeCid = codeCid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodePid() {
        return codePid;
    }

    public void setCodePid(String codePid) {
        this.codePid = codePid;
    }

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", codeCid='" + codeCid + '\'' +
                ", name='" + name + '\'' +
                ", codePid='" + codePid + '\'' +
                '}';
    }
}
