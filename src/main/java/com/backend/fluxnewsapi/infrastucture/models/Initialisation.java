package com.backend.fluxnewsapi.infrastucture.models;

import com.backend.fluxnewsapi.domain.utils.DateUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
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
            this.toInitied = true;
        }else{
            this.updateday =  DateUtils.tomorrow();
            this.toInitied = true;
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
