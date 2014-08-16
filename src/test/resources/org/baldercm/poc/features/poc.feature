@deleteSamples
Feature: CucumberFeature Poc

As a developer
I want to implement a Cucumber Poc
In order to increase my knwoledge

Scenario: CucumberScenario Poc
	Given the existing samples
		| name 		| age 	| height 	|
		| Laruska 	| 2 	| 0.89 		|
		| Albuchi 	| 28 	| 1.68 		|
		| Balder 	| 31 	| 1.75 		|
	When the user finds all samples
	Then response is "OK"
	And the user gets a list of all samples

Scenario: Create Valid Sample
	Given no existing samples
	When the user creates a sample
		| name 		| age 	| height 	|
		| Laruska 	| 22	| 0.89 		|
	Then response is "CREATED"
	And the sample is saved
	
Scenario: Create Invalid Sample
	Given no existing samples
	When the user creates a sample
		| name 		| age 	| height 	|
		| Laruska 	| 2 	| 0.89 		|
	Then response is "BAD_REQUEST"
	And the sample is not saved