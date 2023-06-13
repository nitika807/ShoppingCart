package com.example.shoppingcart;

import com.example.shoppingcart.model.Product;
import com.example.shoppingcart.model.UserInfo;
import com.example.shoppingcart.repository.ProductMdbRepo;
import com.example.shoppingcart.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShoppingCartApplication implements CommandLineRunner {

    ProductMdbRepo productMdbRepo;
    UserRepo userRepo;

    @Autowired
    public ShoppingCartApplication(ProductMdbRepo productMdbRepo, UserRepo userRepo) {
        this.productMdbRepo = productMdbRepo;
        this.userRepo = userRepo;
    }


    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
        Product product1 = new Product(1, "Insignia 4.7 Cu. Ft. Top Load Washer ", "Take the hassle" +
                " out of laundry duty with this 4.7 cu. ft. top load washer from Insignia.", 899, "",
                5);
        productMdbRepo.save(product1);
        Product product2 = new Product(2, "Electrolux Front Load Steam Washer (ELFW7537AW) - Whit",
                "Power through your laundry effortlessly with the Electrolux high efficiency front " +
                        "load steam washer. With 5.2 cu. ft. capacity, it " +
                        "can tackle large loads with ease.",1099,"",8);
        productMdbRepo.save(product2);
        Product product3 = new Product(3, "Ninja Air Fryer -" +
                " 3.79L (4QT) - Black",
                "The Ninja air fryer makes it quick and easy to air fry and dehydrate your food in a flash. It boasts a 3.79L capacity that accommodates up to" +
                        " 2 pounds of French fries at once so you can " +
                        "effortlessly whip up family-sized meals.",119,"", 11);
        productMdbRepo.save(product3);
        Product product4 = new Product(4, "Samsung Over-The-Range " +
                "Microwave - 2.1 Cu. Ft. -" +
                " Stainless Steel"," Samsung over-the-range" +
                " microwave can handle just about anything on the menu.",699,"",12);
        productMdbRepo.save(product4);

        UserInfo user1 = new UserInfo(1, "John","pass1", "ADMIN");
        userRepo.save(user1);
        UserInfo user2 = new UserInfo(2, "John","pass2", "USER");
        userRepo.save(user2);
        UserInfo user3 = new UserInfo(3, "Sean","pass3", "USER");
        userRepo.save(user3);
        UserInfo user4 = new UserInfo(4, "Sean","pass4","ADMIN");
        userRepo.save(user4);
    }

}
