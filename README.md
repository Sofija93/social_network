# 📱 Social Network (Android App)

Moderna Android aplikacija razvijena u Kotlin-u, namenjena umrežavanju korisnika, deljenju objava i multimedijalnog sadržaja (reel-ova). 
Projekat je organizovan po čistoj paketnoj arhitekturi i koristi standardne Android komponente za interfejs i upravljanje podacima.

## 🚀 Tehnologije i alati

* **Programski jezik:** [Kotlin](https://kotlinlang.org/)
* **Platforma:** Android SDK (Android Studio)
* **Arhitektura & UI:** 
  * `Activity` i `Fragment` komponente za navigaciju i ekrane
  * `RecyclerView` sa prilagođenim adapterima za dinamičke liste
  * Upravljanje autentifikacijom i korisničkim sesijama

## 📂 Struktura projekta (`com.sofia.izipizisocialnetwork`)

Aplikacija je modularno podeljena u sledeće pakete i komponente:

* **`adapters/`** – Adapteri za povezivanje podataka sa listama (`RecyclerView`):
  * `FollowAdapter.kt` – Prikaz i upravljanje listom praćenja korisnika.
  * `MyPostRvAdapter.kt` & `PostAdapter.kt` – Prikaz objava na profilu i glavnom feed-u.
  * `MyReelAdapter.kt` & `ReelAdapter.kt` – Upravljanje video sadržajem i reel-ovima.
  * `SearchAdapter.kt` – Prikaz rezultata pretrage.
  * `ViewPagerAdapter.kt` – Upravljanje tabovima i prelazima između ekrana.
* **`fragments/`** – UI fragmenti za različite sekcije aplikacije (feed, pretraga, profil, obaveštenja).
* **`models/`** – Podatkovne klase (Data Classes) koje definišu strukturu entiteta (korisnici, objave, komentari).
* **`post/`** – Logika vezana za kreiranje, pregled i interakciju sa objavama.
* **`utils/`** – Pomoćne klase i funkcije (kriva validacija, rad sa fajlovima, mrežne skripte).

### 🔑 Glavne aktivnosti (Activities)
* `LoginActivity.kt` – Ekran za prijavljivanje postojećih korisnika.
* `SignUpActivity.kt` – Registracija novih naloga.
* `MainActivity.kt` – Osnovni kontejner i početna tačka nakon prijave.
* `HomeActivity.kt` – Glavni radni ekran aplikacije sa feed-om i navigacijom.

## ⚙️ Pokretanje projekta

1. Klonirajte repozitorijum na svoj računar.
2. Otvorite projekat u razvojnom okruženju **[Android Studio](https://developer.android.com/studio)**.
3. Sačekajte da Gradle sinhronizuje zavisnosti.
4. Pokrenite aplikaciju na emulatoru ili fizičkom Android uređaju
