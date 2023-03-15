Feature: JDBC uzerinden Databse baglantisi kurularak ilk test
  @jdbc

  Scenario: Users tablosundan isimler testi.

    Given Database ile iletisimi baslat
    Then Query statement araciligi ile database gonderilir
    Then Databaseden donen resulset verisi test edilir
    And Database kapatilir


