Feature:MTU BestallUtkorning
@BestallUtkorning
Scenario:BestallUtkorning
Given I go to "MTUUrl" on "Chrome"
And i wait till page is loaded
And i wait for "100" seconds
And i wait till Element is loaded
And i click on "Postservice" Element
And i wait till Element is loaded
And i wait till Element is loaded
And i click on "Bestall_utkorning" Element
And i enter "Foretagsnamm_utkorning" as "chai"
And i enter "Foretagsnamm_utkorning" as "jagirdar"
And i enter "Adress_utkorning" as "DLF"
And i enter "Postnummer_utkorning" as "13145"
And i click on "ort_utkorning" Element
And i enter "ort_utkorning" as "nacka"
And i enter "Boxnummer_utkorning" as "10123"
And i enter "Postnummer2_utkorning" as "10123"
And i click on "Utkorning_utkorning" Element
And i click on "Mypack_Postpaket_utkorning" Element
And i click on "NAsta_utkorning" Element
And i enter "sok_pa_utkorning" as "CGI"
And i click on "SOK_utkorning" Element
And i click on "CGI Sverige AB" Element out of "searchResults_utkorning"
And i wait till Element is loaded
And i enter "Firmatecknare_utkorning" as "chai"
And i enter "Fornamn_utkorning" as "chai"
And i enter "Efternamn_utkorning" as "jagirdar"
And i enter "Telefon_utkorning" as "09632853095"
And i enter "Email_utkorning" as "chaitra108@gmail.com"
And i enter "SkrivE-mail_utkorning" as "chaitra108@gmail.com"
And i click on "Addresen_utkorning" Element
And i wait till Element is loaded
And i wait till Element is loaded
And i click on "NAsta2_utkorning" Element
Then "OrderSummary_utkorning1" should be present for scenario to be "PASS"

And Type to console n log the feature executed
|BestallUtkorning|MTU|Executed|