@Web
Feature: Sign Up Functionality
    
  Scenario Outline: Verify that user can sign up to avon web
    Given Launch the application
    When user enters Contact information like "<firstName>","<lastName>","<Email>","<Country Code>","<Number>"
    And select user consent checboxes
    And user click on button with text "continue"

    Examples: 
      | firstName | lastName | Email                    | Country Code | Number     |
      | Kishor    | Mathe    | Kishor.Mathe@nttdata.com | +91          | 8805291426 |
