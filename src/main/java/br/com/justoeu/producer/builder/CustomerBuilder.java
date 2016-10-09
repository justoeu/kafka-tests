package br.com.justoeu.producer.builder;

import br.com.justoeu.producer.domain.Customer;

import java.util.Random;

public class CustomerBuilder {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;


    public CustomerBuilder firstName(String name){
        this.firstName = name;
        return this;
    }

    public CustomerBuilder lastName(String name){
        this.lastName = name;
        return this;
    }

    public CustomerBuilder code(int id){
        this.id = id;
        return this;
    }

    public CustomerBuilder email(String email){
        this.email = email;
        return this;
    }

    public CustomerBuilder phone(String phone){
        this.phone = phone;
        return this;
    }

    public Customer build(){

        Customer customer = new Customer();
        customer.setId(this.id);
        customer.setFirstName(this.firstName);
        customer.setLastName(this.lastName);
        customer.setEmail(this.email);
        customer.setPhone(this.phone);

        return customer;
    }

    public Customer buildFake(){
        Customer customer = new Customer();
        customer.setId(new Random().nextInt());
        customer.setFirstName("Joao " + new Random().nextInt());
        customer.setLastName("Oliveira " + new Random().nextInt());
        customer.setEmail(new Random().nextInt()+"@testEmail.com");
        customer.setPhone(String.valueOf(new Random().nextInt(10000000)));

        return customer;
    }
}
