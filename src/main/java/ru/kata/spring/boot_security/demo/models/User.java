package ru.kata.spring.boot_security.demo.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "age")
    private int age;
    @Column(name = "password")
    private String password;
    @Column
    private String firstName;
    @Column
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        if (roles == null) {
            roles = new HashSet<>();
        }
        roles.add(role);
    }

    public Set<Role> getRole() {
        return roles;
    }

    public User() {
    }

    public User(String username, int age, String password, String firstName, String lastName) {
        this.username = username;
        this.age = age;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String username, int age, String password, String firstName, String lastName, Set<Role> roles) {
        this.username = username;
        this.age = age;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }

    public String roleToString() {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (Role role : roles) {
            sb.append(role.getName()).append(" ");
            sb.delete(0, 5);
            list.add(sb.toString());
        }
        return list.toString().replace("[", "").replace("]", "").replace("ROLE_", "");
    }
}
