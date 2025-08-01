package simple;

public class HelloExtAcademy {

    ExternalMessageService externalMessageService;

    public HelloExtAcademy(ExternalMessageService externalMessageServiceMock) {
        this.externalMessageService = externalMessageServiceMock;
    }


    public String sayHello(String name) {
        if (name != null) {
            return externalMessageService.sayHelloFromOuterSpace(name);
        } else {
            return externalMessageService.sayHelloFromOuterSpace();
        }
    }
}
