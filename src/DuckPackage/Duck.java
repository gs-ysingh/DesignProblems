package DuckPackage;

/**
 * Created by YSingh on 11/03/17.
 */

//Taken from Head first design pattern first chapter: Introduction (Strategy pattern)

//Problem statement: There are different types of duck, which can fly/not fly, quack/not quck etc.

interface FlyBehaviour {
    public void fly();
}

interface QuackBehaviour {
    public void quack();
}

class FlyWithWings implements FlyBehaviour {
    @Override
    public void fly() {
        System.out.println("I am flying");
    }
}

class FlyNotPossible implements FlyBehaviour {
    @Override
    public void fly() {
        System.out.println("I am not flying");
    }
}

class Quack implements QuackBehaviour {
    @Override
    public void quack() {
        System.out.println("I can quack");
    }
}

class Squeak implements QuackBehaviour {
    @Override
    public void quack() {
        System.out.println("I can squeak");
    }
}

class MuteQuack implements QuackBehaviour {
    @Override
    public void quack() {
        System.out.println("I am mute");
    }
}

abstract class Duck {
    QuackBehaviour quackBehaviour;
    FlyBehaviour flyBehaviour;

    public void display() {
        System.out.println("Display duck");
    }

    public void performQuack() {
        quackBehaviour.quack();
    }

    public void performFly() {
        flyBehaviour.fly();
    }

    public void setQuackBehaviour(QuackBehaviour q) {
        quackBehaviour = q;
    }

    public void setFlyBehaviour(FlyBehaviour f) {
        flyBehaviour = f;
    }
}

class MallardDuck extends Duck {
    public MallardDuck() {
        quackBehaviour = new Quack();
        flyBehaviour = new FlyWithWings();
    }

    public void display() {
        System.out.println("I am Mallard duck");
    }
}

class RubberDuck extends Duck {
    public RubberDuck() {
        quackBehaviour = new MuteQuack();
        flyBehaviour = new FlyNotPossible();
    }

    public void display() {
        System.out.println("I am Rubber duck");
    }
}

class Solution {
    public static void main(String[] args) {
        Duck duck = new MallardDuck();
        duck.display();
        duck.performFly();
        duck.performQuack();

        //We can change the quack behavior of Mallard duck
        duck.setQuackBehaviour(new Squeak());
        duck.performQuack();

        Duck duck2 = new RubberDuck();
        duck2.display();
        duck2.performFly();
        duck2.performQuack();
    }
}


