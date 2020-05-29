package com.backend.fluxnewsapi.models;

import com.backend.fluxnewsapi.utils.DateUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

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
         * and specific hour of updating
         */
        if(firstInit){
            this.updateday =  DateUtils.today();
            this.toInitied = false;
        }else{
            this.updateday =  DateUtils.tomorrow();
            this.toInitied = false;
        }
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String updateday;
    @Column(name = "toinitied")
    private boolean toInitied;
    @OneToOne
    @MapsId
    private User user;
}
