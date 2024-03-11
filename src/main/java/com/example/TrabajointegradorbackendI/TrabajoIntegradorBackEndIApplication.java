package com.example.TrabajointegradorbackendI;

import com.example.TrabajointegradorbackendI.dao.BD;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrabajoIntegradorBackEndIApplication {

	public static void main(String[] args) {
		BD.crearTablas();
		SpringApplication.run(TrabajoIntegradorBackEndIApplication.class, args);
	}

}
