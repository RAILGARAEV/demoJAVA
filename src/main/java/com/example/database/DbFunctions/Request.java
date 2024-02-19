package com.example.database.DbFunctions;

import java.util.Date;
import java.util.List;

public class Request {
    String id, date, equipment, tip, priority, ispolnitel, problem, status;

    public Request(String id, String date, String equipment, String tip, String priority, String ispolnitel, String problem, String status) {
        this.id = String.valueOf(id);
        this.date = String.valueOf(date);
        this.equipment = equipment;
        this.tip = tip;
        this.priority = priority;
        this.ispolnitel = ispolnitel;
        this.problem = problem;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getIspolnitel() {
        return ispolnitel;
    }

    public void setIspolnitel(String ispolnitel) {
        this.ispolnitel = ispolnitel;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
