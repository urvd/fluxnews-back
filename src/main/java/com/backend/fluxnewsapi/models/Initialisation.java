package com.backend.fluxnewsapi.models;

import com.backend.fluxnewsapi.utils.DateUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@EqualsAndHashCode
@Entity(name = "initialisation")
public class Initialisation {
    public Initialisation(){

    }
    public Initialisation(boolean firstInit){
        /**
         * set init date for tomorrow
         * so that when today is tomorrow
         * we can update it.
         */
        this.id = "initial";
        this.nextInitDate = DateUtils.tomorrow();
        this.toInitied = true;
    }
    @Id
    private String id;
    private String nextInitDate;
    private boolean toInitied;
}
