
public class EmployeeHashTable {
    
    public static class Employee {
        String name;
        String job;
        int salary;

        public Employee(String name, String job, int salary) {
            this.name = name;
            this.job = job;
            this.salary = salary;
        }

        public void show_info() {
            System.out.println(
                            "----------------------------------------------------------------\n" +
            "Employee\nName: " + this.name + "\nJob: " + this.job + "\nSalary: " + this.salary +
            "\n----------------------------------------------------------------"
            );
        }
    }
    public static void main(String[] args) {
        HashTable<Integer, Employee> table = new HashTable<>();
        table.put(table.size(), new Employee("John Smith", "Boss", 6000)); // Uid = 0
        table.put(table.size(), new Employee("Tom Waits", "Manager", 5000)); // Uid = 1
        table.put(table.size(), new Employee("Mig Jagger", "Secretary", 4000)); // Uid = 2
        Employee employee = table.get(0);
        employee.show_info();
        Employee employee1 = table.get(1);
        employee1.show_info();
        Employee employee2 = table.get(2);
        employee2.show_info();
        
        table.remove(2);
        System.out.println(table.get(2));
        System.out.println(table.size());

        
    }
}
