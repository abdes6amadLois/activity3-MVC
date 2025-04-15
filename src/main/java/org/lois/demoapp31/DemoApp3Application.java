package org.lois.demoapp31;

import org.lois.demoapp31.entities.Patient;
import org.lois.demoapp31.entities.Product;
import org.lois.demoapp31.repository.PatientRepository;
import org.lois.demoapp31.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Date;

@SpringBootApplication
public class DemoApp3Application implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;



    public static void main(String[] args) {
        SpringApplication.run(DemoApp3Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
           // productRepository.save(new Product(null,"computer",5000,11));
            Product product = Product.builder().name("computer")
                    .price(6000).quantity(12).build();
            productRepository.save(product);
            productRepository.save(Product.builder().name("Printer").price(2000).quantity(10).build());
            productRepository.findAll().forEach(System.out::println);
            System.out.println("-------------------------------------------");
            productRepository.findAll().forEach(p -> System.out.println(p.toString()));
        };
    }

    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(new Patient(null,"Mohamed",new Date(),false,34));
        patientRepository.save(new Patient (null, "Hanane", new Date(), false, 4321));
        patientRepository.save(new Patient (null, "Imane", new Date(), true, 34));
        patientRepository.save(new Patient(null,"Said",new Date(),true,70));
        patientRepository.save(new Patient (null, "Oussama", new Date(), false, 321));
        patientRepository.save(new Patient (null, "Israe", new Date(), true, 934));
        patientRepository.save(new Patient (null, "Karim", new Date(), true, 785));
        System.out.println("-----------------------------------------");
        System.out.println(patientRepository.findByNomContains("karim", PageRequest.of(0, 2)));
        System.out.println("-----------------------------------------");
        System.out.println(patientRepository.findAll());
    }
}
