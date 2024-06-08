package com.roze.SpringBootRecapFinal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class MyFirstService {
    private Environment environment;
    //    @Autowired
//    @Qualifier("secondBean")
    private FirstClass firstClass;

    //    public MyFirstService(
//          // @Qualifier("bean2")
//          FirstClass firstClass
//    ) {
//        this.firstClass = firstClass;
//    }
//    @Autowired
//    public void initDependency(@Qualifier("firstBean") FirstClass firstClass) {
//        this.firstClass = firstClass;
//    }
    @Autowired
    public void setFirstClass(@Qualifier("firstBean") FirstClass firstClass) {
        this.firstClass = firstClass;

    }

    public String helloSpring() {
        return "Hello From dependency Injection: " + firstClass.hello();
    }

    public String getJavaVersion() {
        return "My Current Java Version: " + environment.getProperty("java.version");
    }

    public String getOs() {
        return "My OS name is: " + environment.getProperty("os.name");
    }
    public String getMyName() {
        return "My name is: " + environment.getProperty("my.name.is");
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
