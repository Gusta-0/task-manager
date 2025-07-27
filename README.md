# 📅 Task Manager API

Uma API RESTful desenvolvida com **Java + Spring Boot** para gerenciamento de tarefas com autenticação, agendamento automático e envio de notificações por e-mail. Ideal para organizar tarefas com prazos e status no estilo **Kanban**.

---

## 🚀 Funcionalidades

- ✅ Cadastro e autenticação de usuários com JWT
- ✅ CRUD de tarefas vinculadas ao usuário
- ✅ Acompanhamento de status da tarefa (A FAZER, EM PROGRESSO, CONCLUÍDO)
- ✅ Agendamento automático com `@Scheduled`
- ✅ Notificações por e-mail antes da data limite
- ✅ Boas práticas com Git Flow, testes e validações

---

## 🛠️ Tecnologias e Dependências

- Java 17+
- Spring Boot 3.x
- Spring Web
- Spring Security (JWT)
- Spring Data JPA
- PostgreSQL
- Java Mail Sender
- Spring Scheduler
- Lombok
- Bean Validation

---

## 🧠 Estrutura do Projeto (Domínio)

### 👤 User
- `id`, `name`, `email`, `password`, `role`
- Relacionamento: 1 usuário → N tarefas

### 📌 Task
- `title`, `description`, `dueDate`, `status`
- Relacionamento: pertence a 1 usuário

### ✉️ Notification
- `sendDate`, `sent`
- Relacionamento: associada a 1 tarefa

---

## 🔐 Autenticação

A autenticação é baseada em **JWT**:
- Usuário realiza login e recebe um token
- Todas as rotas protegidas exigem esse token no cabeçalho:  
  `Authorization: Bearer <token>`

---

## 📸 Diagrama de Classes

> 💡 Diagrama gerado com Mermaid. Como o GitHub não renderiza Mermaid no README, uma imagem está disponível abaixo.

![Diagrama de Classes](docs/diagrama.png)

<details>
<summary>Código Mermaid</summary>

```mermaid
classDiagram
    class User {
        - id: Long
        - name: String
        - email: String
        - password: String
        - role: String
        - tasks: List~Task~
        + getId(): Long
        + getName(): String
        + getEmail(): String
        + getPassword(): String
        + getRole(): String
        + getTasks(): List~Task~
        + addTask(task: Task): void
        + removeTask(task: Task): void
    }

    class Task {
        - id: Long
        - title: String
        - description: String
        - dueDate: LocalDateTime
        - status: TaskStatus
        - user: User
        + getId(): Long
        + getTitle(): String
        + getDescription(): String
        + getDueDate(): LocalDateTime
        + getStatus(): TaskStatus
        + getUser(): User
        + assignToUser(user: User): void
    }

    class Notification {
        - id: Long
        - task: Task
        - sendDate: LocalDateTime
        - sent: Boolean
        + getId(): Long
        + getTask(): Task
        + getSendDate(): LocalDateTime
        + isSent(): Boolean
    }

    User "1" -- "N" Task : has
    Task "1" -- "0..1" Notification : triggers
