package Builder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

final class Student {

    private Integer id;
    private String name;
    private String course;
    private Double marks;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", course='" + course + '\'' +
                ", marks=" + marks +
                '}';
    }

    public Student(Builder builder) {
        this.id=builder.id;
        this.name=builder.name;
        this.course=builder.course;
        this.marks=builder.marks;
    }

    public static class Builder {
        private Integer id;
        private String name;
        private String course;
        private Double marks;

        public Builder setId(Integer id) {
           this.id = id;
            return this;
        }
        public Builder setName(String name) {
            this.name= name;
            return this;
        }
        public Builder setCourse(String course) {
            this.course= course;
            return this;
        }
        public Builder setMarks(Double marks) {
            this.marks= marks;
            return this;
        }
        public Student build(){
            return new Student(this);
        }
        public static Builder newInstance(){
            return new Builder();
        }

    }



}
class StudentRunner{
    volatile  Student  student;

    public static void main(String[] args) {
        StudentRunner sr =  new StudentRunner();
        Runnable runnable1=()-> sr.student= Student.Builder.newInstance()
                .setId(1)
                .setName("Jayadevan")
                .setCourse("MSc")
                .setMarks(90.0)
                .build();
        Runnable runnable2=()-> sr.student= Student.Builder.newInstance()
                .setId(2)
                .setName("Nirmala")
                .setCourse("MPhil")
                .setMarks(80.0)
                .build();

        ExecutorService es= Executors.newFixedThreadPool(2);
        es.submit(runnable1);
        es.submit(runnable2);
        try {
            es.awaitTermination(50, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(sr.student.toString());
    }



}

