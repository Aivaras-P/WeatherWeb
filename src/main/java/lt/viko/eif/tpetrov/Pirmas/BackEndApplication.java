package lt.viko.eif.tpetrov.Pirmas;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BackEndApplication {

	public static void main(String[] args) {


		SpringApplication.run(BackEndApplication.class, args);

	}


}
