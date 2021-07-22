package dev.bukgeuk.polarmagic.ext;

public interface PlayerEntityExt {
    Double getMaxManaAmount();
    Double getCurrentManaAmount();
    void setMaxManaAmount(double amount);
    void setCurrentManaAmount(double amount);

    Integer getMagicLevel();

    Double getMagicCurrentExp();
    void addMagicCurrentExp(double exp);
    Double getMagicMaxExp();
    void setMagicMaxExp(double exp);

    Double getManaRecoveryAmount();
    void setManaRecoveryAmount(double amount);
}