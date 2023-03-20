package ru.kata.spring.boot_security.demo.models;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users",
        uniqueConstraints = {@UniqueConstraint(columnNames = "username")})      // уникальный логин
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    @NotEmpty(message = "У пользователя должна быть почта")
    @Size(min = 4, max = 100, message = "Почта должна быть от 4 до 100 символов длинной")
    @Email(message = "Почта должна быть в формате example@ex.ex")
    private String username;
    @Column(name = "age")
    @Min(value = 0, message = "Возвраст пользователя должен быть больше или равен 0")
    private int age;
    @Column(name = "password")
    private String password;
    @Column
    @NotEmpty(message = "Имя пользователя не может быть пустым")
    private String firstName;
    @Column
    @NotEmpty(message = "Фамилия пользователя не может быть пустой")
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

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
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

//    public User(String username, int age) {
//        this.username = username;
//        this.age = age;
//    }
//
//
//    public User(String username, int age, String password, Set<Role> roles) {
//        this.username = username;
//        this.age = age;
//        this.password = password;
//        this.roles = roles;
//    }

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

    public void setAge(int yearOfBirth) {
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
}
