package com.party_grouping.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "character_table")
public class CharacterEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer level;
    private Integer fame;
    private String apiId;
    private String server;
    private String jobName;       // 직업군
    private String jobGrowName;   // 전직
    private String jobId;         // 직업군 id
    private String jobGrowId;     // 전직 id

    public CharacterEntity() {
    }

    public CharacterEntity(String name, Integer level) {
        this.name = name;
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getFame() {
        return fame;
    }

    public void setFame(Integer fame) {
        this.fame = fame;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGrowName() {
        return jobGrowName;
    }

    public void setJobGrowName(String jobGrowName) {
        this.jobGrowName = jobGrowName;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobGrowId() {
        return jobGrowId;
    }

    public void setJobGrowId(String jobGrowId) {
        this.jobGrowId = jobGrowId;
    }
}
