package simple;

import io.quarkus.logging.Log;

public class ExternalMessageServiceImpl implements ExternalMessageService {

    CarService carService;

    public ExternalMessageServiceImpl(CarService carService) {
        this.carService = carService;
    }

    @Override
    public String sayHelloFromOuterSpace(String name) {
        return "Hello from outer space" + name;
    }

    private void doSomethingPrivately() {
        carService.doSomething();
    }

    @Override
    public String sayHelloFromOuterSpace() {
        doSomethingPrivately();
        return "Hello from outer space";
    }

    @Override
    public void fazAlgo() {
        Log.info("faz Algo");
    }
}
