package com.gustavo.taskapi.taskmanager.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter//(AccessLevel.PROTECTED) // setter privado só para uso interno
@NoArgsConstructor//(access = AccessLevel.PROTECTED) // JPA exige construtor padrão
@AllArgsConstructor(access = AccessLevel.PRIVATE) // construtor privado para o Builder
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    @Email // validação de email
    @NotBlank(message = "Email é obrigatório") // validação de campo obrigatório
    private String email;

    @NotBlank(message = "Senha é obrigatória") // validação de campo obrigatório
    @Column(length = 60) // tamanho máximo para segurança
    private String password;

    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default // garante que não será nulo quando usar Builder
    private List<Task> tasks = new ArrayList<>();

//    // 🔧 Método de criação controlada
//    public static User create(String name, String email, String password, String role) {
//        return User.builder()
//                .name(name)
//                .email(email)
//                .password(password)
//                .role(role)
//                .tasks(new ArrayList<>())
//                .build();
//    }
//
//
//    public void addTask(Task task) {
//        this.tasks.add(task);
//        task.setUser(this); // atualiza o lado da tarefa
//    }
//
//    public void removeTask(Task task) {
//        this.tasks.remove(task);
//        task.setUser(null); // quebra o vínculo com o user
//    }
}
