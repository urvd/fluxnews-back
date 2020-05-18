package com.backend.fluxnewsapi.controllers;

import com.backend.fluxnewsapi.models.Initialisation;
import com.backend.fluxnewsapi.services.InitialisationRepository;
import com.backend.fluxnewsapi.utils.DateUtils;

import java.util.Optional;

public class InitilisationRessource {
    /**
     * this class is not a controller, just for internal use
     */
    private static InitialisationRepository initRepository;
    /*public InitilisationRessource(InitialisationRepository initRepository){
        this.initRepository = initRepository;

    }*/

    public static void createInitialisationParam(){
        /**
         * initialisation au moment de la création d'un User.
         */
        Initialisation initialisation = new Initialisation(true);
        initRepository.save(initialisation);
    }
    //@GetMapping("/init/show")
    private static Initialisation getInitParamShow(){
        /**
         * recherche paramettre de mis a jour article.
         */
        Optional<Initialisation> init = initRepository.findById("initial");
        return init.get();
    }

    public static void updateInitParam(Initialisation update){
        /**
         * recherche avant de mettre a jour les articles.
         */
        Optional<Initialisation> currentInit = Optional.of(getInitParamShow());
        Initialisation current =  currentInit.get();
        if(!update.getNextInitDate().equals(current.getNextInitDate())){
            current.setToInitied(update.isToInitied());
        }
        if(!update.isToInitied() == current.isToInitied()){
            currentInit.get().setNextInitDate(DateUtils.tomorrow());
        }
    }

    public static boolean checkNextInitDay(){
        return (Optional.of(getInitParamShow()).get().getNextInitDate())
                .equals(DateUtils.today());
    }
    public static boolean checkToInitData(){
        return (Optional.of(getInitParamShow()).get().getNextInitDate())
                .equals(DateUtils.today());
    }

}
