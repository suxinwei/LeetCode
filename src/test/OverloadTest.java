package test;

/**
 * created by 80288791 at 2020/4/3
 * description:
 */
class OverloadTest {
    public static void main(String[] args) {
        Student student = new Student();
        say(student);
    }

    public static void say(Person person) {
        person.say();
    }

    static class Person {
        public void say() {
            System.out.println("人");
        }
    }

    static class Student extends Person {
        public void say() {
            System.out.println("学生");
        }
    }
}
