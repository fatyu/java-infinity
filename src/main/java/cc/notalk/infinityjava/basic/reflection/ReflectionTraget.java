package cc.notalk.infinityjava.basic.reflection;

class ReflectionTraget {

    private String title;
    private int age;
    public String name;
    private String company;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getProgrommingLanguage() {
        return progrommingLanguage;
    }

    public void setProgrommingLanguage(String progrommingLanguage) {
        this.progrommingLanguage = progrommingLanguage;
    }

    private String progrommingLanguage;

    public void introduceSelf() {
        System.out.println("my name is " + name + ",age is " + age + ",work at " + company + " ,the titile is " + title + "and use " + progrommingLanguage + " to code!");
    }

    private void skill() {
        System.out.println("be real,i hava no skills!");
    }
}