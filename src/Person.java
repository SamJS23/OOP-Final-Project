public class Person {
    private int personId;
    private String personName;




    public int getPersonId(){
        return personId;
    }
    public String getPersonName(){
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Person(int personId, String personName){
        this.personId = personId;
        this.personName = personName;
    }


}
