## Diagrama de Classes

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
