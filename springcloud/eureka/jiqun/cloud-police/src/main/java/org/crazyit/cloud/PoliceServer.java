package org.crazyit.cloud;

import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PoliceServer {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// 读取控制台的端口输入
		String port = scan.nextLine();
		new SpringApplicationBuilder(PoliceServer.class).properties("server.port=" + port).run(args);
	}

}
