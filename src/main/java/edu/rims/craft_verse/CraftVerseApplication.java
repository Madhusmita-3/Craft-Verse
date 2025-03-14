package edu.rims.craft_verse;

import java.io.File;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CraftVerseApplication  implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CraftVerseApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception{
		File file = new File("upload_images");
		if (!file.exists()) {
			file.mkdirs();
			
		}
	}

} 