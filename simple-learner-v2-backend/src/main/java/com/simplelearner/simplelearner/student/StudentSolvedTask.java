package com.simplelearner.simplelearner.student;

import com.simplelearner.simplelearner.task.Task;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
public class StudentSolvedTask {

    @EmbeddedId
    private StudentSolvedTaskId id;

    @ManyToOne
    @JoinColumn(name = "student_name", insertable = false, updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "task_id", insertable = false, updatable = false)
    private Task task;

    private Instant timestamp;
    private boolean correct;

    public StudentSolvedTask() {}

    public StudentSolvedTask(Student student, Task task, boolean correct) {
        this.id = new StudentSolvedTaskId(student.getName(), task.getId());
        this.student = student;
        this.task = task;
        this.correct = correct;
        this.timestamp = Instant.now();

        student.getSolvedTasks().add(this);
        task.getSolvedByStudents().add(this);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentSolvedTask that = (StudentSolvedTask) o;
        return Objects.equals(student, that.student) &&
                Objects.equals(task, that.task) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {

        return Objects.hash(student, task, timestamp);
    }
}
