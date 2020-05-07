package com.backend.fluxnewsapi.services;

import com.backend.fluxnewsapi.models.Initialisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InitialisationRepository extends JpaRepository<Initialisation, String> {
    /*@Query("SELECT*FROM initialisation")
    Initialisation findInitedDataParam();*/

/*    @Query("INSERT INTO initialisation value(:tomorow,:init)")
    void insertInitDataParam(@Param("tomorow") String tomorowDate, @Param("init") int init);

    @Query("UPDATE initialisation i SET i.nextInitDate = :tomorow, i.dataInitied = :init WHERE i.nextInitDate = today")
    void updateInitDataParam(@Param("tomorow") String tomorowDate, @Param("init") int init);*/
}
