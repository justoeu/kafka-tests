package br.com.justoeu.kafka.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Customer implements Serializable {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
