
Feature:MTU Bestall hamtning
@Bestallhamtning
Scenario:Beställ hämtning
Given I go to "MTUUrl" on "Chrome"
And i click on "Postservice" Element
And i wait till page is loaded
And i wait for "100" seconds
And i wait till Element is loaded
And i click on "Bestall_hamtning" Element
And i enter "Foretagsnamn" as "chaitraJ"
And i enter "Address" as "CGI"
And i enter "Postnummer_ort" as "13145"
And i click on "City_field" Element
And i enter "City_field" as "NACKA"
And i click on "vardar_Max15" Element
And i click on "Nasta" Element
And i enter "SearchPar" as "CGI"
And i click on "Sok" Element
And i click on "CGI Sverige AB" Element out of "SearchResults"
And i enter "BesokAddress" as "TOSHAMNSG"
And i enter "Postnummerort1" as "16440"
And i enter "Firm" as "16440"
And i enter "ForeNamm" as "chaitraJ"
And i enter "EfterNamn" as "Jagirdar"
And i enter "Telefon" as "0987654321"
And i enter "Email" as "chaitra108@gmail.com"
And i enter "ConfirmEmail" as "chaitra108@gmail.com"
And i click on "Addresspostbox" Element
And i wait for "100" seconds
And i wait till Element is loaded
And i enter "billToZipcode" as "13145"
And i click on "BillToCity" Element
And i enter "BillToCity" as "NACKA"
And i wait till Element is loaded
And i enter "billToAddress" as "DLF"
And i click on "Nasta2" Element
Then "OrderSummary" should be present for scenario to be "PASS"
And Type to console n log the feature executed
|Bestallhamtning|MTU|Executed|
