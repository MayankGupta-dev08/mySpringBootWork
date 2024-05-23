package dev.mayank.other.injection.constructor;

class Employee {
    private Integer sapId;
    private String fullName;
    private Character sex;
    private Integer salary;

    public Employee() {
    }

    public Employee(Integer sapId, String fullName, Character sex, Integer salary) {
        this.sapId = sapId;
        this.fullName = fullName;
        this.sex = sex;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{\n sapId=%d,\n fullName='%s',\n sex=%s,\n salary=%d\n}"
                .formatted(sapId, fullName, sex, salary);
    }
}