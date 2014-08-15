Feature: CucumberFeature Poc

As a developer
I want to implement a Cucumber Poc
In order to increase my knwoledge

@usingSample
Scenario: CucumberScenario Poc
	Given the existing Samples
		| name 		| age 	| height 	|
		| Laruska 	| 2 	| 0.89 		|
		| Albuchi 	| 28 	| 1.68 		|
		| Balder 	| 31 	| 1.75 		|
	When the user find all samples
	Then response is "OK"
	And the user gets a list of all samples
	