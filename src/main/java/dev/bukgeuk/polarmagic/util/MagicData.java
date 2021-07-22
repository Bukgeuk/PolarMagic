package dev.bukgeuk.polarmagic.util;

public class MagicData {
    public Double maxManaAmount;
    public Double currentManaAmount;
    public Double aCurrentManaAmount;
    public Integer magicLevel;
    public Double magicCurrentExp;
    public Double aMagicCurrentExp;
    public Double magicMaxExp;
    public Double aMagicMaxExp;
    public Double manaRecoveryAmount;

    public MagicData(Double maxManaAmount, Double currentManaAmount, Integer magicLevel, Double magicCurrentExp, Double magicMaxExp, Double manaRecoveryAmount, Double aCurrentManaAmount, Double aMagicCurrentExp, Double aMagicMaxExp) {
        this.maxManaAmount = maxManaAmount;
        this.currentManaAmount = currentManaAmount;
        this.magicLevel = magicLevel;
        this.magicCurrentExp = magicCurrentExp;
        this.magicMaxExp = magicMaxExp;
        this.aCurrentManaAmount = aCurrentManaAmount;
        this.manaRecoveryAmount = manaRecoveryAmount;
        this.aMagicCurrentExp = aMagicCurrentExp;
        this.aMagicMaxExp = aMagicMaxExp;
    }
}

