package com.party_grouping.dto;

import java.time.LocalDateTime;

public class BaseDto {
    protected LocalDateTime reg_date;
    protected LocalDateTime del_date;

    public LocalDateTime getReg_date() {
        return reg_date;
    }

    public void setReg_date(LocalDateTime reg_date) {
        this.reg_date = reg_date;
    }

    public LocalDateTime getDel_date() {
        return del_date;
    }

    public void setDel_date(LocalDateTime del_date) {
        this.del_date = del_date;
    }
}
