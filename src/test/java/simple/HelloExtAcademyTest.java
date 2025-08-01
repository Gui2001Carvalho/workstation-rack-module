package simple;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HelloExtAcademyTest {

    @Mock
    ExternalMessageService externalMessageServiceSpy;

    @InjectMocks
    HelloExtAcademy helloExtAcademy;
    CarService carService;

    @BeforeEach
    void setUp() {
        externalMessageServiceSpy = Mockito.spy(new ExternalMessageServiceImpl(mock(CarService.class)));
        helloExtAcademy = new HelloExtAcademy(externalMessageServiceSpy);
    }

    @Test
    @DisplayName("When providing a null name a message from outer space should be returned")
    void providing_a_null_name_message_from_outer_space_should_be_returned() {
        //given
        String name = null;
        when(externalMessageServiceSpy.sayHelloFromOuterSpace()).thenReturn("Hello from outer space");
        //Mockito.doReturn("Hey Rennan").when(externalMessageServiceSpy).sayHelloFromOuterSpace(name);

        //Mockito.when(externalMessageServiceSpy.sayHelloFromOuterSpace())
        //        .thenReturn("Hello from outer space");
        //when
        String actualName = helloExtAcademy.sayHello(name);
        //then
        assertEquals("Hello from outer space", actualName);
        /*
        assertThat(actualName)
                .as("The actual name should be: Hello from Outer Space")
                .isEqualTo("Hello from Outer Space");
         */
    }

    @Test
    @DisplayName("When providing a name from outer space a name should be returned")
    void providing_a_name_from_outer_space_should_be_returned() {
        //given
        String name = "Rennan";
        String outroNome = "Gui";
        Mockito.when(externalMessageServiceSpy.sayHelloFromOuterSpace(name))
                .thenReturn("Hello " + name);
        //when
        String actualName = helloExtAcademy.sayHello(name);
        //than
        assertThat(actualName).isEqualTo("Hello " + name);
    }
}