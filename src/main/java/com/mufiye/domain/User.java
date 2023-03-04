package com.mufiye.domain;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Data
@Node("User")
public class User implements Cloneable{
    @Id
    @GeneratedValue
    private Long id;

    @Property
    private String username;
    @Property
    private String password;
    @Property
    private String name;
    @Property
    private String role;
    @Property
    private String email;
    @Property
    private String lastLogin;

    @Override
    public Object clone() {
        try {
            return super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
            return null;
        }
    }
}
