Feature:MTU BestallPostbox
@BestallPostbox
Scenario:BestallPostbox
Given I go to "MTUUrl" on "Chrome"
And i click on "Postservice" Element
And i wait till page is loaded
And i wait for "100" seconds
And i wait till Element is loaded
And i click on "Bestall_postbox" Element
And i enter "Hitta_En_postbox" as "nacka"
And i click on "sok_postbox" Element
And i wait till Element is loaded
And i click on "TORGGATAN 8" Element out of "SearchResults_postbox"
And i click on "Nasta_postbox" Element
And i enter "sok_pa_postbox" as "CGI"
And i wait till Element is loaded
And i click on "sok2_postbox" Element
And i click on "CGI Sverige AB" Element out of "SearchResults2_postbox"
And i enter "Firmatechknara_postbox" as "chaitraJ"
And i enter "Fornamm_postbox" as "chaitraJ"
And i enter "Efternamn_postbox" as "chaij"
And i enter "Telefon_postbox" as "0987654321"
And i enter "Email_postbox" as "chaitra108@gmail.com"
And i enter "SkrivEmail_postbox" as "chaitra108@gmail.com"
And i click on "Adressen_for_postboxen_postbox" Element
And i wait till Element is loaded
And i click on "Nasta3_postbox" Element
Then "summary_postbox" should be present for scenario to be "PASS"

And Type to console n log the feature executed
|BestallPostbox|MTU|Executed|