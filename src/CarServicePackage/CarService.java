package CarServicePackage;

/**
 * Created by YSingh on 01/03/17.
 */

    //http://www.design-patterns-stories.com/patterns/Command/
    //https://www.youtube.com/watch?v=7Pj5kAhVBlg

interface Command {
    public void execute();
}

class Client implements Command {
    Command command;

    public Client(Command command) {
        this.command = command;
    }

    @Override
    public void execute() {
        command.execute();
    }
}

interface ICarService {
    public void assignTechnician();
}

class Manager implements ICarService {

    @Override
    public void assignTechnician() {
        System.out.println("Technician Assigned");
    }
}

class CarServiceRequested implements Command {
    Manager manager;

    public CarServiceRequested(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.assignTechnician();
    }
}

class CarService {
    public static void main(String[] args) {
        Manager manager = new Manager();
        CarServiceRequested carServiceRequested = new CarServiceRequested(manager);
        Client client = new Client(carServiceRequested);
        client.execute();
    }
}