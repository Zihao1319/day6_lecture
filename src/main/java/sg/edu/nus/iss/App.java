package sg.edu.nus.iss;

import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + "\tRunnable ..." + i);
                }
            }

        });
        // thread1.start();

        MyRunnableImplementation mRI = new MyRunnableImplementation("task1");
        MyRunnableImplementation mRI2 = new MyRunnableImplementation("task2");
        MyRunnableImplementation mRI3 = new MyRunnableImplementation("task3");
        MyRunnableImplementation mRI4 = new MyRunnableImplementation("task4");
        MyRunnableImplementation mRI5 = new MyRunnableImplementation("task5");

        // Thread thread2 = new Thread(mRI);
        // thread2.start();

        // Thread thread3 = new Thread(mRI);
        // thread3.start();

        // single thread only
        // ExecutorService executorService = Executors.newSingleThreadExecutor();
        // executorService.execute(mRI);
        // executorService.execute(mRI2);
        // executorService.shutdown();

        // creating thread pool
        // ExecutorService executorService = Executors.newFixedThreadPool(3);
        // executorService.execute(mRI);
        // executorService.execute(mRI2);
        // executorService.execute(mRI3);
        // executorService.execute(mRI4);
        // executorService.execute(mRI5);
        // executorService.shutdown();

        // unlike fixed, cached will allocate counters for you dynamically
        // ExecutorService executorService = Executors.newCachedThreadPool();
        // executorService.execute(mRI);
        // executorService.execute(mRI2);
        // executorService.execute(mRI3);
        // executorService.execute(mRI4);
        // executorService.shutdown();

        // example of how you run a runnable interface
        MyRunnableInterface<Integer> addOperation = (a, b) -> {
            return a + b;
        };

        MyRunnableInterface<Integer> multiplyOperation = (a, b) -> {
            return a * b;
        };

        MyRunnableInterface<String> concatOperation = (a, b) -> {
            return a + b;
        };

        MyMessageInterface printString = (a) -> {
            System.out.println(a);
        };

        // System.out.printf("addOperation: %d\n", addOperation.process(1, 1));
        // System.out.printf("multiplyOperation: %d\n", multiplyOperation.process(2,
        // 2));
        // System.out.printf("concatOperation: %s\n", concatOperation.process("cat",
        // "food"));
        // printString.printMessage("Go for break at 12pm");

        // list of employees
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee(1, "zihao", "ooi", 10000));
        employees.add(new Employee(2, "david", "loh", 10000));
        employees.add(new Employee(3, "adam", "khoo", 20000));
        employees.add(new Employee(4, "bernard", "tan", 2000));

        // employees.forEach(employee -> {
        // System.out.println(employee);
        // });

        //collectors to list is necessary so that it can be collected as list and displayed
        List<Employee> filteredEmployees = employees.stream()
                .filter(employee -> employee.getLastName().contains("oo"))
                .collect(Collectors.toList());

        // filteredEmployees.forEach(employee -> {
        // System.out.println(employee);
        // });

        employees.sort(Comparator.comparing(emp -> emp.getFirstName()));
        
        //compare by firstname then reverse it
        Comparator<Employee> compare = Comparator.comparing(e -> e.getFirstName());
        employees.sort(compare.reversed());

        //sort by first name, and then within the people with same firstname, sort by last name
        Comparator<Employee> groupByComparator = Comparator.comparing(Employee::getFirstName)
                .thenComparing(Employee::getLastName);

        employees.sort(groupByComparator);
        
        //print the info out
        employees.forEach(employee -> {
            System.out.println(employee);
        });

    }

}
