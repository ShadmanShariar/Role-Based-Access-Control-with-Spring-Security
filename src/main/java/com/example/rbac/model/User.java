package com.example.rbac.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;

    private String username;
    private String password;

    private Set<String> roleIds = new HashSet<>();

}
