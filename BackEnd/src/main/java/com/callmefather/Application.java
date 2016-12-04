package com.callmefather;

import com.callmefather.dao.DAOUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by xinliu on 12/3/16.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        DAOUtil.init();
        SpringApplication.run(Application.class, args);
        System.out.println("started");
    }
}
