package dev.bukgeuk.polarmagic.util;

import java.util.LinkedList;
import java.util.Queue;

public class MagicData {
    public Double maxManaAmount;
    public Double currentManaAmount;
    public Double aCurrentManaAmount;
    public Integer magicLevel;
    public Double magicCurrentExp;
    public Double magicMaxExp;
    public Double manaRecoveryAmount;

    public MagicData(Double maxManaAmount, Double currentManaAmount, Integer magicLevel, Double magicCurrentExp, Double magicMaxExp, Double aCurrentManaAmount, Double manaRecoveryAmount) {
        this.maxManaAmount = maxManaAmount;
        this.currentManaAmount = currentManaAmount;
        this.magicLevel = magicLevel;
        this.magicCurrentExp = magicCurrentExp;
        this.magicMaxExp = magicMaxExp;
        this.aCurrentManaAmount = aCurrentManaAmount;
        this.manaRecoveryAmount = manaRecoveryAmount;
    }
}

