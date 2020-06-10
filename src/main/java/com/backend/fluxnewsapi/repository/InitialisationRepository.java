package com.backend.fluxnewsapi.repository;

import com.backend.fluxnewsapi.models.Initialisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InitialisationRepository extends JpaRepository<Initialisation, Long> {
    /*@Query("SELECT*FROM initialisation")
    Initialisation findInitedDataParam();*/

/*    @Query("INSERT INTO initialisation value(:tomorow,:init)")
    void insertInitDataParam(@Param("tomorow") String tomorowDate, @Param("init") int init);

    @Query("UPDATE initialisation i SET i.nextInitDate = :tomorow, i.dataInitied = :init WHERE i.nextInitDate = today")
    void updateInitDataParam(@Param("tomorow") String tomorowDate, @Param("init") int init);*/

    /*@Query("SELECT i FROM initialisation i WHERE i.updateday = ?1 AND i.userId = ?2")*/
    Initialisation findByUserId(long userId);
    boolean existsByUserId(long userId);

    /*@Query("SELECT u FROM User u WHERE u.status = ?1")
    User findUserByStatus(Integer status);*/

}
