package com.backend.fluxnewsapi.domain.dtos.models;

import lombok.Data;

@Data
public class Appreciation {

    public Appreciation() {
    }

    public Appreciation(Long refId, Long userid) {
        this.refId = refId;
        this.userid = userid;
    }

    private Long refId;
    private Long userid;
    private boolean lu;
    private boolean aimer;
    private int note;
}
