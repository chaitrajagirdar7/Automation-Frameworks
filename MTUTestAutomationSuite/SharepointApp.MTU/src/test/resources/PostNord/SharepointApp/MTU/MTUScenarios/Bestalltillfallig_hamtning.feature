Feature:MTU Bestalltillfallig_hamtning
@Bestalltillfallig_hamtning
Scenario Outline:Bestalltillfallig_hamtning

Given I go to "MTUUrl" on "<browser>"
And i wait till page is loaded
And i wait for "100" seconds
And i click on "Postservice" Element
And i wait till Element is loaded
And i click on "Bestall_tillfallig_hamtning" Element
And i enter "sokpa_tillfallig_hamtning" as "CGI"
And i click on "sok_tillfallig_hamtning" Element
And i click on "<SearchResult>" Element out of "searchResults_tillfallig_hamtning"
And i enter "Firmatecknare_tillfallig_hamtning" as "<Fullname>"
And i enter "Foretagsnamn_tillfallig_hamtning" as "<FirstName>"

And i enter "Adress_tillfallig_hamtning" as "DLF"
And i enter "Postnummer_tillfallig_hamtning" as "13145"
And i wait till Element is loaded
And i wait till Element is loaded
And i wait till Element is loaded
And i wait till Element is loaded
And i wait till Element is loaded
And i wait till Element is loaded
And i click on "ort_tillfallig_hamtning" Element
And i enter "ort_tillfallig_hamtning" as "nacka"
And i enter "FName_tillfallig_hamtning" as "CHAI"
And i enter "LName_tillfallig_hamtning" as "JAGI10123"
And i enter "Telefon_tillfallig_hamtning" as "09632853095"
And i enter "Email_tillfallig_hamtning" as "chaitra108@gmail.com"
And i enter "ConfirmEmail_tillfallig_hamtning" as "chaitra108@gmail.com"
And i click on "JagAcceptar_tillfallig_hamtning" Element
And i click on "LagTill_tillfallig_hamtning" Element
And i wait till Element is loaded
And i wait till Element is loaded
And i wait till Element is loaded
And i wait till Element is loaded
And i scroll till "Finish_tillfallig_hamtning" Element 
And i click on "Finish_tillfallig_hamtning" Element
Then "order_summary_tillfallig_hamtning" should be present for scenario to be "PASS"

Examples:
|browser|SearchResult|Fullname|FirstName|
|Mozilla|CGI Sverige AB|chaitraJagirdar|chaitra|

