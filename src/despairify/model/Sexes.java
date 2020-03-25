package despairify.model;

public class Sexes {
	protected int sexId;
    protected String sex;
  
    public Sexes(int sexId, String sex) {
        this.sexId = sexId;
        this.sex = sex;
    }
    
    public Sexes(String sex) {
        this.sex = sex;
    }
  
    public int getSexId() {
        return sexId;
    }
  
    public void setSexId(int sexId) {
        this.sexId = sexId;
    }
  
    public String getSex() {
        return sex;
    }
  
    public void setSex(String sex) {
        this.sex = sex;
    }
}
