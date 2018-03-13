package org.crazyit.cloud;

import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServerApp {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String profiles = scan.nextLine();
		new SpringApplicationBuilder(ServerApp.class).profiles(profiles).run(args);
	}

}
