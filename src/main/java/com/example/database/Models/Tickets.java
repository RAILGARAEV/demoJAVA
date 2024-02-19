package com.example.database.Models;


public class Tickets {

    String id, date, equipment, tip, priority, ispolnitel, problem, status, remont, time, materials, colmaterials, cost, comment;

    public Tickets(String id, String date, String equipment, String tip, String priority, String ispolnitel, String problem, String status, String remont, String time, String materials, String colmaterials, String cost, String comment) {
        this.id = String.valueOf(id);
        this.date = String.valueOf(date);
        this.equipment = equipment;
        this.tip = tip;
        this.priority = priority;
        this.ispolnitel = ispolnitel;
        this.problem = problem;
        this.status = status;
        this.remont = remont;
        this.time = time;
        this.materials = materials;
        this.colmaterials = colmaterials;
        this.cost = cost;
        this.comment = comment;
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

    public String getRemont() {
        return remont;
    }

    public void setRemont(String remont) {
        this.remont = remont;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }
    public String getColMaterials() {
        return colmaterials;
    }

    public void setColMaterials(String colmaterials) {
        this.colmaterials = colmaterials;
    }
    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}