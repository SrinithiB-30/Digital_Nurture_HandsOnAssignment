//Exercise 1: Implementing the Singleton Pattern
//SingletonPatternExample
//Define a Singleton Class
public class Logger {
    private static Logger instance;
    
    private Logger() {
        // private constructor to prevent instantiation
    }
    
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
    
    public void log(String message) {
        System.out.println("Log: " + message);
    }
}
//Test the Singleton Implementation
public class SingletonPatternTest {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();
        
        logger1.log("This is the first log message.");
        logger2.log("This is the second log message.");
        
        System.out.println(logger1 == logger2); // should print true
    }
}
//Analysis:

//Time Complexity: O(n) - Where n is the number of years; each recursive call reduces the problem size by one.
//Optimization: Use memoization or iterative solutions to avoid excessive computation and improve efficiency.


//Exercise 2: Implementing the Factory Method Pattern

//Define Document Classes

interface Document {
    void open();
}

class WordDocument implements Document {
    public void open() {
        System.out.println("Opening Word document...");
    }
}

class PdfDocument implements Document {
    public void open() {
        System.out.println("Opening PDF document...");
    }
}

class ExcelDocument implements Document {
    public void open() {
        System.out.println("Opening Excel document...");
    }
}
//Implementing the Factory Method
abstract class DocumentFactory {
    public abstract Document createDocument();
}

class WordDocumentFactory extends DocumentFactory {
    public Document createDocument() {
        return new WordDocument();
    }
}

class PdfDocumentFactory extends DocumentFactory {
    public Document createDocument() {
        return new PdfDocument();
    }
}

class ExcelDocumentFactory extends DocumentFactory {
    public Document createDocument() {
        return new ExcelDocument();
    }
}
//Test the Factory Method Implementation
public class FactoryMethodPatternTest {
    public static void main(String[] args) {
        DocumentFactory wordFactory = new WordDocumentFactory();
        Document wordDoc = wordFactory.createDocument();
        wordDoc.open();

        DocumentFactory pdfFactory = new PdfDocumentFactory();
        Document pdfDoc = pdfFactory.createDocument();
        pdfDoc.open();

        DocumentFactory excelFactory = new ExcelDocumentFactory();
        Document excelDoc = excelFactory.createDocument();
        excelDoc.open();
    }
}

//Exercise 3: Implementing the Builder Pattern

//Product Class
public class Computer {
    private String CPU;
    private String RAM;
    private String storage;
    
    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
    }

    public static class Builder {
        private String CPU;
        private String RAM;
        private String storage;
        
        public Builder setCPU(String CPU) {
            this.CPU = CPU;
            return this;
        }
        
        public Builder setRAM(String RAM) {
            this.RAM = RAM;
            return this;
        }
        
        public Builder setStorage(String storage) {
            this.storage = storage;
            return this;
        }
        
        public Computer build() {
            return new Computer(this);
        }
    }
}
//Nested Builder class inside Computer.
//Ensure Computer has a private constructor that takes Builder as a parameter.

public class BuilderPatternTest {
    public static void main(String[] args) {
        Computer computer = new Computer.Builder()
            .setCPU("Intel i7")
            .setRAM("16GB")
            .setStorage("512GB SSD")
            .build();
        
        System.out.println("Computer built with Builder pattern.");
    }
}


//Exercise 4: Implementing the Adapter Pattern
//Target Interface

interface PaymentProcessor {
    void processPayment(double amount);
}
//Adaptee Classes

class PayPal {
    public void makePayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
    }
}

class Stripe {
    public void charge(double amount) {
        System.out.println("Processing Stripe payment of $" + amount);
    }
}
//Adapter Class
class PayPalAdapter implements PaymentProcessor {
    private PayPal payPal;
    
    public PayPalAdapter(PayPal payPal) {
        this.payPal = payPal;
    }
    
    public void processPayment(double amount) {
        payPal.makePayment(amount);
    }
}

class StripeAdapter implements PaymentProcessor {
    private Stripe stripe;
    
    public StripeAdapter(Stripe stripe) {
        this.stripe = stripe;
    }
    
    public void processPayment(double amount) {
        stripe.charge(amount);
    }
}

//Adapter Implementation
public class AdapterPatternTest {
    public static void main(String[] args) {
        PaymentProcessor payPalProcessor = new PayPalAdapter(new PayPal());
        payPalProcessor.processPayment(100.0);
        
        PaymentProcessor stripeProcessor = new StripeAdapter(new Stripe());
        stripeProcessor.processPayment(200.0);
    }
}

//Exercise 5: Implementing the Decorator Pattern
interface Notifier {
    void send(String message);
}
class EmailNotifier implements Notifier {
    public void send(String message) {
        System.out.println("Sending email: " + message);
    }
}
abstract class NotifierDecorator implements Notifier {
    protected Notifier wrapped;

    public NotifierDecorator(Notifier wrapped) {
        this.wrapped = wrapped;
    }
    
    public void send(String message) {
        wrapped.send(message);
    }
}

class SMSNotifierDecorator extends NotifierDecorator {
    public SMSNotifierDecorator(Notifier wrapped) {
        super(wrapped);
    }
    
    public void send(String message) {
        super.send(message);
        System.out.println("Sending SMS: " + message);
    }
}

class SlackNotifierDecorator extends NotifierDecorator {
    public SlackNotifierDecorator(Notifier wrapped) {
        super(wrapped);
    }
    
    public void send(String message) {
        super.send(message);
        System.out.println("Sending Slack message: " + message);
    }
}
public class DecoratorPatternTest {
    public static void main(String[] args) {
        Notifier notifier = new EmailNotifier();
        Notifier smsNotifier = new SMSNotifierDecorator(notifier);
        Notifier slackNotifier = new SlackNotifierDecorator(smsNotifier);
        
        slackNotifier.send("Hello, this is a notification!");
    }
}

//Exercise 6: Implementing the Proxy Pattern
interface Image {
    void display();
}
class RealImage implements Image {
    private String fileName;
    
    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }
    
    private void loadFromDisk() {
        System.out.println("Loading " + fileName);
    }
    
    public void display() {
        System.out.println("Displaying " + fileName);
    }
}
class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;
    
    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }
    
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
public class ProxyPatternTest {
    public static void main(String[] args) {
        Image image = new ProxyImage("test_image.jpg");
        
        // Image will be loaded from disk
        image.display();
        
        // Image will not be loaded from disk, as it is already loaded
        image.display();
    }
}


//Exercise 7: Implementing the Observer Pattern
interface Stock {
    void registerObserver(Observer observer);
    void deregisterObserver(Observer observer);
    void notifyObservers();
}
//concrete subject

import java.util.ArrayList;
import java.util.List;

class StockMarket implements Stock {
    private List<Observer> observers = new ArrayList<>();
    private double stockPrice;
    
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
    
    public void deregisterObserver(Observer observer) {
        observers.remove(observer);
    }
    
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockPrice);
        }
    }
    
    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
        notifyObservers();
    }
}
interface Observer {
    void update(double stockPrice);
}
class MobileApp implements Observer {
    public void update(double stockPrice) {
        System.out.println("Mobile app notified. New stock price: $" + stockPrice);
    }
}

class WebApp implements Observer {
    public void update(double stockPrice) {
        System.out.println("Web app notified. New stock price: $" + stockPrice);
    }
}
public class ObserverPatternTest {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();
        
        Observer mobileApp = new MobileApp();
        Observer webApp = new WebApp();
        
        stockMarket.registerObserver(mobileApp);
        stockMarket.registerObserver(webApp);
        
        stockMarket.setStockPrice(100.0);
        stockMarket.setStockPrice(105.0);
    }
}


//Exercise 8: Implementing the Strategy Pattern
interface PaymentStrategy {
    void pay(double amount);
}
class CreditCardPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using credit card.");
    }
}

class PayPalPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal.");
    }
}
class PaymentContext {
    private PaymentStrategy paymentStrategy;
    
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
    
    public void pay(double amount) {
        paymentStrategy.pay(amount);
    }
}
public class StrategyPatternTest {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();
        
        context.setPaymentStrategy(new CreditCardPayment());
        context.pay(100.0);
        
        context.setPaymentStrategy(new PayPalPayment());
        context.pay(200.0);
    }
}

//Exercise 9: Implementing the Command Pattern
interface Command {
    void execute();
}
class LightOnCommand implements Command {
    private Light light;
    
    public LightOnCommand(Light light) {
        this.light = light;
    }
    
    public void execute() {
        light.on();
    }
}

class RemoteControl {
    private Command command;
    
    public void setCommand(Command command) {
        this.command = command;
    }
    
    public void pressButton() {
        command.execute();
    }
}
class Light {
    public void on() {
        System.out.println("Light is ON");
    }
    
    public void off() {
        System.out.println("Light is OFF");
    }
}
public class CommandPatternTest {
    public static void main(String[] args) {
        Light livingRoomLight = new Light();
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);
        
        RemoteControl remote = new RemoteControl();
        
        remote.setCommand(lightOn);
        remote.pressButton();
        
        remote.setCommand(lightOff);
        remote.pressButton();
    }
}

//Exercise 10: Implementing the MVC Pattern
class Student {
    private String name;
    private String id;
    private String grade;
    
    public Student(String name, String id, String grade) {
        this.name = name;
        this.id = id;
        this.grade = grade;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getGrade() {
        return grade;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
    }
}
//view class
class StudentView {
    public void displayStudentDetails(String studentName, String studentId, String studentGrade) {
        System.out.println("Student: ");
        System.out.println("Name: " + studentName);
        System.out.println("ID: " + studentId);
        System.out.println("Grade: " + studentGrade);
    }
}
//Controller Class
class StudentController {
    private Student model;
    private StudentView view;
    
    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }
    
    public void setStudentName(String name) {
        model.setName(name);
    }
    
    public String getStudentName() {
        return model.getName();
    }
    
    public void setStudentId(String id) {
        model.setId(id);
    }
    
    public String getStudentId() {
        return model.getId();
    }
    
    public void setStudentGrade(String grade) {
        model.setGrade(grade);
    }
    
    public String getStudentGrade() {
        return model.getGrade();
    }
    
    public void updateView() {
        view.displayStudentDetails(model.getName(), model.getId(), model.getGrade());
    }
}
public class MVCPatternTest {
    public static void main(String[] args) {
        Student model = new Student("John Doe", "12345", "A");
        
        StudentView view = new StudentView();
        
        StudentController controller = new StudentController(model, view);
        
        controller.updateView();
        
        controller.setStudentName("Jane Doe");
        controller.updateView();
    }
}
//Exercise 11: Implementing Dependency Injection
interface CustomerRepository {
    Customer findCustomerById(String id);
}
class CustomerRepositoryImpl implements CustomerRepository {
    public Customer findCustomerById(String id) {
        return new Customer(id, "Customer Name");
    }
}
class CustomerService {
    private CustomerRepository repository;
    
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }
    
    public Customer getCustomer(String id) {
        return repository.findCustomerById(id);
    }
}
public class DependencyInjectionTest {
    public static void main(String[] args) {
        CustomerRepository repository = new CustomerRepositoryImpl();
        CustomerService service = new CustomerService(repository);
        
        Customer customer = service.getCustomer("12345");
        System.out.println("Customer ID: " + customer.getId());
        System.out.println("Customer Name: " + customer.getName());
    }
}

class Customer {
    private String id;
    private String name;
    
    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
}
