Feature: Test Onboarding - Login Functionality
    
  Scenario Outline: Verify that user can successfully login to Avon application
    Given User is on Welcome page
    When user selects "<Market>" and "<language>" from dropdown
    And User enters valid "<AccountNumber>" and "<Password>" on Login Page
    And User enters the "7,2,4,5" PIN code
    And User confirms the "7,2,4,5" PIN code
    Then Avon Home Page is displayed
    When user opens Menu icon
    And user click on "Log out" button
    Then Verify user is logged out

    Examples: 
      | Market         | language | AccountNumber | Password |
      | United Kingdom | English  |           307 | testteam |