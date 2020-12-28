package cn.lksun.demojob.admin.worker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;

public class RegisterTask implements CommandLineRunner, Ordered {
    @Override
    public void run(String... args) throws Exception {

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
