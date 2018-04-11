package com.simplelearner.simplelearner.student;

import com.simplelearner.simplelearner.task.Task;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentSolvedTaskId implements Serializable {

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "task_id")
    private Long taskId;

    public StudentSolvedTaskId() {}

    public StudentSolvedTaskId(String studentName, Long taskId) {
        this.studentName = studentName;
        this.taskId = taskId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentSolvedTaskId that = (StudentSolvedTaskId) o;
        return Objects.equals(studentName, that.studentName) &&
                Objects.equals(taskId, that.taskId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentName, taskId);
    }
}
