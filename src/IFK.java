public interface IFK {
    void InitialisiereSpeichermedium(String speichermedium);

    void LeseHausbesitzer(int Hausbesitzer_ID);
    void SpeichereNeuenHausbesitzer(String Hausbesitzer_Name);
    void AendereHausbesitzer(int Hausbesitzer_ID, String Hausbesitzer_Name);
    void LoescheHausbesitzer(int Hausbesitzer_ID);
    int GetHausbesitzer_ID();
    String GetHausbesitzer_Name();
    int GetAnzahlHausbesitzer();

    void LeseImmobilie(int Immobilie_ID);
    void LoescheImmobilie(int Immobilie_ID);
    void AendereImmobilie(int Immobilie_ID, String Immobilie_Adresse, float Immobilie_Preis, boolean Immobilie_Typ, float Immobilie_Flaeche, int Immobilie_Raeume);
    void ErstelleImmobilie(String Immobilie_Adresse, float Immobilie_Preis, boolean Immobilie_Typ, float Immobilie_Flaeche, int Immobilie_Raeume);
    int GetImmobilie_ID();
    String GetImmobilie_Adresse();
    float GetImmobilie_Preis();
    boolean GetImmobilie_Typ();
    float GetImmobilie_Flaeche();
    int GetImmobilie_Raeume();
    int GetAnzahlImmobilie();

    void AendereHausbesitzer();
    int GetHausbesitzer();
}
