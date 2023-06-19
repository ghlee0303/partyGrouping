package com.party_grouping.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "character_table", indexes = {@Index(name = "character_index", columnList = "apiId, server")})
public class CharacterEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer level;
    private Integer fame;
    private String apiId;
    private String server;
    private String adventureName;
    private String jobName;       // 직업군
    private String jobGrowName;   // 전직
    private String jobId;         // 직업군 id
    private String jobGrowId;     // 전직 id
    private int buffLevel;
    private boolean buffMax;
    private String buffName;
    private String buffId;
    private boolean buffer;

    @OneToOne(targetEntity = CharacterItemEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name="item_id")
    private CharacterItemEntity item;

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

    public String getAdventureName() {
        return adventureName;
    }

    public void setAdventureName(String adventureName) {
        this.adventureName = adventureName;
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

    public int getBuffLevel() {
        return buffLevel;
    }

    public void setBuffLevel(int buffLevel) {
        this.buffLevel = buffLevel;
    }

    public String getBuffName() {
        return buffName;
    }

    public void setBuffName(String buffName) {
        this.buffName = buffName;
    }

    public String getBuffId() {
        return buffId;
    }

    public void setBuffId(String buffId) {
        this.buffId = buffId;
    }

    public boolean isBuffer() {
        return buffer;
    }

    public void setBuffer(boolean buffer) {
        this.buffer = buffer;
    }

    public CharacterItemEntity getItem() {
        return item;
    }

    public void setItem(CharacterItemEntity item) {
        this.item = item;
    }

    public boolean isBuffMax() {
        return buffMax;
    }

    public void setBuffMax(boolean buffMax) {
        this.buffMax = buffMax;
    }
}
