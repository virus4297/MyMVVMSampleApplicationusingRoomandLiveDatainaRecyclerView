package com.example.mymvvmsampleapplicationusingroomandlivedatainarecyclerview;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String description;

    private String status;

    private String snmp;

    private long bandwidth;

    private long cpu;

    private long ram;

    private long disk;

    private String time;


    public Note(String title, String description, String status,String snmp,
                long bandwidth,long cpu,long ram,long disk,String time) {
        this.title = title;
        this.description = description;
        this.status=status;
        this.snmp=snmp;
        this.bandwidth=bandwidth;
        this.cpu=cpu;
        this.ram=ram;
        this.disk=disk;
        this.time=time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getSnmp() {
        return snmp;
    }

    public long getBandwidth() {
        return bandwidth;
    }

    public long getCpu() {
        return cpu;
    }

    public long getRam() {
        return ram;
    }

    public long getDisk() {
        return disk;
    }

    public String getTime() {
        return time;
    }

}
