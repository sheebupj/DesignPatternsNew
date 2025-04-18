package Builder;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

final class Employee{
    private Integer id;
    private String name;
    private String department;
    private Double salary;


    public Employee (EmployeeBuilder employeeBuilder){
        this.id=employeeBuilder.id;
        this.name= employeeBuilder.name;
        this.department=employeeBuilder.department;
        this.salary=employeeBuilder.salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }

    public static class EmployeeBuilder {
        private Integer id;
        private String name;
        private String department;
        private Double salary;



        public EmployeeBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public EmployeeBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public EmployeeBuilder setDepartment(String department) {
            this.department = department;
            return this;
        }

        public EmployeeBuilder setSalary(Double salary) {
            this.salary = salary;
            return this;
        }
        public static EmployeeBuilder newInstance(){
            return new EmployeeBuilder();
        }
        public Employee build(){
            return new Employee(this);
        }


    }
}
class Runner {
    volatile Employee employee;

    public static void main(String[] args) {
        //  System.out.println(Employee.EmployeeBuilder.newInstance().setId(1).setName("sheebu").setDepartment("IT").setSalary(200000.0).build().toString());
        Runner runner = new Runner();
        Runnable runnable1 = () ->
                runner.employee = Employee.EmployeeBuilder
                        .newInstance().setId(1)
                        .setName("sheebu")
                        .setDepartment("Developement")
                        .setSalary(200000.0)
                        .build();

        Runnable runnable2 = () ->
                runner.employee = Employee.EmployeeBuilder
                        .newInstance().setId(2)
                        .setName("sheena")
                        .setDepartment("Developement")
                        .setSalary(100000.0)
                        .build();


        ExecutorService es = Executors.newFixedThreadPool(2);
        es.submit(runnable1);
        es.submit(runnable2);

        try {
            es.awaitTermination(100, TimeUnit.MILLISECONDS) ;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(runner.employee.toString());
    }
}


